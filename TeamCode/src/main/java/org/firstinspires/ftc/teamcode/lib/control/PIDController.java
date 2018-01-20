package org.firstinspires.ftc.teamcode.lib.control;

public class PIDController {
    private double kP;
    private double kI;
    private double kD;
    private double lastTime;
    private int integral = 0;
    private int previousError = 0;

    public PIDController(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public PIDController(){
    }

    public void setVariables(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double run(int targetValue, int position) {
        double dt = (System.currentTimeMillis() - lastTime);
        lastTime = System.currentTimeMillis();

        int angleError = (targetValue - position);
        angleError -= (360*Math.floor(0.5+(((double) angleError)/360.0)));

        int error = angleError;

        integral += kI * error * dt;

        double u = (kP * error + integral + kD * (error - previousError) / dt);

        previousError = error;

        return u;
    }
}