package org.firstinspires.ftc.teamcode.lib.progressors;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.AutonomousProgressor;
import org.firstinspires.ftc.teamcode.lib.AutonomousTask;

/**
 * Created by Victo on 12/10/2017.
 */

public class TimeProgressor extends AutonomousProgressor {

    private ElapsedTime timer = null;
    private double timeToProgress = 1.0;

    public TimeProgressor(String progressorName, AutonomousTask defaultNextTask, double timeS) {
        super(progressorName, defaultNextTask);
        timeToProgress = timeS;
    }

    @Override
    public void Init() {
        timer = new ElapsedTime();
        status = "Init.";
    }

    @Override
    public void Start() {
        timer.reset();
        status = "Starting timer";
    }


    @Override
    public boolean canProgress() {
        status = "Counting: " + timer.seconds();
        return timer.seconds() >= timeToProgress;
    }

    @Override
    public void Stop() {
        timer = null;
        status = "Stopped. Destroying resources.";
    }

    @Override
    public AutonomousTask getNextTask() {
        status = "Asked for next task. Ending.";
        return defaultNextTask;
    }
}
