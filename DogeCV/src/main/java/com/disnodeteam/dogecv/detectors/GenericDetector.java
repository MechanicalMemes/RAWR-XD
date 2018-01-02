package com.disnodeteam.dogecv.detectors;


import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.OpenCVPipeline;
import com.disnodeteam.dogecv.filters.DogeCVColorFilter;
import com.disnodeteam.dogecv.filters.HSVColorFilter;
import com.disnodeteam.dogecv.filters.LeviColorFilter;

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

public class GenericDetector extends OpenCVPipeline {

    public enum GenericDetectionMode {
        PERFECT_AREA, MAX_AREA
    }

    public enum GenericDetectionSpeed {
        VERY_FAST, FAST, BALANCED, SLOW, VERY_SLOW
    }

    public enum GenericFilterMode {
        HSV_RANGE, LEVI_BLUE, LEVI_RED
    }

    public GenericDetectionMode detectionMode = GenericDetectionMode.MAX_AREA;
    public double downScaleFactor = 0.4;
    public double perfectRatio = 1;
    public boolean rotateMat = false;
    public GenericDetectionSpeed speed = GenericDetectionSpeed.BALANCED;

    public DogeCVColorFilter colorFilter = new HSVColorFilter(new Scalar(50,50,50), new Scalar(50,50,50));

    public double perfectArea = 6500;
    public double areaWeight = 0.05; // Since we're dealing with 100's of pixels
    public double minArea = 700;
    public double ratioWeight = 15; // Since most of the time the area diffrence is a decimal place
    public double maxDiffrence = 10; // Since most of the time the area diffrence is a decimal place
    public boolean debugContours = false;
    public boolean stretch = false;
    public Size stretchKernal = new Size(10,10);
    private Point resultLocation = new Point();

    private Mat workingMat = new Mat();
    private Mat blurredMat = new Mat();
    private Mat mask = new Mat();
    private Mat hiarchy = new Mat();
    private Mat structure = new Mat();

    private Size newSize = new Size();

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        Size initSize = rgba.size();
        newSize = new Size(initSize.width * downScaleFactor, initSize.height * downScaleFactor);
        rgba.copyTo(workingMat);

        Imgproc.resize(workingMat, workingMat, newSize);

        if (rotateMat) {
            Mat tempBefore = workingMat.t();

            Core.flip(tempBefore, workingMat, -1); //mRgba.t() is the transpose

            tempBefore.release();
        }

        Mat preConvert = workingMat.clone();

        colorFilter.process(preConvert,mask);

        if(stretch){
            structure = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT,stretchKernal);
            Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE,structure);
        }
        List<MatOfPoint> contours = new ArrayList<>();

        Imgproc.findContours(mask, contours, hiarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(workingMat, contours, -1, new Scalar(230, 70, 70), 2);
        Rect chosenRedRect = null;
        double chosenScore = Integer.MAX_VALUE;

        MatOfPoint2f approxCurve = new MatOfPoint2f();

        for (MatOfPoint c : contours) {
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
            double areaDiffrence = 0;

            switch (detectionMode) {
                case MAX_AREA:
                    areaDiffrence = -area * areaWeight;
                    break;
                case PERFECT_AREA:
                    areaDiffrence = Math.abs(perfectArea - area);
                    break;
            }

            // Just declaring vars to make my life eassy
            double x = rect.x;
            double y = rect.y;
            double w = rect.width;
            double h = rect.height;
            Point centerPoint = new Point(x + (w / 2), y + (h / 2));


            double cubeRatio = Math.max(Math.abs(h / w), Math.abs(w / h)); // Get the ratio. We use max in case h and w get swapped??? it happens when u account for rotation
            double ratioDiffrence = Math.abs(cubeRatio - perfectRatio);


            double finalDiffrence = (ratioDiffrence * ratioWeight) + (areaDiffrence * areaWeight);


            // Optional to ALWAYS return a result.

            // Update the chosen rect if the diffrence is lower then the curreny chosen
            // Also can add a condition for min diffrence to filter out VERY wrong answers
            // Think of diffrence as score. 0 = perfect
            if (finalDiffrence < chosenScore && finalDiffrence < maxDiffrence && area > minArea) {
                chosenScore = finalDiffrence;
                chosenRedRect = rect;
            }

            if (debugContours && area > 100) {
                Imgproc.circle(workingMat, centerPoint, 3, new Scalar(0, 255, 255), 3);
                Imgproc.putText(workingMat, "Area: " + area, centerPoint, 0, 0.5, new Scalar(0, 255, 255));
            }

        }

        if (chosenRedRect != null) {
            Imgproc.rectangle(workingMat,
                    new Point(chosenRedRect.x, chosenRedRect.y),
                    new Point(chosenRedRect.x + chosenRedRect.width, chosenRedRect.y + chosenRedRect.height),
                    new Scalar(0, 255, 0), 3);

            Imgproc.putText(workingMat,
                    "Result: " + String.format("%f2", chosenScore),
                    new Point(chosenRedRect.x - 5, chosenRedRect.y - 10),
                    Core.FONT_HERSHEY_PLAIN,
                    1.3,
                    new Scalar(0, 255, 0),
                    2);
        }
        Imgproc.resize(workingMat, workingMat, initSize);

        preConvert.release();
        Imgproc.putText(workingMat, "DogeCV Generic: " + newSize.toString() + " - " + speed.toString() + " - " + detectionMode.toString(), new Point(5, 15), 0, 1, new Scalar(0, 255, 255), 2);

        return workingMat;
    }
}