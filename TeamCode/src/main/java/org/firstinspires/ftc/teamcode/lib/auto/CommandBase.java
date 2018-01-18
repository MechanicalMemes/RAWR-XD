package org.firstinspires.ftc.teamcode.lib.auto;

import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;

/**
 * Created by Victo on 1/17/2018.
 */

public abstract class CommandBase {
    public DogeBot bot;
    public DogeAutoOpMode opMode;
    public CommandBase(DogeAutoOpMode opMode){
        this.opMode = opMode;
        this.bot = opMode.bot;
    }

    public abstract void Run();
    public abstract void Stop();

    public boolean canRunLoop(){
        return !opMode.isStopRequested() && opMode.opModeIsActive();
    }
}
