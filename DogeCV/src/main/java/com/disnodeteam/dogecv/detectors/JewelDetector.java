package com.disnodeteam.dogecv.detectors;


import com.disnodeteam.dogecv.OpenCVPipeline;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 11/5/2017.
 */

public class JewelDetector extends OpenCVPipeline {

    public enum JewelOrder {
        RED_BLUE,
        BLUE_RED,
        UNKNOWN
    }


    private JewelOrder order = JewelOrder.UNKNOWN;

    public enum JewelDetectionMode {
        EDGE_SCORING
    }

    public enum JewelDetectionSpeed {
        VERY_FAST, FAST, BALANCED, SLOW, VERY_SLOW
    }


    public JewelDetectionMode  detectionMode    = JewelDetectionMode.EDGE_SCORING;
    public double              downScaleFactor  = 0.6;
    public boolean             rotateMat        = false;
    public JewelDetectionSpeed speed            = JewelDetectionSpeed.BALANCED;
    public double perfectArea = 750;

    private Mat workingMat = new Mat();
    private Mat blurredMat  = new Mat();
    private Mat maskRed  = new Mat();
    private Mat maskBlue  = new Mat();
    private Mat hiarchy  = new Mat();

    Mat kernel = Mat.ones(5,5, CvType.CV_32F);

    private Size newSize = new Size();

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




        Imgproc.erode(workingMat, blurredMat,kernel);
        Imgproc.dilate(blurredMat, blurredMat,kernel);

        getRedMask(workingMat);
        getBlueMask(workingMat);

        List<MatOfPoint> contoursRed = new ArrayList<>();

