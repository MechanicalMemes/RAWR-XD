package org.firstinspires.ftc.teamcode.testing.debug;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/23/2018.
 */

public class PreflightCheckOp extends DogeAutoOpMode {


    private boolean driveStatus[]   = new boolean[4];
    private double drivePosDif[]   = new double[4];

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();


        waitForStart();
        telemetry.addData("Test", "Drive and Encoder Test. This is automated.");
        telemetry.addData("Status", "Starting in 3s");
        telemetry.update();

        ElapsedTime timer = new ElapsedTime();
        while(timer.seconds() < 3.0){
            telemetry.addData("Test", "Drive and Encoder Test. This is automated.");
            telemetry.addData("Status", "Starting in " + (3 - timer.seconds()) + "s");
            telemetry.update();
        }
        timer.reset();

        // Test Motor 1

        double motor1StartPos = bot.driveFrame.allMotors[0].getCurrentPosition();
        double motor1EndPos = motor1StartPos;
        bot.driveFrame.allMotors[0].setPower(1.0);
        bot.driveFrame.setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while(timer.seconds() < 3.0){
            motor1EndPos = bot.driveFrame.allMotors[0].getCurrentPosition();
            telemetry.addData("Test", "Testing Motor 1");
            telemetry.addData("Status", motor1EndPos);
            telemetry.update();
        }

        drivePosDif[0] = Math.abs(motor1StartPos - motor1EndPos);
        if(drivePosDif[0] < 5){
            driveStatus[0] = false;
            driveStatus[0] = true;
        }

        // Test Motor 2

        double motor2StartPos = bot.driveFrame.allMotors[1].getCurrentPosition();
        double motor2EndPos = motor1StartPos;
        bot.driveFrame.allMotors[1].setPower(1.0);
        bot.driveFrame.setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while(timer.seconds() < 3.0){
            motor1EndPos = bot.driveFrame.allMotors[1].getCurrentPosition();
            telemetry.addData("Test", "Testing Motor 2");
            telemetry.addData("Status", motor1EndPos);
            telemetry.update();
        }

        drivePosDif[1] = Math.abs(motor2StartPos - motor2EndPos);
        if(drivePosDif[1] < 5){
            driveStatus[1] = false;
        }else {
            driveStatus[1] = true;
        }




    }

    private void PrintStatus(){

        telemetry.addData("Drive Train", driveStatus.toString());

    }
}
