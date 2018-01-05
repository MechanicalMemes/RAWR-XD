package org.firstinspires.ftc.teamcode.testing.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 1/4/2018.
 */
@Autonomous(name="Concept Jewel", group="RAWRXD_CONC")
@Disabled
public class RAWRXD_Conc_Jewel extends LinearOpMode{
    RAWRXDBot bot;
    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXDBot(hardwareMap, this, telemetry);
        bot.Init();

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a){
                bot.UpJewel();
            }
            if(gamepad1.b){
                bot.DownJewel();
            }

        }
    }
}
