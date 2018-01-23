package org.firstinspires.ftc.teamcode.hardware.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/17/2018.
 */

public class CommandWait extends CommandBase {
    private double time;
    private ElapsedTime timer;
    public CommandWait(DogeAutoOpMode opMode, double time) {
        super(opMode);
        this.time = time;
    }


    @Override
    public void Start() {
        timer = new ElapsedTime();
    }

    @Override
    public void Loop() {
        opMode.telemetry.addData("Waiting",timer.seconds() + "/" + time );
    }

    @Override
    public void Stop() {
        timer = null;
    }

    @Override
    public boolean IsTaskRunning() {
        return timer.seconds() < time;
    }
}
