package org.firstinspires.ftc.teamcode.testing.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 1/4/2018.
 */
@Autonomous(name="Concept Phone", group="RAWRXD_CONC")
@Disabled
public class RAWRXD_Conc_PhoneServo extends LinearOpMode{
    RAWRXDBot bot;
    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXDBot(hardwareMap, this, telemetry);
        bot.Init();

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a){
                bot.SetPhoneFront();
            }
            if(gamepad1.b){
                bot.SetPhoneOutside();
            }

            if(gamepad1.x){
                bot.SetPhonePicto();
            }
        }
    }
}
