package org.firstinspires.ftc.teamcode.lib.auto;

import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;

/**
 * Created by Victo on 1/17/2018.
 */

public abstract class CommandBase {
    public DogeBot bot;
    public DogeAutoOpMode opMode;
    public boolean overrideLoop = false;
    public CommandBase(DogeAutoOpMode opMode){
        this.opMode = opMode;
        this.bot = opMode.bot;
    }
    public void setOverrideLoop(boolean va){
        overrideLoop = overrideLoop;
    }




    public void Run(){
        Start();
        if(!overrideLoop){
            while(canRunLoop()){
                Loop();
            }
            Stop();
        }

    }
    public abstract void Start();
    public abstract void Loop();
    public abstract void Stop();

    public abstract boolean IsTaskRunning();
    public boolean canRunLoop(){
        return !opMode.isStopRequested() && opMode.opModeIsActive() && IsTaskRunning();
    }
}
