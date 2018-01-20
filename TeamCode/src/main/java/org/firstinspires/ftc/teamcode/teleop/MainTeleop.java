package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by jcm12 on 1/19/2018.
 */
@TeleOp(name="Main Teleop", group="COMP")
public class MainTeleop extends DogeAutoOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();


        while(opModeIsActive()){
            bot.driveFrame.setLeftPower(gamepad1.left_stick_y);
            bot.driveFrame.setRightPower(gamepad1.right_stick_y);
        }

    }
}
