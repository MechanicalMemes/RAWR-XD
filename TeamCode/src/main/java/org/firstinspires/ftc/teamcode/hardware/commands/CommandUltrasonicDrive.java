package org.firstinspires.ftc.teamcode.hardware.commands;

import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/22/2018.
 */

public class CommandUltrasonicDrive extends CommandBase {
    public CommandUltrasonicDrive(DogeAutoOpMode opMode, double distance, double speed) {
        super(opMode);
    }

    public CommandUltrasonicDrive(DogeAutoOpMode opMode, double distance, double speed, double angle) {
        super(opMode);
    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {

    }

    @Override
    public void Stop() {

    }

    @Override
    public boolean IsTaskRunning() {
        return false;
    }
}
