package org.firstinspires.ftc.teamcode.testing.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.teamcode.hardware.commands.CommandAutoStack;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DistanceDetection;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/23/2018.
 */
@Autonomous(name="Conc Distance", group="RAWRXD_CONC")

public class Concept_Distance extends DogeAutoOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DistanceDetection distanceDetection = new DistanceDetection(hardwareMap, "ultra");

        waitForStart();

        while (opModeIsActive()){

            if(distanceDetection.getDistance() > 18){
                telemetry.addData("STATUS", "STOP!!!");
                telemetry.update();
            }

            //telemetry.addData("Distance", distanceDetection.getDistance());

            //telemetry.update();
        }
    }
}
