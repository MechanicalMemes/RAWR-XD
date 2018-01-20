package org.firstinspires.ftc.teamcode.hardware.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.subsystems.Lift;
import org.firstinspires.ftc.teamcode.lib.auto.CommandBase;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/20/2018.
 */

public class CommandLiftToPosition extends CommandBase {

    private int position;
    public CommandLiftToPosition(DogeAutoOpMode opMode, int pos) {
        super(opMode);
        position = pos;
    }

    @Override
    public void Run() {
        bot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bot.lift.setTarget(position);

        while(canRunLoop() && bot.lift.isLiftBusy()){
            bot.lift.liftInput(1.0,true);
        }

        bot.lift.liftInput(0,true);
    }

    @Override
    public void Stop() {

    }
}
