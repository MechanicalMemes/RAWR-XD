package org.firstinspires.ftc.teamcode.testing.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot;

import org.firstinspires.ftc.teamcode.hardware.commands.CommandGyroTurn;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandWait;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/3/2018.
 */
@Autonomous(name="Conc Gyro", group="RAWRXD_CONC")

public class RAWRXD_Conc_Gyro extends DogeAutoOpMode {
    private RAWRXDBot TempBot;

    @Override
    public void runOpMode() throws InterruptedException {
        TempBot = new RAWRXDBot(hardwareMap);
        this.bot =TempBot;

        waitForStart();

        while (opModeIsActive()){
            // Soft Turning
            new CommandGyroTurn(this,1.0,45).Run();
            new CommandGyroTurn(this,1.0,-45).Run();

            // Probaby gonna break stuff
            new CommandGyroTurn(this,1.0,90).Run();
            new CommandGyroTurn(this,1.0,180).Run();

            new CommandGyroTurn(this,1.0,0).Run();
            new CommandGyroTurn(this,1.0,45).Run();
            new CommandGyroTurn(this,1.0,90).Run();
            new CommandGyroTurn(this,1.0,125).Run();
            new CommandGyroTurn(this,1.0,180).Run();
            new CommandGyroTurn(this,1.0,225).Run();
            new CommandGyroTurn(this,1.0,270).Run();
        }
    }
}
