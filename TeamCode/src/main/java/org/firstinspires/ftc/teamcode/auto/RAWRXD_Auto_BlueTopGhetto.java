package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.GlyphDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/4/2018.
 */

public class RAWRXD_Auto_BlueTopGhetto extends DogeAuto {


    private VuforiaHardware vuforia;
    private JewelDetector jewelDetector;
    private CryptoboxDetector cryptoboxDetector;
    private GlyphDetector glyphDetector;


    private double speedMultiplier = 1.0;
    public RAWRXD_Auto_BlueTopGhetto(DogeAutoOpMode parent) {
        super(parent);


    }


    @Override
    public void Init() {
        bot.SetPhonePicto();
        bot.SetDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        jewelDetector = new JewelDetector();
        jewelDetector.downScaleFactor = 0.4;
        jewelDetector.areaWeight = 30;
        jewelDetector.perfectRatio = 1.0;
        jewelDetector.debugContours = true;

        cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.trackableMemory = 8;

        glyphDetector = new GlyphDetector();
        glyphDetector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance());

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

        bot.LiftToPosition(30);

        bot.WaitForTime(2.0);

        telemetry.addData("Jewel", jewelDetector.getLastOrder().toString());
        telemetry.update();
        switch (jewelDetector.getLastOrder()){
            case BLUE_RED:
                bot.gyroDrive(0.2,-70,0);

                bot.UpJewel();
                bot.gyroDrive(1.0,80,0);

        }
        bot.LiftToPosition(1000);
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
                bot.gyroDrive(0.6 * speedMultiplier,1200,0);

                break;

            case RIGHT:
                bot.gyroDrive(0.6 * speedMultiplier,780,0);

                break;
        }

        bot.gyroTurn(0.6 * speedMultiplier,90);

        bot.gyroDrive(0.4 * speedMultiplier,300,90);

        bot.OpenGrab();

        glyphDetector.enable();
        bot.gyroDrive(0.5 * speedMultiplier,-500,90);
        bot.gyroTurn(0.6 * speedMultiplier,270);

        bot.LiftToPosition(10);
        bot.gyroDrive(1 * speedMultiplier,1400,270);

        bot.CloseGrab();

        bot.WaitForTime(0.1);

        bot.LiftToPosition(3000);

        bot.WaitForTime(0.2);


        bot.gyroDrive(0.6 * speedMultiplier,-500,270);

        bot.gyroTurn(0.6* speedMultiplier,90);


        bot.gyroDrive(0.7* speedMultiplier,1000,90);
        bot.gyroDrive(0.1 * speedMultiplier,600,90);

        bot.OpenGrab();
        bot.WaitForTime(0.4);
        bot.gyroDrive(0.25 * speedMultiplier,-1000,90);
        bot.LiftToPosition(0);
        bot.gyroDrive(0.7 * speedMultiplier,600,90);
        glyphDetector.disable();
    }

    @Override
    public void Stop() {

    }
}
