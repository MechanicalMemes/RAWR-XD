package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetector;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/4/2018.
 */
@Autonomous(name="RAWR Auto - Red - Far", group="RAWRXD_AUTO")
public class RAWRXD_Auto_MK1 extends DogeAutoOpMode {
    private DogeAuto redBottomAuto;
    private DogeAuto redTopAuto;
    private DogeAuto blueTopAuto;
    private DogeAuto blueBottomAuto;

    private DogeAuto selectedAuto;

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        redBottomAuto = new RAWRXD_Auto_RedBottom(this);
       // blueTopAuto = new RAWRXD_Auto_BlueTopGhetto(this);
        blueBottomAuto = new RAWRXD_Auto_BlueBottom(this);

        switch(fieldPostion){
            case RED_BOTTOM:
                selectedAuto = redBottomAuto;
                break;

            case BLUE_TOP:
                //selectedAuto = blueTopAuto;
                break;
            case BLUE_BOTTOM:
                selectedAuto = blueBottomAuto;
        }


        selectedAuto.Init();

        if(opModeIsActive()){
            selectedAuto.Run();
        }

    }
}
