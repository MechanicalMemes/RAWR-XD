
package org.firstinspires.ftc.teamcode.testing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXD_BOT;
import org.firstinspires.ftc.teamcode.hardware.sensors.IMU;
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

@Autonomous(name="RAWR-XD: IMU Test", group="Testing")


public class RAWRXD_IMU_Test extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private RAWRXD_BOT bot = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        bot = new RAWRXD_BOT(hardwareMap, this);

        bot.Init();
        telemetry.addData("IMU Status", bot.imu.imu.getSystemStatus());
        waitForStart();
        runtime.reset();

        if (opModeIsActive()) {

            bot.gyroDrive(0.5,4000,0);

            bot.gyroTurn(0.5,270);

            bot.gyroDrive(0.5,1000,270);

            bot.WaitForTime(0.4);
            bot.gyroDrive(0.5,-1000,270);

            bot.gyroTurn(0.5,90);

            bot.gyroDrive(0.5,4000,90);

        }


    }
}
