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

        switch(fieldPostion){
            case RED_BOTTOM:
                selectedAuto = redBottomAuto;
        }


        selectedAuto.Init();

        if(opModeIsActive()){
            selectedAuto.Run();
        }

    }
}
