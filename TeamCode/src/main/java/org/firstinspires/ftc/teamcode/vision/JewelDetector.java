package org.firstinspires.ftc.teamcode.vision;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Mat;

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

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        return null;
    }

    public JewelOrder getOrder() {
        return order;
    }
}
