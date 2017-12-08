package com.disnodeteam.dogeauto;

/**
 * Created by Victo on 12/7/2017.
 */

public class DogeWaitCondition {
    public String name = "Parent WaitCondition";

    public DogeWaitCondition(){

    }

    public DogeWaitCondition(String waitName){
        name = waitName;
    }

    public boolean isRunning(){
        return false;
    }
}
