package org.firstinspires.ftc.teamcode.hardware.bots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.hardware.sensors.IMU;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DriveFrame;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Grabbers;
import org.firstinspires.ftc.teamcode.hardware.subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Lift;
import org.firstinspires.ftc.teamcode.hardware.subsystems.PhoneServo;

/**
 * Created by Victo on 1/17/2018.
 */

public class RAWRXDBot extends DogeBot {

    private String leftDriveFront  = "dl1";
    private String leftDriveRear   = "dl2";
    private String rightDriveFront = "dr1";
    private String rightDriveRear  = "dr2";
    private String driveMotorNames[] = new String[]{leftDriveFront, leftDriveRear, rightDriveFront, rightDriveRear};



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



    private String jewelArm    = "jewel";
    private double JEWEL_UP = 0;
    private double JEWEL_DOWN = 1.0;

    private String

    public RAWRXDBot(HardwareMap hwd) {
        super(hwd);
        P = 0.5;
        I = 0;
        D = 0.9;
        PID_THRESH = 0.5;

        COUNTS_PER_MOTOR_REV =  537.6;

        driveFrame = new DriveFrame(hardwareMap, null, driveMotorNames,false);
        grabbers = new Grabbers(hardwareMap, new String[]{"lg1", "lg2", "rg1", "rg2"},new double[]{0,1,0,1}, new double[]{0.5,0.5,0.5,0.5}, new double[]{1,0,1,0});
        navigationHardware = new IMU(hardwareMap, "imu");
        jewelArm = new JewelArm(hardwareMap, "jewel", 0,1);
        lift = new Lift(hardwareMap, new String[]{"lift1","lift2"}, 0,2500);
        phoneServo = new PhoneServo(hardwareMap, "phone", new double[]{0, 0.8, 1.0});
    }

    @Override
    public void Init() {

    }


}
