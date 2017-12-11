package org.firstinspires.ftc.teamcode.lib.auto.actions;

import org.firstinspires.ftc.teamcode.lib.auto.AutonomousAction;

/**
 * Created by Victo on 12/10/2017.
 */

public class DriveAction extends AutonomousAction {


    public DriveAction(String actionName, double lPower, double rPower, double lDistance, double rDistance, boolean angelHold) {
        super(actionName);
    }

    @Override
    public void Init() {

    }

    @Override
    public void Start() {
        //WOO DRIVE MOTORS
    }

    @Override
    public void Loop() {
        //WOO PIDS
    }

    @Override
    public void Stop() {
        // BOO STOPP
    }
}
