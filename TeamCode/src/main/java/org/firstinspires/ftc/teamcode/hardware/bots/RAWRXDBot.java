package org.firstinspires.ftc.teamcode.hardware.bots;

import org.firstinspires.ftc.teamcode.hardware.sensors.IMU;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DriveFrame;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Grabbers;

/**
 * Created by Victo on 1/17/2018.
 */

public class RAWRXDBot extends DogeBot {

    private String leftDriveFront;
    private String leftDriveRear;
    private String rightDriveFront;
    private String rightDriveRear;

    private String grabberTopLeft;
    private String grabberBottomLeft;
    private String grabberTopRight;
    private String grabberBottomRight;

    //private double grabberTopLeftOpen;
    //private double grabberTopLeftOpen;
   // private double grabberTopLeftOpen;
    //private double grabberTopLeftOpen;


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
