package org.firstinspires.ftc.teamcode.lib.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXD_BOT;

/**
 * Created by Victo on 12/31/2017.
 */

public class DogeAutoOpMode extends LinearOpMode {

    private RAWRXD_BOT bot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXD_BOT(hardwareMap, this);
        bot.Init();
    }

}
