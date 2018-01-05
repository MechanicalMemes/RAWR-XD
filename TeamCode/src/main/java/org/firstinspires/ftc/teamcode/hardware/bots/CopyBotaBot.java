package org.firstinspires.ftc.teamcode.hardware.bots;

import android.util.MutableBoolean;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Alex on 11/3/2017.
 */

public class CopyBotaBot {
    private HardwareMap hardwareMap;

    //DRIVE RELATED VARS
    private String  Drive_FrontLeft_Name = "fl";
    public DcMotor Drive_FrontLeft_Motor = null;
    private DcMotorSimple.Direction Drive_FrontLeft_Direction = DcMotorSimple.Direction.REVERSE;

    private String  Drive_FrontRight_Name = "fr";
    public DcMotor Drive_FrontRight_Motor = null;
    private DcMotorSimple.Direction Drive_FrontRight_Direction = DcMotorSimple.Direction.FORWARD;

    private String  Drive_RearLeft_Name = "rl";
    public DcMotor Drive_RearLeft_Motor = null;
    private DcMotorSimple.Direction Drive_RearLeft_Direction = DcMotorSimple.Direction.REVERSE;

    private String  Drive_RearRight_Name = "rr";
    public DcMotor Drive_RearRight_Motor = null;
    private DcMotorSimple.Direction Drive_RearRight_Direction = DcMotorSimple.Direction.FORWARD;

    private String Lift_Name = "lift";
    public DcMotor Lift_Motor = null;
    private DcMotorSimple.Direction Lift_Direction = DcMotorSimple.Direction.FORWARD;


    private DcMotor.ZeroPowerBehavior DriveZeroPower = DcMotor.ZeroPowerBehavior.BRAKE;


    private String Left_Grab_Name  = "lg";
    private Servo  Left_Grab_Servo = null;

    private String Right_Grab_Name  = "rg";
    private Servo  Right_Grab_Servo = null;


    private double LEFT_GRAB_OPEN = 0.5;
    private double LEFT_GRAB_MIDDLE = 0.7;
    private double LEFT_GRAB_CLOSE = 0.9;

    private double LEFT2_GRAB_OPEN = 0.7;
    private double LEFT2_GRAB_MIDDLE = 0.5;
    private double LEFT2_GRAB_CLOSE = 0.3;


    private double RIGHT_GRAB_OPEN = 0.7;
    private double RIGHT_GRAB_MIDDLE =  0.5;
    private double RIGHT_GRAB_CLOSE = 0.3 ;

    private double RIGHT2_GRAB_OPEN = 0.5;
    private double RIGHT2_GRAB_MIDDLE = 0.7;
    private double RIGHT2_GRAB_CLOSE = 0.9;

    private String Left2_Grab_Name  = "lg2";
    private Servo  Left2_Grab_Servo = null;

    private String Right2_Grab_Name  = "rg2";
    private Servo  Right2_Grab_Servo = null;





    private String Help_Name = "help";
    public DcMotor Help_Motor = null;
    public DcMotor.Direction Help_Direction = DcMotorSimple.Direction.FORWARD;



    public CopyBotaBot(HardwareMap _hwd){
        hardwareMap = _hwd;

    }

    public void Init(){
        Drive_FrontLeft_Motor  = hardwareMap.dcMotor.get(Drive_FrontLeft_Name );
        Drive_FrontRight_Motor = hardwareMap.dcMotor.get(Drive_FrontRight_Name);
        Drive_RearLeft_Motor   = hardwareMap.dcMotor.get(Drive_RearLeft_Name  );
        Drive_RearRight_Motor  = hardwareMap.dcMotor.get(Drive_RearRight_Name );

        Lift_Motor = hardwareMap.dcMotor.get(Lift_Name);

        Left_Grab_Servo = hardwareMap.servo.get(Left_Grab_Name);
        Right_Grab_Servo = hardwareMap.servo.get(Right_Grab_Name);
        Left2_Grab_Servo = hardwareMap.servo.get(Left2_Grab_Name);
        Right2_Grab_Servo = hardwareMap.servo.get(Right2_Grab_Name);

        Drive_FrontLeft_Motor.setDirection (Drive_FrontLeft_Direction);
        Drive_FrontRight_Motor.setDirection(Drive_FrontRight_Direction);
        Drive_RearLeft_Motor.setDirection  (Drive_RearLeft_Direction);
        Drive_RearRight_Motor.setDirection (Drive_RearRight_Direction);

        Lift_Motor.setDirection(Lift_Direction);

        Drive_FrontLeft_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_FrontRight_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_RearLeft_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_RearRight_Motor.setZeroPowerBehavior(DriveZeroPower);

        Help_Motor = hardwareMap.dcMotor.get(Help_Name);
        Help_Motor.setDirection(Help_Direction);
    }

    public void Drive(double XValue, double YValue, double rotationInput,  double Sensitivity){
        final double x = Math.pow(XValue, 3.0);
        final double y = -Math.pow(YValue, 3.0);
        final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        double direction = Math.atan2(x, y);
        double robotAngle = direction + Math.PI / 4;

        final double rotation = -Math.pow(rotationInput, 3.0);

        final double v1 = speed * Math.cos(robotAngle) + rotation;
        final double v2 = speed * Math.sin(robotAngle) - rotation;
        final double v3 = speed * Math.sin(robotAngle) + rotation;
        final double v4 = speed * Math.cos(robotAngle) - rotation;

        Drive_FrontLeft_Motor.setPower(v1 * Sensitivity);
        Drive_FrontRight_Motor.setPower(v2 * Sensitivity);
        Drive_RearLeft_Motor.setPower(v3 * Sensitivity);
        Drive_RearRight_Motor.setPower(v4 * Sensitivity);
    }

    public void LiftOverride(double val){
        Lift_Motor.setPower(val);
    }

    public void OpenGrab(){
        Left_Grab_Servo.setPosition(LEFT_GRAB_OPEN);
        Right_Grab_Servo.setPosition(RIGHT_GRAB_OPEN);
        Left2_Grab_Servo.setPosition(LEFT2_GRAB_OPEN);
        Right2_Grab_Servo.setPosition(RIGHT2_GRAB_OPEN);
    }

    public void MiddleGrab(){
        Left_Grab_Servo.setPosition(LEFT_GRAB_MIDDLE);
        Right_Grab_Servo.setPosition(RIGHT_GRAB_MIDDLE);
        Left2_Grab_Servo.setPosition(LEFT2_GRAB_MIDDLE);
        Right2_Grab_Servo.setPosition(RIGHT2_GRAB_MIDDLE);
    }

    public void CloseGrab(){
        Left_Grab_Servo.setPosition(LEFT_GRAB_CLOSE);
        Right_Grab_Servo.setPosition(RIGHT_GRAB_CLOSE);
        Left2_Grab_Servo.setPosition(LEFT2_GRAB_CLOSE);
        Right2_Grab_Servo.setPosition(RIGHT2_GRAB_CLOSE);
    }

}
