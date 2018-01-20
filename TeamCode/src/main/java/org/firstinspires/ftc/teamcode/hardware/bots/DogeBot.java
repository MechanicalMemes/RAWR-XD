package org.firstinspires.ftc.teamcode.hardware.bots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.subsystems.DriveFrame;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Grabbers;
import org.firstinspires.ftc.teamcode.hardware.subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Lift;
import org.firstinspires.ftc.teamcode.hardware.subsystems.NavigationHardware;
import org.firstinspires.ftc.teamcode.hardware.subsystems.PhoneServo;

/**
 * Created by Victo on 1/17/2018.
 */


public abstract class DogeBot {
    public HardwareMap hardwareMap;

    public double P = 0.1;
    public double I = 0.1;
    public double D = 0.1;
    public double PID_THRESH = 1;


    public double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    public double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    public double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    public double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public DriveFrame driveFrame = null;
    public Grabbers grabbers = null;
    public NavigationHardware navigationHardware = null;
    public JewelArm jewelArm = null;
    public Lift lift = null;
    public PhoneServo phoneServo = null;

    public DogeBot(HardwareMap hwd){
        hardwareMap = hwd;
    }

    public abstract void Init();

    public int convertEncoder(double inputInches){
        return (int)(inputInches * COUNTS_PER_INCH);
    }



}
