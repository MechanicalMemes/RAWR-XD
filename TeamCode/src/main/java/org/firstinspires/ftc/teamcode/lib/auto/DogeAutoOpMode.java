package org.firstinspires.ftc.teamcode.lib.auto;

import com.qualcomm.ftcrobotcontroller.FieldPositonData;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

/**
 * Created by Victo on 12/31/2017.
 */

public abstract class DogeAutoOpMode extends LinearOpMode {

    public RAWRXDBot bot = null;
    public FieldPositonData.FieldPostion fieldPostion;
    public boolean useDogeCV;
    public int autoSpeed;
    @Override
    public void runOpMode() throws InterruptedException {

        fieldPostion = FieldPositonData.fieldPostion;
        autoSpeed = FieldPositonData.speed;
        useDogeCV = FieldPositonData.dogeCVCrypto;

        bot = new RAWRXDBot(hardwareMap, this);
        bot.Init();
    }

}
