package org.firstinspires.ftc.teamcode.testing.concepts;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot_Old;

/**
 * Created by Victo on 1/3/2018.
 */
@Autonomous(name="Conc Crpyto Align", group="RAWRXD_CONC")
@Disabled
public class RAWRXD_Conc_CryptoAlign extends LinearOpMode {
    private CryptoboxDetector cryptoboxDetector = null;
    private RAWRXDBot_Old bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXDBot_Old(hardwareMap, this, telemetry);
        bot.Init();
        bot.SetPhoneFront();

        cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        cryptoboxDetector.downScaleFactor = 0.5;
        cryptoboxDetector.detectionMode = CryptoboxDetector.CryptoboxDetectionMode.RED;
        cryptoboxDetector.speed = CryptoboxDetector.CryptoboxSpeed.BALANCED;
        cryptoboxDetector.trackableMemory = 8;
        cryptoboxDetector.rotateMat = false;
        cryptoboxDetector.enable();

        waitForStart();



        if(opModeIsActive()){

            bot.gyroDriveCrpyto(0.5,500, cryptoboxDetector);
        }

        cryptoboxDetector.disable();
    }

    /*
         * Code to run ONCE when the driver hits INIT
         */

}
