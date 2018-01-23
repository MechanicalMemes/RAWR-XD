package org.firstinspires.ftc.teamcode.hardware.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;
import org.firstinspires.ftc.teamcode.lib.control.PIDController;
import org.firstinspires.ftc.teamcode.lib.logging.UniLogger;

/**
 * Created by Victo on 1/17/2018.
 */

public class CommandGyroDrive extends CommandBase {
    private double angle = 0;
    private double speed = 0.5;
    private double distance = 1000;
    private PIDController pidController;


    int     newLeftTarget1, newLeftTarget2;
    int     newRightTarget1, newRightTarget2;
    int     moveCounts;
    double  max;
    double  error;
    double  steer;
    double  leftSpeed;
    double  rightSpeed;

    public CommandGyroDrive(DogeAutoOpMode opMode, double speed, double angle, double distance) {
        super(opMode);

        this.speed = speed;
        this.angle = angle;
        this.distance = bot.convertEncoder(distance);
        pidController = new PIDController(bot.P, bot.I, bot.D);

        if(bot.driveFrame == null){
            UniLogger.Log("DOGE-AUTO","Command 'CommandGyroDrive' requires DriveTrain, but it is null");
        }

    }


    @Override
    public void Start() {
        moveCounts =(int) distance;

        newLeftTarget1 = bot.driveFrame.allMotors[0].getCurrentPosition() + moveCounts;
        newLeftTarget2 = bot.driveFrame.allMotors[1].getCurrentPosition() + moveCounts;
        newRightTarget1 = bot.driveFrame.allMotors[0].getCurrentPosition() + moveCounts;
        newRightTarget2 = bot.driveFrame.allMotors[1].getCurrentPosition() + moveCounts;

        // Set Target and Turn On RUN_TO_POSITION
        bot.driveFrame.allMotors[0].setTargetPosition(newLeftTarget1);
        bot.driveFrame.allMotors[1].setTargetPosition(newLeftTarget2);
        bot.driveFrame.allMotors[2].setTargetPosition(newRightTarget1);
        bot.driveFrame.allMotors[3].setTargetPosition(newRightTarget2);

        bot.driveFrame.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);

        // start motion.
        speed = Range.clip(Math.abs(speed), 0.0, 1.0);
        bot.driveFrame.setLeftPower(speed);
        bot.driveFrame.setRightPower(speed);
    }

    @Override
    public void Loop() {
        int heading = (int)bot.navigationHardware.getHeading();

        double finalError = pidController.run((int)angle,heading);

        steer = Range.clip(finalError, -1, 1);

        // if driving in reverse, the motor correction also needs to be reversed
        if (distance < 0)
            steer *= -1.0;

        leftSpeed = speed - steer;
        rightSpeed = speed + steer;

        // Normalize speeds if either one exceeds +/- 1.0;
        max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if (max > 1.0)
        {
            leftSpeed /= max;
            rightSpeed /= max;
        }

        bot.driveFrame.setLeftPower(leftSpeed);
        bot.driveFrame.setRightPower(rightSpeed);

        if(opMode != null){
            opMode.telemetry.addData("Final error" ,finalError);
            opMode.telemetry.addData("Raw Target" ,angle);
            opMode.telemetry.addData("Raw Angle" ,heading);

        }
    }

    @Override
    public void Stop() {
        bot.driveFrame.setLeftPower(0);
        bot.driveFrame.setRightPower(0);

        bot.driveFrame.setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public boolean IsTaskRunning() {
        return bot.driveFrame.isMotorBusy();
    }


}
