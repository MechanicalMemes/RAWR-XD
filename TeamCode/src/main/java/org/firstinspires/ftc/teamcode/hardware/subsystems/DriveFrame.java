package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 1/17/2018.
 */

public class DriveFrame {
    public DcMotor driveLeftFront;
    public DcMotor driveLeftRear;
    public DcMotor driveRightFront;
    public DcMotor driveRightRear;

    public List<DcMotor> allMotors = new ArrayList<>();

    private Telemetry telemetry;

    public DriveFrame(HardwareMap hwd, Telemetry tel, String motorNames[], boolean reverse){
        driveLeftFront  = hwd.dcMotor.get(motorNames[0]);
        driveLeftRear   = hwd.dcMotor.get(motorNames[1]);
        driveRightFront = hwd.dcMotor.get(motorNames[2]);
        driveRightRear  = hwd.dcMotor.get(motorNames[3]);

        if(!reverse){
            driveRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            driveRightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        }else{
            driveLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
            driveLeftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        telemetry = tel;

        setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean isMotorBusy(){
        return driveLeftFront.isBusy() || driveLeftRear.isBusy() || driveRightFront.isBusy() || driveRightRear.isBusy();
    }

    public void setMotorModes(DcMotor.RunMode mode){
        for (DcMotor _motor : allMotors){
            _motor.setMode(mode);
        }
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior mode){
        for (DcMotor _motor : allMotors){
            _motor.setZeroPowerBehavior(mode);
        }
    }

    public void setLeftPower(double power){
        driveLeftFront.setPower(power);
        driveLeftRear.setPower(power);
    }

    public void setRightPower(double power){
        driveRightFront.setPower(power);
        driveRightRear.setPower(power);
    }

    public void setLeftTarget(int target){
        driveLeftFront.setTargetPosition(target);
        driveLeftRear.setTargetPosition(target);
    }

    public void setRightTarget(int target){
        driveRightFront.setTargetPosition(target);
        driveRightRear.setTargetPosition(target);
    }


}
