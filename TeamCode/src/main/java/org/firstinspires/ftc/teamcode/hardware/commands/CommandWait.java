package org.firstinspires.ftc.teamcode.hardware.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/17/2018.
 */

public class CommandWait extends CommandBase {
    private double time;
    public CommandWait(DogeAutoOpMode opMode, double time) {
        super(opMode);
        this.time = time;
    }

    @Override
    public void Run() {
        ElapsedTime timer = new ElapsedTime();
        while (canRunLoop() && timer.seconds() < time){
        }
    }

    @Override
    public void Stop() {

    }
}
