package org.firstinspires.ftc.teamcode.lib.auto;

/**
 * Created by Victo on 12/10/2017.
 */

public abstract class AutonomousAction {
    public String name = "Default Action";
    public DogeAutonomous autonomous = null;
    public boolean isBusy = false;
    public boolean isInit = false;

    public AutonomousAction(String actionName){
        this.name = actionName;
    }

    public void OnParentSet(DogeAutonomous parentAuto){
        autonomous = parentAuto;
    }

    public abstract void Init();

    public abstract void Start();

    public abstract void Loop();

    public abstract void Stop();

}
