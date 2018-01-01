package org.firstinspires.ftc.teamcode.lib.auto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Victo on 12/31/2017.
 */

public abstract class DogeAutoTask {
    private String name;
    private String status;
    private DogeAutoOpMode parentOpMode;
    public void Run(DogeAutoOpMode opMode){
        parentOpMode = opMode;
    }
}
