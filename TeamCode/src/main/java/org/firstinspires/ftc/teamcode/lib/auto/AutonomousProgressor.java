package org.firstinspires.ftc.teamcode.lib.auto;

/**
 * Created by Victo on 12/10/2017.
 */

public abstract class AutonomousProgressor {
    public String name = "Default Progressor";

    public AutonomousTask defaultNextTask = null; // Instead of defining a next task, just go to the next task in the list
    public String status = "null";

    public AutonomousProgressor(String progressorName, AutonomousTask defaultNextTask){
        this.name = progressorName;

        if(defaultNextTask == null){
            this.defaultNextTask = null;
            this.status = "Constructed. Using next task as default.";
        }else{
            this.defaultNextTask = defaultNextTask;
            this.status = "Constructed. Default task set.";
        }

    }

    public abstract void Init();

    public abstract void Start();

    public abstract boolean canProgress();

    public abstract void Stop();

    public abstract AutonomousTask getNextTask();
}
