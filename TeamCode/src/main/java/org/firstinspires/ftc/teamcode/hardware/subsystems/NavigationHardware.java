package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

/**
 * Created by Victo on 1/17/2018.
 */

public class NavigationHardware {

    public enum NavType{
        MR_GYRO,
        REV_IMU
    }

    public Gyroscope mrGyro;
    public BNO055IMU imu;

    public NavigationHardware(HardwareMap map, NavType type, String name){

    }

    public double getHeading(){
        return 0;
    }

    public double getError(double targetAngle){
        double angleError = 0;

        angleError = (targetAngle - getHeading());
        angleError -= (360*Math.floor(0.5+((angleError)/360.0)));

        return angleError;
    }
}