        Imgproc.findContours(maskRed, contoursRed, hiarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Rect chosenRedRect = null;
        double chosenRedScore = Integer.MAX_VALUE;

        MatOfPoint2f approxCurve = new MatOfPoint2f();

        for(MatOfPoint c : contoursRed) {
            MatOfPoint2f contour2f = new MatOfPoint2f(c.toArray());

            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint(approxCurve.toArray());

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(points);

            // You can find this by printing the area of each found rect, then looking and finding what u deem to be perfect.
            // Run this with the bot, on a balance board, with jewels in their desired location. Since jewels should mostly be
            // in the same position, this hack could work nicely.


            double area = Imgproc.contourArea(c);
            double areaDiffrence = Math.abs(perfectArea - area);
            double areaWeight = 0.1; // Since we're dealing with 100's of pixels


            // Just declaring vars to make my life eassy
            double x = rect.x;
            double y = rect.y;
            double w = rect.width;
            double h = rect.height;
            Point centerPoint = new Point(x + ( w/2), y + (h/2));

            double cubeRatio = Math.max(Math.abs(h/w), Math.abs(w/h)); // Get the ratio. We use max in case h and w get swapped??? it happens when u account for rotation
            double ratioDiffrence = Math.abs(1- cubeRatio);
            double ratioWeight = 10; // Since most of the time the area diffrence is a decimal place

            double finalDiffrence = (ratioDiffrence * ratioWeight) + (areaDiffrence * areaWeight);


            // Optional to ALWAYS return a result.
            if(chosenRedRect == null){
                chosenRedRect = rect;
                chosenRedScore = finalDiffrence;
            }

            // Update the chosen rect if the diffrence is lower then the curreny chosen
            // Also can add a condition for min diffrence to filter out VERY wrong answers
            // Think of diffrence as score. 0 = perfect
            if(finalDiffrence < chosenRedScore){
                chosenRedScore = finalDiffrence;
                chosenRedRect = rect;
            }

        }

        List<MatOfPoint> contoursBlue = new ArrayList<>();

        Imgproc.findContours(maskBlue, contoursBlue,hiarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Rect chosenBlueRect = null;
        double chosenBlueScore = Integer.MAX_VALUE;

        for(MatOfPoint c : contoursBlue) {
            MatOfPoint2f contour2f = new MatOfPoint2f(c.toArray());

            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint(approxCurve.toArray());

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(points);

            // You can find this by printing the area of each found rect, then looking and finding what u deem to be perfect.
            // Run this with the bot, on a balance board, with jewels in their desired location. Since jewels should mostly be
            // in the same position, this hack could work nicely.


            double area = Imgproc.contourArea(c);
            double areaDiffrence = Math.abs(perfectArea - area);
            double areaWeight = 0.1; // Since we're dealing with 100's of pixels


            // Just declaring vars to make my life eassy
            double x = rect.x;
            double y = rect.y;
            double w = rect.width;
            double h = rect.height;
            Point centerPoint = new Point(x + ( w/2), y + (h/2));

            double cubeRatio = Math.max(Math.abs(h/w), Math.abs(w/h)); // Get the ratio. We use max in case h and w get swapped??? it happens when u account for rotation
            double ratioDiffrence = Math.abs(1- cubeRatio);
            double ratioWeight = 10; // Since most of the time the area diffrence is a decimal place

            double finalDiffrence = (ratioDiffrence * ratioWeight) + (areaDiffrence * areaWeight);


            // Optional to ALWAYS return a result.
            if(chosenBlueRect == null){
                chosenBlueRect = rect;
                chosenBlueScore = finalDiffrence;
            }

            // Update the chosen rect if the diffrence is lower then the curreny chosen
            // Also can add a condition for min diffrence to filter out VERY wrong answers
            // Think of diffrence as score. 0 = perfect
            if(finalDiffrence < chosenBlueScore){
                chosenBlueScore = finalDiffrence;
                chosenBlueRect = rect;
            }

        }

        if(chosenRedRect != null){
            Imgproc.rectangle(workingMat,
                    new Point(chosenRedRect.x, chosenRedRect.y),
                    new Point(chosenRedRect.x + chosenRedRect.width, chosenRedRect.y + chosenRedRect.height),
                    new Scalar(255, 0, 0), 2);

            Imgproc.putText(workingMat,
                    "Red: " + chosenRedScore,
                    new Point(chosenRedRect.x - 5, chosenRedRect.y - 10),
                    Core.FONT_HERSHEY_PLAIN,
                    2,
                    new Scalar(255, 0, 0),
                    2);
        }

        if(chosenBlueRect != null){
            Imgproc.rectangle(workingMat,
                    new Point(chosenBlueRect.x, chosenBlueRect.y),
                    new Point(chosenBlueRect.x + chosenBlueRect.width, chosenBlueRect.y + chosenBlueRect.height),
                    new Scalar(0, 0, 255), 2);

            Imgproc.putText(workingMat,
                    "Blue: " + chosenRedScore,
                    new Point(chosenBlueRect.x - 5, chosenBlueRect.y - 10),
                    Core.FONT_HERSHEY_PLAIN,
                    2,
                    new Scalar(0, 0, 255),
                    2);
        }

        if(rotateMat){
            Mat tempAfter = workingMat.t();
            Core.flip(tempAfter, workingMat, 0); //mRgba.t() is the transpose
            tempAfter.release();
        }

        Imgproc.resize(workingMat, workingMat, initSize);
        Imgproc.resize(maskBlue, maskBlue, initSize);

        Imgproc.putText(workingMat,"DogeCV JewelV1: " + newSize.toString() + " - " + speed.toString() + " - " + detectionMode.toString() ,new Point(5,15),0,0.6,new Scalar(0,255,255),2);
        return workingMat;
    }

    public void getRedMask(Mat input){
        Mat convrted = new Mat();
        input.copyTo(convrted);
        Imgproc.cvtColor(convrted, convrted, Imgproc.COLOR_RGB2Lab);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        List<Mat> channels = new ArrayList<Mat>();
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), maskRed, 164.0, 255, Imgproc.THRESH_BINARY);
        convrted.release();
    }

    public Mat getBlueMask(Mat input){
        Mat convrted = new Mat();

        input.copyTo(convrted);
        Imgproc.cvtColor(convrted, convrted, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);

        Scalar lower = new Scalar(90, 135, 25);
        Scalar upper = new Scalar(130, 250, 150);



        Core.inRange(convrted,lower,upper,maskBlue);
        convrted.release();
        return maskBlue;
    }

    public JewelOrder getOrder() {
        return order;
    }
}
