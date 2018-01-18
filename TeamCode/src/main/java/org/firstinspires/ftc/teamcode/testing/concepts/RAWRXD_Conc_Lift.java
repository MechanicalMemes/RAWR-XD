package org.firstinspires.ftc.teamcode.testing.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot_Old;

/**
 * Created by Victo on 1/4/2018.
 */
@Autonomous(name="Concept Lift", group="RAWRXD_CONC")
@Disabled
public class RAWRXD_Conc_Lift extends LinearOpMode{
    RAWRXDBot_Old bot;
    @Override
    public void runOpMode() throws InterruptedException {
        bot = new RAWRXDBot_Old(hardwareMap, this, telemetry);
        bot.Init();

        waitForStart();

        bot.LiftToPosition(2500);
        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 2.0){

        }
        bot.LiftToPosition(0);
    }
}
