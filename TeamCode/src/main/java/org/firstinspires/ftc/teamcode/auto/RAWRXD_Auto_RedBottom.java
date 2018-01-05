package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/4/2018.
 */

public class RAWRXD_Auto_RedBottom extends DogeAuto {


    private VuforiaHardware vuforia;
    private JewelDetector jewelDetector;
    private CryptoboxDetector cryptoboxDetector;


    private double speedMultiplier = 1.0;
    public RAWRXD_Auto_RedBottom(DogeAutoOpMode parent) {
        super(parent);


    }


    @Override
    public void Init() {
        bot.SetPhonePicto();
        bot.SetDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        jewelDetector = new JewelDetector();
        jewelDetector.downScaleFactor = 0.4;
        jewelDetector.rotateMat = true;
        jewelDetector.areaWeight = 30;
        jewelDetector.perfectRatio = 1.0;
        jewelDetector.debugContours = true;

        cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.trackableMemory = 8;

        vuforia = new VuforiaHardware();
        vuforia.Init(opMode.hardwareMap);

        cryptoboxDetector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance());
        jewelDetector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance());

        while(!opMode.isStarted() && !opMode.isStopRequested()) {
            vuforia.Loop();
            telemetry.addData("Vuforia", vuforia.getVuMark().toString());
            telemetry.update();
        }

    }

    @Override
    public void Run() {
        vuforia.Stop();


        jewelDetector.enable();


        //bot.SetPhoneOutside();
        bot.CloseGrab();
        bot.DownJewel();
        bot.SetPhoneOutside();
        bot.WaitForTime(1.0);

        bot.LiftToPosition(800);

        bot.WaitForTime(0.4);

        switch (jewelDetector.getLastOrder()){
            case RED_BLUE:
                bot.EncoderDrive(-50,-50,0.2);
                bot.WaitForTime(0.3);
                bot.EncoderDrive(50,50,0.2);

        }

        jewelDetector.disable();


        bot.SetPhoneFront();
        bot.gyroDrive(0.2 * speedMultiplier,400,  -5);
        bot.UpJewel();
        switch(vuforia.getVuMark()){
            case UNKNOWN:
                bot.gyroDrive(0.6 * speedMultiplier,1000,0);

                break;
            case LEFT:
                bot.gyroDrive(0.6 * speedMultiplier,1400,0);

                break;

            case CENTER:
                bot.gyroDrive(0.6 * speedMultiplier,1100,0);

                break;

            case RIGHT:
                bot.gyroDrive(0.6 * speedMultiplier,800,0);

                break;
        }

        bot.gyroTurn(0.6 * speedMultiplier,90);

        bot.gyroDrive(0.4 * speedMultiplier,300,90);

        bot.OpenGrab();


        bot.gyroDrive(0.5 * speedMultiplier,-500,90);
        bot.gyroTurn(0.6 * speedMultiplier,270);

        bot.LiftToPosition(10);
        bot.gyroDrive(1 * speedMultiplier,1400,270);

        bot.CloseGrab();

        bot.WaitForTime(0.1);

        bot.LiftToPosition(2000);

        bot.WaitForTime(0.2);


        bot.gyroDrive(0.6 * speedMultiplier,-500,270);

        bot.gyroTurn(0.6* speedMultiplier,90);
        cryptoboxDetector.enable();

        bot.gyroDrive(0.8* speedMultiplier,1000,90);
        bot.gyroDrive(0.1 * speedMultiplier,600,90);

        bot.OpenGrab();
        bot.WaitForTime(0.4);
        bot.gyroDrive(0.25 * speedMultiplier,-300,90);
        bot.LiftToPosition(0);
        cryptoboxDetector.disable();
    }

    @Override
    public void Stop() {

    }
}
