package org.firstinspires.ftc.teamcode.lib.auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.bots.DogeBot;
import org.firstinspires.ftc.teamcode.hardware.bots.RAWRXDBot_Old;

/**
 * Created by Victo on 1/4/2018.
 */

public abstract class DogeAuto {
    public DogeAutoOpMode opMode;
    public Telemetry telemetry;
    public int autoSpeed;
    public boolean useDogeCV;
    public DogeBot bot;

    public DogeAuto(DogeAutoOpMode parent){
        opMode = parent;
        telemetry = opMode.telemetry;
        bot = opMode.bot;
        autoSpeed = opMode.autoSpeed;
        useDogeCV = opMode.useDogeCV;
    }

    public abstract void Init();

    public abstract void Run();

    public abstract void Stop();
}
