package com.qualcomm.ftcrobotcontroller;

/**
 * Created by Victo on 12/31/2017.
 */

public class FieldPositonData {
    public enum FieldPostion {
        BLUE_TOP,
        BLUE_BOTTOM,
        RED_TOP,
        RED_BOTTOM
    }
    public static FieldPostion fieldPostion = FieldPostion.RED_BOTTOM;
    public static int speed = 2; // 0 - 4
    public static boolean dogeCVCrypto = true;

    // This is a total hack for rn. Sad to. I was doing everything so good.
}
