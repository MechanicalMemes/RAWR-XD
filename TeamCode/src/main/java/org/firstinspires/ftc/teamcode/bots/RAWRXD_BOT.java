package org.firstinspires.ftc.teamcode.bots;

import android.util.MutableBoolean;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Alex on 11/3/2017.
 */

public class RAWRXD_BOT {
    private HardwareMap hardwareMap;

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


    //Global Drive Settings
    private DcMotor.ZeroPowerBehavior DriveZeroPower = DcMotor.ZeroPowerBehavior.BRAKE;

    //
    //SCORING RELATED VARS
    //

    //Lift Motor
    //  private String Lift_Name = "lift";
    //  public DcMotor Lift_Motor = null;
    //  private DcMotorSimple.Direction Lift_Direction = DcMotorSimple.Direction.FORWARD;

    //Arm Motors
    //  private String Rotate_Name = "arm";
    //  public DcMotor Rotate_Motor = null;
    //  private DcMotorSimple.Direction Rotate_Direction = DcMotorSimple.Direction.FORWARD;

    //Grabber
    private String Grab_Name  = "grab";
    private Servo  Grab_Servo = null;
    private double GRAB_CLOSE = 0.3;
    private double GRAB_OPEN  = 0.5;


    public RAWRXD_BOT(HardwareMap _hwd){
        hardwareMap = _hwd;

    }

    public void Init(){

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


       // Lift_Motor   = hardwareMap.dcMotor.get(Lift_Name);
       // Rotate_Motor = hardwareMap.dcMotor.get(Rotate_Name);

       // Lift_Motor.setDirection(Lift_Direction);
       // Rotate_Motor.setDirection(Rotate_Direction);


       // Grab_Servo = hardwareMap.servo.get(Grab_Name);
    }

    public void Drive(double LeftVal, double RightVal,  double Sensitivity) {
        Drive_Left_Motor.setPower(LeftVal * Sensitivity);
        Drive_Right_Motor.setPower(RightVal * Sensitivity);

        Drive_Left_Motor2.setPower(LeftVal * Sensitivity);
        Drive_Right_Motor2.setPower(RightVal * Sensitivity);
    }
    //public void LiftOverride(double val){
    //    Lift_Motor.setPower(val);
    //}

    ///public void RotateOverride(double val){
  //      Rotate_Motor.setPower(val);
   // }


}
