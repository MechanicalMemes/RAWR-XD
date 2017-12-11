
package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXD_BOT;
import org.firstinspires.ftc.teamcode.lib.auto.AutonomousAction;
import org.firstinspires.ftc.teamcode.lib.auto.AutonomousProgressor;
import org.firstinspires.ftc.teamcode.lib.auto.AutonomousTask;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutonomous;
import org.firstinspires.ftc.teamcode.lib.auto.actions.DriveAction;
import org.firstinspires.ftc.teamcode.lib.auto.progressors.TimeProgressor;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank DriveManual Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Task Auto Test 1", group="testing")


public class RAWRXD_TaskAuto_Test1 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private RAWRXD_BOT bot = null;

    private DogeAutonomous redAuto = new DogeAutonomous("Red Auto", this);

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        bot = new RAWRXD_BOT(hardwareMap, this);

        bot.Init();

        AutonomousTask driveTask1 = new AutonomousTask("Drive Task 1");
        AutonomousAction driveStraightAction = new DriveAction("Drive forward",1.0,1.0,3000,3000,false);

        driveTask1.actions.add(driveStraightAction);

        redAuto.AddTask(driveTask1);

        AutonomousProgressor timer1 = new TimeProgressor("Wait for Something",null, 1.5);

        redAuto.AddProgressor(timer1);


        AutonomousTask driveTask2 = new AutonomousTask("Drive Task 2");
        AutonomousAction turnAction = new DriveAction("Turn",1.0,-1.0,3000,-3000,false);

        driveTask1.actions.add(turnAction);

        redAuto.AddTask(driveTask2);


        redAuto.Init();

        waitForStart();
        runtime.reset();
        redAuto.Start();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            redAuto.Loop();

        }


    }
}
