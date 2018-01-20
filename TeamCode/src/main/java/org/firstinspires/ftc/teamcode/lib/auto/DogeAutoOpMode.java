package org.firstinspires.ftc.teamcode.lib.auto;

import android.app.Activity;

import com.qualcomm.ftccommon.configuration.RobotConfigFileManager;
import com.qualcomm.ftcrobotcontroller.FieldPositonData;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;
import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 12/31/2017.
 */

public abstract class DogeAutoOpMode extends LinearOpMode {

    public DogeBot bot;
    public FieldPositonData.FieldPostion fieldPostion;
    public boolean useDogeCV;
    public int autoSpeed;
    public String currentConfig;
    @Override
    public void runOpMode() throws InterruptedException {

        fieldPostion = FieldPositonData.fieldPostion;
        autoSpeed = FieldPositonData.speed;
        useDogeCV = FieldPositonData.dogeCVCrypto;

        RobotConfigFileManager configManager;

        configManager = new RobotConfigFileManager((Activity) hardwareMap.appContext);

        currentConfig = configManager.getActiveConfig().getName();

        switch (currentConfig){
            case "RAWR-XD":
                bot = new RAWRXDBot(hardwareMap);
                break;
        }

    }

}
