package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.Controller;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by jcm12 on 1/19/2018.
 */
@TeleOp(name="Main Teleop", group="COMP")
public class MainTeleop extends DogeAutoOpMode {

    private double speed = 1.0;
    private Controller controller1;
    private Controller controller2;
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        controller1 = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);
        while(opModeIsActive()){
            controller1.Update();
            controller2.Update();



            if(gamepad1.left_trigger > 0.1 || gamepad1.left_bumper){
                speed = 0.2;
            } else if (gamepad1.right_bumper || gamepad1.right_trigger > 0.1) {
                speed = 0.5;
            }else{
                speed = 1.0;
            }


            bot.driveFrame.setLeftPower(gamepad1.left_stick_y * speed);
            bot.driveFrame.setRightPower(gamepad1.right_stick_y  * speed);

            if(controller2.YState == Controller.ButtonState.JUST_PRESSED){
                bot.jewelArm.Up();
            }

            if(controller2.AState == Controller.ButtonState.JUST_PRESSED){
                bot.grabbers.closeGrabbers();
            }

            if(controller2.BState == Controller.ButtonState.JUST_PRESSED){
                bot.grabbers.openGrabbers();
            }

            if(controller2.XState == Controller.ButtonState.JUST_PRESSED){
                bot.grabbers.softOpenGrabbers();
            }


            bot.lift.liftInput(gamepad2.left_stick_y + gamepad2.right_stick_y, false);

            if(controller2.DPadUp == Controller.ButtonState.JUST_PRESSED){
                bot.lift.resetLift();
            }

            double rawLiftInput = -gamepad2.left_trigger;
            rawLiftInput += gamepad2.right_trigger;
            bot.lift.liftInput(rawLiftInput, true);

        }

    }
}
