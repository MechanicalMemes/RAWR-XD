package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/4/2018.
 */
@Autonomous(name="Main Auto", group="AUTO")
public class DogeAutoMain extends DogeAutoOpMode {

    private DogeAuto selectedAuto;

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();





        switch(fieldPostion){
            case RED_BOTTOM:

                break;

            case BLUE_TOP:
                //selectedAuto = blueTopAuto;
                break;
            case BLUE_BOTTOM:

        }


        selectedAuto.Init();

        if(opModeIsActive()){
            selectedAuto.Run();
        }

    }
}
