package org.firstinspires.ftc.teamcode.lib.control;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Victo on 1/17/2018.
 */

public class PIDController {

    double P, I, D;

    private double lastTime;
    private double Input, Output, SetPoint;
    private double errSum, lastErr;
    private ElapsedTime elapsedTime;
    private boolean elaspReset = false;
    private boolean bypassError = false;

    public PIDController(double P, double I, double D, double setPoint){
        this.P = P;
        this.I = I;
        this.D = D;

        this.SetPoint = setPoint;

        elapsedTime = new ElapsedTime();
    }

    public PIDController(double P, double I, double D){
        this.P = P;
        this.I = I;
        this.D = D;

        this.SetPoint = 0;
        this.bypassError = true;
        elapsedTime = new ElapsedTime();
    }

    public double Compute(double input) {
        if (!elaspReset) {
            elapsedTime.reset();
            elaspReset = true;
        }

        double now = elapsedTime.milliseconds();

        double error = SetPoint - input;

        if(bypassError){
            error = input;
        }

        errSum += error * now;

        double dErr = (error - lastErr) / now;

        double res  = P * error + I * errSum + D * dErr;

        lastErr = error;

        elapsedTime.reset();
        return res;
    }
}
