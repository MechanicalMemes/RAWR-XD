package org.firstinspires.ftc.teamcode.lib.auto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 12/10/2017.
 */

public class AutonomousTask {
    public String name = "Default Task";
    public List<AutonomousAction> actions = new ArrayList<>();


    public AutonomousTask(String taskName){
        name = taskName;
    }


    public void Init(){
        for (AutonomousAction action: actions) {
            action.Start();
        }
    }
    public void Start(){
        for (AutonomousAction action: actions) {
            action.Start();
        }
    }
    public void Loop(){
        for (AutonomousAction action: actions) {
            action.Loop();
        }
    }
    public void Stop(){
        for (AutonomousAction action: actions) {
            action.Stop();
        }
    }
}
