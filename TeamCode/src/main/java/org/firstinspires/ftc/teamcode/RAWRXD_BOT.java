package org.firstinspires.ftc.teamcode;

import android.util.MutableBoolean;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Alex on 11/3/2017.
 */

public class RAWRXD_BOT {
    private HardwareMap hardwareMap;

    private String  Drive_FrontLeft_Name = "fl";
    private DcMotor Drive_FrontLeft_Motor = null;
    private DcMotorSimple.Direction Drive_FrontLeft_Direction = DcMotorSimple.Direction.REVERSE;

    private String  Drive_FrontRight_Name = "fr";
    private DcMotor Drive_FrontRight_Motor = null;
    private DcMotorSimple.Direction Drive_FrontRight_Direction = DcMotorSimple.Direction.FORWARD;

    private String  Drive_RearLeft_Name = "rf";
    private DcMotor Drive_RearLeft_Motor = null;
    private DcMotorSimple.Direction Drive_RearLeft_Direction = DcMotorSimple.Direction.REVERSE;

    private String  Drive_RearRight_Name = "rr";
    private DcMotor Drive_RearRight_Motor = null;
    private DcMotorSimple.Direction Drive_RearRight_Direction = DcMotorSimple.Direction.FORWARD;

    private DcMotor.ZeroPowerBehavior DriveZeroPower = DcMotor.ZeroPowerBehavior.BRAKE;

    private String Outake_Name  = "out";
    private String Outake_Motor = null;

    public RAWRXD_BOT(HardwareMap _hwd){
        hardwareMap = _hwd;

    }

    public void Init(){
        Drive_FrontLeft_Motor  = hardwareMap.dcMotor.get(Drive_FrontLeft_Name );
        Drive_FrontRight_Motor = hardwareMap.dcMotor.get(Drive_FrontRight_Name);
        Drive_RearLeft_Motor   = hardwareMap.dcMotor.get(Drive_RearLeft_Name  );
        Drive_RearRight_Motor  = hardwareMap.dcMotor.get(Drive_RearRight_Name );

        Drive_FrontLeft_Motor.setDirection (Drive_FrontLeft_Direction);
        Drive_FrontRight_Motor.setDirection(Drive_FrontRight_Direction);
        Drive_RearLeft_Motor.setDirection  (Drive_RearLeft_Direction);
        Drive_RearRight_Motor.setDirection (Drive_RearRight_Direction);

        Drive_FrontLeft_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_FrontRight_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_RearLeft_Motor.setZeroPowerBehavior(DriveZeroPower);
        Drive_RearRight_Motor.setZeroPowerBehavior(DriveZeroPower);
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
}
