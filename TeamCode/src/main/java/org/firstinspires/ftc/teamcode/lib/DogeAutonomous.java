package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 12/10/2017.
 */

public class DogeAutonomous {
    public String name = "Default DogeAutonomous";

    public List<AutonomousTask> tasks = new ArrayList<>();
    public List<AutonomousProgressor> progressors = new ArrayList<>();

    public int currentProgressorIndex = 0;

    public AutonomousTask currentTask = null;
    public AutonomousProgressor currentProgressor = null;

    public String currentStatus = "default";

    private OpMode parentOpMode = null;

    public DogeAutonomous(String autoName, OpMode opMode){
        name = autoName;
        parentOpMode = opMode;
    }

    public void AddTask(AutonomousTask task){
        tasks.add(task);
    }

    public void AddProgressor(AutonomousProgressor progressor){
        progressors.add(progressor);
    }


    public void Init(){
        for(AutonomousTask task: tasks){
            task.Init();
        }

        for(AutonomousProgressor progressor: progressors){
            progressor.Init();
        }
    }

    public void Start(){
        currentTask = tasks.get(0);
        currentProgressor = progressors.get(currentProgressorIndex);

        currentTask.Start();
        currentProgressor.Start();
    }

    public void Loop(){
        currentTask.Loop();

        if(currentProgressor.canProgress()) {
            currentTask.Stop();

            AutonomousTask nextTask = currentProgressor.getNextTask();

            if(nextTask == null){
                nextTask = tasks.get(tasks.indexOf(currentTask));
            }

            currentProgressorIndex++;

            currentTask.Stop();
            currentProgressor.Stop();

            currentTask = null;
            currentProgressor = null;

            currentTask = nextTask;
            currentTask.Start();

            if(currentProgressorIndex < progressors.size()){
                currentProgressor = progressors.get(currentProgressorIndex);
                currentProgressor.Start();
            }


        }

    }

}
