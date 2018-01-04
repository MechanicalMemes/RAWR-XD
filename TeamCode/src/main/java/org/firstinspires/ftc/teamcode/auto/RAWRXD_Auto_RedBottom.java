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

        switch (autoSpeed){
            case 0:
                speedMultiplier = 0.25;
            case 1:
                speedMultiplier = 0.4;
            case 2:
                speedMultiplier = 0.6;
            case 3:
                speedMultiplier = 0.8;
            case 4:
                speedMultiplier = 1;
        }
    }


    @Override
    public void Init() {
        bot.SetPhonePicto();
        bot.SetDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        jewelDetector = new JewelDetector();
        jewelDetector.downScaleFactor = 0.4;
        jewelDetector.rotateMat = true;

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

        bot.WaitForTime(0.2);

        bot.LiftToPosition(600);

        bot.WaitForTime(0.4);

        switch (jewelDetector.getLastOrder()){
            case RED_BLUE:
                bot.EncoderDrive(-100,-100,0.2);
                bot.WaitForTime(0.3);
                bot.EncoderDrive(100,100,0.5);

        }

        jewelDetector.disable();



        bot.gyroDrive(0.5 * speedMultiplier,1900,  10);

        switch(vuforia.getVuMark()){
            case UNKNOWN:
                bot.gyroDrive(1.0 * speedMultiplier,1000,0);

                break;
            case LEFT:
                bot.gyroDrive(1.0 * speedMultiplier,2250,0);

                break;

            case CENTER:
                bot.gyroDrive(1.0 * speedMultiplier,1000,0);

                break;

            case RIGHT:
                bot.gyroDrive(1.0 * speedMultiplier,500,0);

                break;
        }

        bot.gyroTurn(1 * speedMultiplier,270);

        bot.gyroDrive(0.4 * speedMultiplier,700,270);

        bot.OpenGrab();


        bot.gyroDrive(0.5 * speedMultiplier,-1000,270);
        bot.gyroTurn(1 * speedMultiplier,90);

        bot.LiftToPosition(0);
        bot.gyroDrive(1 * speedMultiplier,3000,90);

        bot.CloseGrab();

        bot.WaitForTime(0.1);

        bot.LiftToPosition(1200);

        bot.WaitForTime(0.2);


        bot.gyroDrive(1 * speedMultiplier,-1000,90);

        bot.gyroTurn(1 * speedMultiplier,260);
        cryptoboxDetector.enable();

        bot.gyroDrive(1 * speedMultiplier,3000,260);
        bot.gyroDrive(1 * speedMultiplier,500,270);

        bot.OpenGrab();
        bot.WaitForTime(0.4);
        bot.gyroDrive(0.25 * speedMultiplier,-800,270);
        cryptoboxDetector.disable();
    }

    @Override
    public void Stop() {

    }
}
