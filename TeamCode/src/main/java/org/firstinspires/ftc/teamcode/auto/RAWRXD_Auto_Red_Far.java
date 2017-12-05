
package org.firstinspires.ftc.teamcode.auto;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.CryptoboxDetectorBlue;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.RAWRXD_BOT;

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

@Autonomous(name="RAWR Auto - Red - Far", group="RED AUTO")

public class RAWRXD_Auto_Red_Far extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private RAWRXD_BOT bot = null;

    private JewelDetector jewelDetector = null;
    private CryptoboxDetectorBlue cryptoboxDetectorBlue = null;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        bot = new RAWRXD_BOT(hardwareMap);

        bot.Init();

        bot.SetPhoneInside();

       // jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

    }



    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized. Gyro Calibration");
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

        runtime.reset();

       // JewelDetector.JewelOrder jewelOrder =  jewelDetector.getOrder();


        bot.SetPhoneOutside();

        bot.DriveStraight(2000,0.5,0.0);
        bot.Turn(1.0,45.0);



    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {

        telemetry.addData("Status", "Run Time: " + runtime.toString());






    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
