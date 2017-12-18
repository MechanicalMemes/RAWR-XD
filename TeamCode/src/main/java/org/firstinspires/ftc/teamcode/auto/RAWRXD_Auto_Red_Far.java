
package org.firstinspires.ftc.teamcode.auto;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXD_BOT;
import org.firstinspires.ftc.teamcode.hardware.sensors.IMU;
import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;


@Autonomous(name="RAWR Auto - Red - Far", group="RED AUTO")


public class RAWRXD_Auto_Red_Far extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private RAWRXD_BOT bot = null;

    private VuforiaHardware vuforia;
    private JewelDetector jewelDetector;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        bot = new RAWRXD_BOT(hardwareMap, this);
        bot.Init();
        bot.SetPhonePicto();

        bot.SetAllMotorsToMode(DcMotor.RunMode.RUN_TO_POSITION);

        jewelDetector = new JewelDetector();



        vuforia = new VuforiaHardware();
        vuforia.Init(hardwareMap);

        while(!isStarted() && !isStopRequested()){
            vuforia.Loop();
        }

        runtime.reset();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            vuforia.Stop();

            jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
            jewelDetector.enable();


            bot.SetPhoneOutside();
            bot.CloseGrab();
            while(runtime.seconds() < 0.2){

            }
            bot.LiftPower(-1);
            runtime.reset();
            while(runtime.seconds() < 0.5){

            }
            bot.LiftPower(0);

            switch (jewelDetector.getLastOrder()){
                case BLUE_RED:
                   bot.EncoderDrive(-500,-500,0.7);
                   bot.EncoderDrive(500,500,0.5);

            }

            bot.EncoderDrive(2000,2000,0.9);


            while(runtime.seconds() < 2){
            }


            switch(vuforia.getVuMark()){
                case UNKNOWN:
                    bot.EncoderDrive(2000,2000,1.0);
                    break;
                case LEFT:
                    bot.EncoderDrive(3000,3000,1.0);
                    break;

                case CENTER:
                    bot.EncoderDrive(2000,2000,1.0);
                    break;

                case RIGHT:
                    bot.EncoderDrive(1000,1000,1.0);
                    break;
            }

            while(runtime.seconds() < 0.2){

            }
            bot.EncoderDrive(2000,-2000,0.8);

            bot.EncoderDrive(2000,2000,0.5);

            bot.OpenGrab();

            while(runtime.seconds() < 0.1){
            }

            bot.EncoderDrive(-1000,-1000,1.0);

        }
    }
}
