package com.disnodeteam.dogeauto;

import android.util.Log;



import java.util.ArrayList;

public class DogeAuto {

    private ArrayList<DogeTask> tasks = new ArrayList<>();
    private DogeWaitCondition waitCondition = null;

    private String currentStatus = "";

   // private OpMode opMode;

    private boolean isStopped = false;
    public DogeAuto(){
    }

    public void AddTask(DogeTask task){
        Log.d("DogeAuto", "Adding task: " + task.name);
        tasks.add(task);
    }

    public void SetWaitCondition(DogeWaitCondition condition){
        waitCondition = condition;
    }

    public void Run(){
        for (DogeTask task : tasks){
            task.Run();
        }

        while(isStopped && waitCondition.isRunning()){
            for (DogeTask task : tasks){
                task.Loop();
            }
        }

        for (DogeTask task : tasks){
            task.Stop();
        }
    }

    public void Stop(){
        isStopped = true;
        for (DogeTask task : tasks){
            task.Stop();
        }
    }
}
