package org.firstinspires.ftc.teamcode.hardware.commands;

import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/23/2018.
 */

public class CommandAutoStack extends CommandBase {
    private CommandLiftToPosition liftUpCommand;
    private CommandLiftToPosition liftDownCommand;
    private CommandGyroDrive driveBackCommand;
    private CommandGyroDrive driveBackNudgeCommand;
    public CommandAutoStack(DogeAutoOpMode opMode) {
        super(opMode);
        liftUpCommand = new CommandLiftToPosition(opMode,bot.lift.maxPos / 2);
        liftDownCommand = new CommandLiftToPosition(opMode,bot.lift.minPos);
        driveBackCommand = new CommandGyroDrive(opMode, 0.4, bot.navigationHardware.getHeading(), -7.0);
        driveBackNudgeCommand = new CommandGyroDrive(opMode, 0.4, bot.navigationHardware.getHeading(), -0.2);
    }

    @Override
    public void Start() {
        liftUpCommand.Run();
        driveBackCommand.Run();
        bot.grabbers.openGrabbers();
        driveBackNudgeCommand.Run();
        liftDownCommand.Run();
        bot.grabbers.closeGrabbers();
        liftUpCommand.Run();
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
