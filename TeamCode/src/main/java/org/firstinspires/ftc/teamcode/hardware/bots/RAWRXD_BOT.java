package org.firstinspires.ftc.teamcode.hardware.bots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Alex on 11/3/2017.
 */

public class RAWRXD_BOT {
    private HardwareMap hardwareMap;


    public BNO055IMU imu = null;

    //
    //DRIVE RELATED VARS
    //

    //Left Motor
    private String Drive_Left_Name = "dl1";
    public DcMotor Drive_Left_Motor = null;
    private DcMotorSimple.Direction Drive_Left_Direction = DcMotorSimple.Direction.FORWARD;

    private String Drive_Left_Name2 = "dl2";
    public DcMotor Drive_Left_Motor2 = null;

    //Right Motor
    private String Drive_Right_Name = "dr1";
    public DcMotor Drive_Right_Motor = null;
    private DcMotorSimple.Direction Drive_Right_Direction = DcMotorSimple.Direction.REVERSE;

    private String Drive_Right_Name2 = "dr2";
    public DcMotor Drive_Right_Motor2 = null;


    //Global DriveManual Settings
    private DcMotor.ZeroPowerBehavior DriveZeroPower = DcMotor.ZeroPowerBehavior.BRAKE;


    private String Phone_Name = "phone";
    public Servo Phone_Servo = null;
    private double PHONE_POS_INSIDE = 0.4;
    private double PHONE_POS_FRONT = 0.5;
    private double PHONE_POST_PICTO = 0.8;
    private double PHONE_POST_OUTSIDE = 1;

    private String Lift1_Name = "lift1";
    public DcMotor Lift1_Motor = null;
    public DcMotor.Direction Lift1_Direction = DcMotorSimple.Direction.FORWARD;

    private String Lift2_Name = "lift2";
    public DcMotor Lift2_Motor = null;
    public DcMotor.Direction Lift2_Direction = DcMotorSimple.Direction.REVERSE;

    private String Grab_Left_Name = "lg";
    public Servo Grab_Left_Servo = null;
    public final double GRAB_LEFT_CLOSED = 1;
    public final double GRAB_LEFT_OPEN   = 0.7;

    private String Grab2_Left_Name = "lg2";
    public Servo Grab2_Left_Servo = null;
    public final double GRAB2_LEFT_CLOSED = 0;
    public final double GRAB2_LEFT_OPEN   = 0.3;

    private String Grab_Right_Name = "rg";
    public Servo Grab_Right_Servo = null;
    public final double GRAB_RIGHT_CLOSED = 1;
    public final double GRAB_RIGHT_OPEN   = 0.7;

    private String Grab2_Right_Name = "rg2";
    public Servo Grab2_Right_Servo = null;
    public final double GRAB2_RIGHT_CLOSED = 0;
    public final double GRAB2_RIGHT_OPEN   = 0.3;


    static final double     HEADING_THRESHOLD       = 15 ;      // As tight as we can make it with an integer gyro
    static final double     P_TURN_COEFF            = 0.05;     // Larger is more responsive, but also less stable
    static final double     P_DRIVE_COEFF           = 0.05;     // Larger is more responsive, but also less stable

    private OpMode opMode;

    public RAWRXD_BOT(HardwareMap _hwd, OpMode parent){
        hardwareMap = _hwd;
        opMode = parent;

    }

