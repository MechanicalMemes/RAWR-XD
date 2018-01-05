package org.firstinspires.ftc.teamcode.testing.concepts;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 1/3/2018.
 */
@Autonomous(name="Conc Gyro", group="RAWRXD_CONC")

public class RAWRXD_Conc_Gyro extends LinearOpMode {
    private RAWRXDBot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXDBot(hardwareMap, this, telemetry);
        bot.Init();
        bot.SetPhoneFront();

        waitForStart();

        if(opModeIsActive()){
            bot.gyroDrive(1.0,1000,5);
            bot.gyroTurn(1.0, 90);
        }
    }
}
