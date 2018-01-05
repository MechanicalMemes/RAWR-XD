
package org.firstinspires.ftc.teamcode.auto;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;
import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;


@Autonomous(name="RAWR Auto - Red - Far", group="RED AUTO")
@Disabled

public class RAWRXD_Auto_Red_Far extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private RAWRXDBot bot = null;

    private VuforiaHardware vuforia;
    private JewelDetector jewelDetector;
    private CryptoboxDetector cryptoboxDetectorOld;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        bot = new RAWRXDBot(hardwareMap, this, telemetry);
        bot.Init();
        bot.SetPhonePicto();

        bot.SetDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        jewelDetector = new JewelDetector();
        jewelDetector.downScaleFactor = 0.4;
        jewelDetector.rotateMat = true;
        cryptoboxDetectorOld = new CryptoboxDetector();

        vuforia = new VuforiaHardware();
        vuforia.Init(hardwareMap);

        cryptoboxDetectorOld.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        while(!isStarted() && !isStopRequested()){
            vuforia.Loop();
            telemetry.addData("Vuforia", vuforia.getVuMark().toString());
            telemetry.update();
        }


        runtime.reset();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
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
            cryptoboxDetectorOld.enable();


            bot.gyroDrive(0.5,1900,  10);

            switch(vuforia.getVuMark()){
                case UNKNOWN:
                    bot.gyroDrive(1.0,1000,0);

                    break;
                case LEFT:
                    bot.gyroDrive(1.0,2250,0);

                    break;

                case CENTER:
                    bot.gyroDrive(1.0,1000,0);

                    break;

                case RIGHT:
                    bot.gyroDrive(1.0,500,0);

                    break;
            }

            bot.gyroTurn(1,270);

            bot.gyroDrive(0.4,700,270);

            bot.OpenGrab();


            bot.gyroDrive(0.5,-1000,270);
            bot.gyroTurn(1,90);
            runtime.reset();

            bot.LiftToPosition(0);
            bot.gyroDrive(1,3000,90);

            bot.CloseGrab();

            bot.WaitForTime(0.1);

            bot.LiftToPosition(1200);

            bot.WaitForTime(0.2);


            bot.gyroDrive(1,-1000,90);

            bot.gyroTurn(1,260);

            bot.gyroDrive(1,3000,260);
            bot.gyroDrive(1,500,270);

            bot.OpenGrab();
            bot.WaitForTime(0.4);
            bot.gyroDrive(0.25,-800,270);

        }
    }
}
