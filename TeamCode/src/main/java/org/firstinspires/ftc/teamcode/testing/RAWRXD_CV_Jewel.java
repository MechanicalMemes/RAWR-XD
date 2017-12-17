
package org.firstinspires.ftc.teamcode.testing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.io.IOException;


@Autonomous(name="DogeCV Jewel Detector", group="testing")

public class RAWRXD_CV_Jewel extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


    private JewelDetector jewelDetector = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        jewelDetector = new JewelDetector();
        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        jewelDetector.downScaleFactor = 0.3;
        jewelDetector.speed = JewelDetector.JewelDetectionSpeed.FAST;
        jewelDetector.enable();


    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized. Gyro Calibration");
    }

    @Override
    public void start() {
        runtime.reset();


    }

    @Override
    public void loop() {



        telemetry.addData("Status", "Run Time: " + runtime.toString());



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        jewelDetector.disable();
    }

}
