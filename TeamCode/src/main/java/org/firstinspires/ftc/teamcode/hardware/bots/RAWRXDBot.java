package org.firstinspires.ftc.teamcode.hardware.bots;

import org.firstinspires.ftc.teamcode.hardware.sensors.IMU;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DriveFrame;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Grabbers;

/**
 * Created by Victo on 1/17/2018.
 */

public class RAWRXDBot extends DogeBot {

    private String leftDriveFront  = "dl1";
    private String leftDriveRear   = "dl2";
    private String rightDriveFront = "dr1";
    private String rightDriveRear  = "dr2";

    private String grabberTopLeft    = "gl1";
    private String grabberBottomLeft = "gl2";
    private String grabberTopRight   = "gr1";
    private String grabberBottomRight= "gr2";

    private double GRAB_TOP_LEFT_OPEN = 0;
    private double GRAB_BOTTOM_LEFT_OPEN = 0;
    private double GRAB_TOP_RIGHT_OPEN = 0;
    private double GRAB_BOTTOM_RIGHT_OPEN = 0;

    private double GRAB_TOP_LEFT_CLOSE = 0;
    private double GRAB_BOTTOM_LEFT_CLOSE = 0;
    private double GRAB_TOP_RIGHT_CLOSE = 0;
    private double GRAB_BOTTOM_RIGHT_CLOSE = 0;

    private double GRAB_TOP_LEFT_SOFTOPEN = 0;
    private double GRAB_BOTTOM_LEFT_SOFTOPEN = 0;
    private double GRAB_TOP_RIGHT_SOFTOPEN = 0;
    private double GRAB_BOTTOM_RIGHT_SOFTOPEN = 0;


    public RAWRXDBot() {
        super();
        P = 0.1;
        I = 0.1;
        D = 0.1;
        driveFrame = new DriveFrame(hardwareMap, null, new String[]{"dl1", "dl2", "dl3", "dl4"},false);
        grabbers = new Grabbers(hardwareMap, new String[]{"lg1", "lg2", "rg1", "rg2"},new double[]{0,1,0,1}, new double[]{0.5,0.5,0.5,0.5}, new double[]{1,0,1,0});
        navigationHardware = new IMU(hardwareMap, "imu");
    }

    @Override
    public void Init() {

    }


}
