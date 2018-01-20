package org.firstinspires.ftc.teamcode.hardware.commands;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;
import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;
import org.firstinspires.ftc.teamcode.lib.control.PIDController;

/**
 * Created by Victo on 1/17/2018.
 */

public class CommandGyroTurn extends CommandBase {
    private double angle = 0;
    private double speed = 0.5;

    private PIDController pidController;

    public CommandGyroTurn(DogeAutoOpMode opMode, double speed, double angle) {
        super(opMode);

        this.speed = speed;
        this.angle = angle;

        pidController = new PIDController(bot.P, bot.I, bot.D);
    }

    @Override
    public void Run() {
        angle = -angle;
        // keep looping while we are still active, and not on heading.
        while (opMode.opModeIsActive() && !onHeading(speed, angle, (int)bot.navigationHardware.getHeading())  && !opMode.isStopRequested()){
            // Update telemetry & Allow time for other processes to run.

        }
    }

    @Override
    public void Stop() {

    }

    // Helper Methods

    boolean onHeading(double speed, double target, int heading) {

        double   steer ;
        boolean  onTarget = false ;
        double leftSpeed;
        double rightSpeed;

        // determine turn power based on +/- error

        if (Math.abs(heading) <= bot.PID_THRESH) {
            steer = 0.0;
            leftSpeed  = 0.0;
            rightSpeed = 0.0;
            onTarget = true;
        }
        else {
            double finalError = pidController.run((int)angle,heading);
            if(opMode != null){
                opMode.telemetry.addData("Final error" ,finalError);
                opMode.telemetry.addData("Raw Angle" ,angle);
                opMode.telemetry.addData("Raw Angle" ,target);
                opMode.telemetry.update();
            }
            steer = Range.clip(finalError, -1, 1);
            rightSpeed  = speed * steer;
            leftSpeed   = -rightSpeed;
        }

        bot.driveFrame.setLeftPower(leftSpeed);
        bot.driveFrame.setRightPower(rightSpeed);



        return onTarget;
    }


}
