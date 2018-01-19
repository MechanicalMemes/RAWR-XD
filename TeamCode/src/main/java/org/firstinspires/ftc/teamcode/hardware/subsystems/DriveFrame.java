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
    // 0 = Front Left
    // 1 = Bottom Left
    // 2 = Front Rightg
    // 3 = Rear Right
    public DcMotor allMotors[] = new DcMotor[4];

    private Telemetry telemetry;

    public DriveFrame(HardwareMap hwd, Telemetry tel, String motorNames[], boolean reverse){
        for(int i=0;i<allMotors.length;i++){
            allMotors[i] = hwd.dcMotor.get(motorNames[i]);
        }
        if(!reverse){
            allMotors[2].setDirection(DcMotorSimple.Direction.REVERSE); // Set FrontRight
            allMotors[3].setDirection(DcMotorSimple.Direction.REVERSE); // Set BottomRight
        }else{
            allMotors[0].setDirection(DcMotorSimple.Direction.REVERSE); // Set FrontLeft
            allMotors[1].setDirection(DcMotorSimple.Direction.REVERSE); // Set FrontRight
        }

        telemetry = tel;

        setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean isMotorBusy(){
        boolean isBusy = false;
        for (DcMotor _motor : allMotors){
            if(_motor.isBusy()){
                isBusy = true;
            }
        }

        return isBusy;
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
        allMotors[0].setPower(power);
        allMotors[1].setPower(power);
    }

    public void setRightPower(double power){
        allMotors[2].setPower(power);
        allMotors[3].setPower(power);
    }

    public void setLeftTarget(int target){
        allMotors[0].setTargetPosition(target);
        allMotors[1].setTargetPosition(target);
    }

    public void setRightTarget(int target){
        allMotors[2].setTargetPosition(target);
        allMotors[3].setTargetPosition(target);
    }


}
