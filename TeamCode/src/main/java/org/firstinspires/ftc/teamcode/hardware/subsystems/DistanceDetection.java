package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PWMOutput;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Victo on 1/22/2018.
 */

public class DistanceDetection {
    AnalogInput sensor;

    public double lastDistance = -1;
    public int errorCount = 0;
    public DistanceDetection(HardwareMap hwd, String name){
        sensor = hwd.analogInput.get(name);
    }

    public double getDistance(){
        return getDistanceFromVoltage(sensor.getVoltage());
    }

    private double getDistanceFromVoltage(double voltage){

        voltage  = voltage ;
        double scale =  (3.3 / 1024);
        double dis =  voltage / scale;

        if(lastDistance == -1){
            lastDistance = dis;
        }

        if(Math.abs(lastDistance - dis) > 5){
            if(errorCount >= 3 ){
                errorCount = 0;
                lastDistance = dis;
            }else{
                errorCount++;
            }

        }else{
            errorCount = 0;
            lastDistance = dis;
        }

        return lastDistance;

    }

}
