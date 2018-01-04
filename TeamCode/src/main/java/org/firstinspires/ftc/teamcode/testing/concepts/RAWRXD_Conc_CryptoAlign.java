package org.firstinspires.ftc.teamcode.testing.concepts;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.qualcomm.ftcrobotcontroller.FieldPositonData;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 1/3/2018.
 */

public class RAWRXD_Conc_CryptoAlign extends OpMode {
    private CryptoboxDetector cryptoboxDetector = null;
    private RAWRXDBot bot = new RAWRXDBot(hardwareMap, null);
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        switch (FieldPositonData.fieldPostion){
            case RED_BOTTOM:
                break;
        }


        cryptoboxDetector = new CryptoboxDetector();
        cryptoboxDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        cryptoboxDetector.downScaleFactor = 0.5;
        cryptoboxDetector.detectionMode = CryptoboxDetector.CryptoboxDetectionMode.RED;
        cryptoboxDetector.speed = CryptoboxDetector.CryptoboxSpeed.BALANCED;
        cryptoboxDetector.trackableMemory = 8;
        cryptoboxDetector.rotateMat = false;



        //Optional Test Code to load images via Drawables
        //cryptoboxDetector.useImportedImage = true;
        //cryptoboxDetector.SetTestMat(com.qualcomm.ftcrobotcontroller.R.drawable.test_cv4);

        cryptoboxDetector.enable();


    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {


        double error = 0;
        if(cryptoboxDetector.isColumnDetected()){
            error =  cryptoboxDetector.getClosestPos() / 10;
        }
        bot.gyroDrive(0.1,1000,0, error);



    }
    int colCount = 0;
    @Override
    public void loop() {



        telemetry.addData("isCryptoBoxDetected", cryptoboxDetector.isCryptoBoxDetected());
        telemetry.addData("isColumnDetected ",  cryptoboxDetector.isColumnDetected());

        telemetry.addData("Column Left ",  cryptoboxDetector.getCryptoBoxLeftPosition());
        telemetry.addData("Column Center ",  cryptoboxDetector.getCryptoBoxCenterPosition());
        telemetry.addData("Column Right ",  cryptoboxDetector.getCryptoBoxRightPosition());




    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        cryptoboxDetector.disable();
    }
}
