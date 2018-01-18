package org.firstinspires.ftc.teamcode.lib.auto;

import com.qualcomm.ftcrobotcontroller.FieldPositonData;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;

/**
 * Created by Victo on 12/31/2017.
 */

public abstract class DogeAutoOpMode extends LinearOpMode {

    public DogeBot bot;
    public FieldPositonData.FieldPostion fieldPostion;
    public boolean useDogeCV;
    public int autoSpeed;
    @Override
    public void runOpMode() throws InterruptedException {

        fieldPostion = FieldPositonData.fieldPostion;
        autoSpeed = FieldPositonData.speed;
        useDogeCV = FieldPositonData.dogeCVCrypto;
    }

}