    public void Init(){

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu.initialize(parameters);


        Drive_Left_Motor  = hardwareMap.dcMotor.get(Drive_Left_Name);
        Drive_Right_Motor = hardwareMap.dcMotor.get(Drive_Right_Name);

        Drive_Left_Motor2  = hardwareMap.dcMotor.get(Drive_Left_Name2);
        Drive_Right_Motor2 = hardwareMap.dcMotor.get(Drive_Right_Name2);

        Drive_Left_Motor.setDirection(Drive_Left_Direction);
        Drive_Right_Motor.setDirection(Drive_Right_Direction);

        Drive_Left_Motor2.setDirection(Drive_Left_Direction);
        Drive_Right_Motor2.setDirection(Drive_Right_Direction);

        Drive_Left_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_Right_Motor.setZeroPowerBehavior(DriveZeroPower);

        Drive_Left_Motor2.setZeroPowerBehavior(DriveZeroPower);
        Drive_Right_Motor2.setZeroPowerBehavior(DriveZeroPower);

        SetAllMotorsToMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Phone_Servo = hardwareMap.servo.get(Phone_Name);

        Lift1_Motor = hardwareMap.dcMotor.get(Lift1_Name);
        Lift2_Motor = hardwareMap.dcMotor.get(Lift2_Name);
        Lift1_Motor.setDirection(Lift1_Direction);
        Lift2_Motor.setDirection(Lift2_Direction);

        Grab_Left_Servo = hardwareMap.servo.get(Grab_Left_Name);
        Grab2_Left_Servo = hardwareMap.servo.get(Grab2_Left_Name);
        Grab_Right_Servo = hardwareMap.servo.get(Grab_Right_Name);
        Grab2_Right_Servo = hardwareMap.servo.get(Grab2_Right_Name);


        while(!imu.isGyroCalibrated()){
            opMode.telemetry.addData("IMU Calibrating", imu.getSystemStatus());
            opMode.telemetry.update();
        }

        SetAllMotorsToMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void SetAllMotorsToMode(DcMotor.RunMode mode){
        Drive_Left_Motor.setMode(mode);
        Drive_Left_Motor2.setMode(mode);
        Drive_Right_Motor.setMode(mode);
        Drive_Right_Motor2.setMode(mode);
    }

    public boolean AreMotorsBusy(){
        return (Drive_Left_Motor.isBusy() && Drive_Left_Motor2.isBusy() && Drive_Right_Motor.isBusy() && Drive_Right_Motor2.isBusy());
    }

    public void DriveManual(double LeftVal, double RightVal, double Sensitivity) {
        Drive_Left_Motor.setPower(LeftVal * Sensitivity);
        Drive_Right_Motor.setPower(RightVal * Sensitivity);

        Drive_Left_Motor2.setPower(LeftVal * Sensitivity);
        Drive_Right_Motor2.setPower(RightVal * Sensitivity);
    }

    public void EncoderDrive(int LeftVal, int RightVal, double speed){
        SetAllMotorsToMode(DcMotor.RunMode.RUN_TO_POSITION);

        Drive_Left_Motor.setTargetPosition(-LeftVal);
        Drive_Left_Motor2.setTargetPosition(-LeftVal);
        Drive_Right_Motor.setTargetPosition(-RightVal);
        Drive_Right_Motor2.setTargetPosition(-RightVal);

        Drive_Left_Motor.setPower(speed);
        Drive_Left_Motor2.setPower(speed);
        Drive_Right_Motor.setPower(speed);
        Drive_Right_Motor2.setPower(speed);

        while(AreMotorsBusy()){}

        SetAllMotorsToMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Phone Settings

    public void SetPhoneInside(){
        Phone_Servo.setPosition(PHONE_POS_INSIDE);
    }

    public void SetPhoneFront(){
        Phone_Servo.setPosition(PHONE_POS_FRONT);
    }

    public void SetPhonePicto(){
        Phone_Servo.setPosition(PHONE_POST_PICTO);
    }
    public void SetPhoneOutside(){
        Phone_Servo.setPosition(PHONE_POST_OUTSIDE);
    }

    // Lift Settings

    public void LiftPower(double power){
        Lift1_Motor.setPower(power);
        Lift2_Motor.setPower(power);
    }

    // Grabbers

    public void CloseGrab(){
        Grab_Right_Servo.setPosition(GRAB_RIGHT_CLOSED);
        Grab_Left_Servo.setPosition(GRAB_LEFT_CLOSED);

        Grab2_Right_Servo.setPosition(GRAB2_RIGHT_CLOSED);
        Grab2_Left_Servo.setPosition(GRAB2_LEFT_CLOSED);
    }

    public void OpenGrab(){
        Grab_Right_Servo.setPosition(GRAB_RIGHT_OPEN);
        Grab_Left_Servo.setPosition(GRAB_LEFT_OPEN);
        Grab2_Right_Servo.setPosition(GRAB2_RIGHT_OPEN);
        Grab2_Left_Servo.setPosition(GRAB2_LEFT_OPEN);
    }

}
