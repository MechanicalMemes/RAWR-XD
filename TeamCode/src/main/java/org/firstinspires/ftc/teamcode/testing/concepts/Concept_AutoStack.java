package org.firstinspires.ftc.teamcode.testing.concepts;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandAutoStack;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandGyroTurn;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/23/2018.
 */

public class Concept_AutoStack extends DogeAutoOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        if (opModeIsActive()){
            new CommandAutoStack(this).Run();
        }
    }
}
