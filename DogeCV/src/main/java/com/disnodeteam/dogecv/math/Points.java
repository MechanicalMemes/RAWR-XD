package com.disnodeteam.dogecv.math;

import org.opencv.core.Point;
import org.opencv.core.Size;

public class Points {

    public static boolean inBounds(Point point, Size size) {
        if(point.x < size.width - 1 && point.x >= 0 && point.y < size.height-1 && point.y >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}