package com.disnodeteam.dogecv.detectors;

import com.disnodeteam.dogecv.OpenCVPipeline;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CryptoboxDetectorV2 extends OpenCVPipeline {

    public enum CryptoboxDetectionMode {
        RED, BLUE
    }

    public enum CryptoboxSpeed {
        VERY_FAST, FAST, BALANCED, SLOW, VERY_SLOW
    }

    public class CryptoboxTrackable{
        public List<Point> points = new ArrayList<>();
    }

    public CryptoboxDetectionMode detectionMode      = CryptoboxDetectionMode.RED;
    public double                 downScaleFactor    = 0.6;
    public boolean                rotateMat          = false;
    public CryptoboxSpeed         speed              = CryptoboxSpeed.BALANCED;
    public int                    centerOffset       = 0;
    public boolean                debugShowMask      = true;


    private boolean CryptoBoxDetected = false;
    private boolean ColumnDetected = false;
    private int[] CryptoBoxPositions = new int[3];


    Scalar lower = new Scalar(90, 135, 25);
    Scalar upper = new Scalar(130, 250, 150);

    private Mat workingMat = new Mat();
    private Mat blurredMat = new Mat();
    private Mat mask1  = new Mat();
    private Mat mask2  = new Mat();
    private Mat mask  = new Mat();
    private Mat hsv  = new Mat();
    private Mat structure  = new Mat();
    private Mat hierarchy = new Mat();
    Mat kernel = Mat.ones(3,3,CvType.CV_32F);

    private Size newSize = new Size();

    private CryptoboxTrackable[] collumnTrack = new CryptoboxTrackable[3];


    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        Size initSize= rgba.size();
        newSize  = new Size(initSize.width * downScaleFactor, initSize.height * downScaleFactor);
        rgba.copyTo(workingMat);


        Imgproc.resize(workingMat, workingMat,newSize);


        if(rotateMat){
            Mat tempBefore = workingMat.t();

            Core.flip(tempBefore, workingMat, 1); //mRgba.t() is the transpose

            tempBefore.release();
        }



        List<MatOfPoint> contours = new ArrayList<>();
        List<Rect> boxes = new ArrayList<>();

        Imgproc.erode(workingMat, blurredMat,kernel);
        Imgproc.dilate(blurredMat, blurredMat,kernel);


        switch(detectionMode){
            case RED:
                getRedMask(blurredMat);
                break;
            case BLUE:
                getBlueMask(blurredMat);
        }

        /*

        switch (speed){
            case VERY_FAST:

                structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1,30));
                Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);
                break;
            case FAST:

                structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2,30));
                Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);
                break;

            case BALANCED:

                structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2,40));
                Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);
                break;


            case SLOW:
                Imgproc.blur(hsv,hsv,new Size(7,7));
                structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2,55));
                Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);
                break;

            case VERY_SLOW:

                structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,60));
                Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);
                break;
        }
        */
       // Imgproc.erode(mask, mask,kernel);



        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        for(MatOfPoint c : contours) {
            if(Imgproc.contourArea(c) >= newSize.height * 0.8 ) { //Filter by area
                Rect column = Imgproc.boundingRect(c);
                int ratio = Math.abs(column.height / column.width);

                if(ratio > 1.5) { //Check to see if the box is tall
                    boxes.add(column); //If all true add the box to array
                }
            }
        }
        for(Rect box : boxes) {
            Imgproc.rectangle(workingMat,new Point(box.x,box.y),new Point(box.x+box.width,box.y+box.height),new Scalar(255,0,0),2);
        }

        Collections.sort(boxes, new Comparator<Rect>() {
            @Override
            public int compare(Rect rect, Rect t1) {
                if(rect.x > t1.x){
                    return 1;
                }else if(rect.x < t1.x){
                    return -1;
                }else{
                    return 0;
                }
            }
        });


        for(int i=0;i<boxes.size() - 1;i++){

            if(i<3){
                if(collumnTrack[i] == null){
                    collumnTrack[i] = new CryptoboxTrackable();
                }

                if(collumnTrack[i].points.size() < 10){
                    collumnTrack[i].points.add(drawSlot(i,boxes));
                }else{
                    Collections.rotate(collumnTrack[i].points, -1);
                    collumnTrack[i].points.set(9,drawSlot(i,boxes));
                }

                for(int j =0;j< collumnTrack[i].points.size();j++){
                    Imgproc.circle(workingMat,collumnTrack[i].points.get(j),4,new Scalar(255,255,255),3);
                }
            }
        }

        CryptoBoxDetected = boxes.size() >=4;
        if(CryptoBoxDetected){
            Point left = drawSlot(0,boxes);
            Point center = drawSlot(1,boxes);
            Point right = drawSlot(2,boxes);

            CryptoBoxPositions[0] = (int)left.x;
            CryptoBoxPositions[1] = (int)center.x;
            CryptoBoxPositions[2] = (int)right.x;

            Imgproc.putText(workingMat, "Left", new Point(left.x - 10, left.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(workingMat,left,5,new Scalar(0,255,255), 3);

            Imgproc.putText(workingMat, "Center", new Point(center.x - 10, center.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(workingMat,center, 5,new Scalar(0,255,255), 3);

            Imgproc.putText(workingMat, "Right", new Point(right.x - 10, right.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(workingMat,right, 5,new Scalar(0,255,255), 3);
        }else{
            for(int i=0;i<boxes.size() - 1;i++){
                Point collumn = drawSlot(i,boxes);
                Imgproc.circle(workingMat,collumn,5,new Scalar(0,255,255), 3);
                if(i<3){
                    CryptoBoxPositions[i] = (int)collumn.x;
                }
            }

            ColumnDetected = boxes.size() > 1;
        }


        if(rotateMat){

            Mat tempAfter = workingMat.t();

            Core.flip(tempAfter, workingMat, 0); //mRgba.t() is the transpose

            tempAfter.release();
        }

        Imgproc.resize(workingMat, workingMat, initSize);
        Imgproc.resize(mask, mask, initSize);

        Imgproc.putText(workingMat,"DogeCV CryptoV2: " + newSize.toString() + " - " + speed.toString() + " - " + detectionMode.toString() ,new Point(5,15),0,0.6,new Scalar(0,255,255),2);
        return mask;


    }

    public Mat getRedMask(Mat input){
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        List<Mat> channels = new ArrayList<Mat>();
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, 164.0, 255, Imgproc.THRESH_BINARY);
        return mask;
    }

    public Mat getBlueMask(Mat input){

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YUV);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        List<Mat> channels = new ArrayList<Mat>();
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, 145.0, 255, Imgproc.THRESH_BINARY);

        return mask;
    }


    public Point drawSlot(int slot, List<Rect> boxes){
        Rect leftColumn = boxes.get(slot); //Get the pillar to the left
        Rect rightColumn = boxes.get(slot + 1); //Get the pillar to the right

        int leftX = leftColumn.x; //Get the X Coord
        int rightX = rightColumn.x; //Get the X Coord

        int drawX = ((rightX - leftX) / 2) + leftX; //Calculate the point between the two
        int drawY = leftColumn.height + leftColumn.y; //Calculate Y Coord. We wont use this in our bot's opetation, buts its nice for drawing

        return new Point(drawX, drawY);
    }

    public ArrayList ones(int width, int height) {
        ArrayList output = new ArrayList();
        for(int i = 1; i <= height; i++) {
            ArrayList row = new ArrayList();
            for(int j = 1; i <= width; i++) {
                row.add(1);
            }
            output.add(row);
        }
        return output;
    }

    public int[] getCryptoBoxPositions() {
        return CryptoBoxPositions;
    }

    public int getCryptoBoxLeftPosition() {
        return CryptoBoxPositions[0];
    }

    public int getCryptoBoxCenterPosition() {
        return CryptoBoxPositions[1];
    }

    public int getCryptoBoxRightPosition() {
        return CryptoBoxPositions[2];
    }

    public boolean isCryptoBoxDetected() {
        return CryptoBoxDetected;
    }

    public boolean isColumnDetected() {
        return ColumnDetected;
    }

    public int[] getCryptoBoxOffsets() {
        int[] offsets = new int[CryptoBoxPositions.length];

        for (int i = 0; i < CryptoBoxPositions.length ; i++) {
            int center = (int)(newSize.width / 2) + centerOffset;
            offsets[i] = CryptoBoxPositions[i] - center;
        }

        return offsets;
    }

    public int getCryptoBoxLeftOffset() {
        int center = (int)(newSize.width / 2) + centerOffset;
        return  CryptoBoxPositions[0] - center;

    }

    public int getCryptoBoxCenterOffset() {
        int center = (int)(newSize.width / 2) + centerOffset;
        return  CryptoBoxPositions[1] - center;
    }

    public int getCryptoBoxRightOffset() {
        int center = (int)(newSize.width / 2) + centerOffset;
        return  CryptoBoxPositions[2] - center;
    }


    public Size getFrameSize() {
        return newSize;
    }


}
