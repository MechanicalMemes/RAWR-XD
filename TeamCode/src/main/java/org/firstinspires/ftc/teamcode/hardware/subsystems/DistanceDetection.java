package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PWMOutput;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Victo on 1/22/2018.
 */

public class DistanceDetection {
    AnalogInput sensor;
    public DistanceDetection(HardwareMap hwd, String name){
        sensor = hwd.analogInput.get(name);
    }

    public double getDistance(){
        return getDistanceFromVoltage(sensor.getVoltage());
    }

    private double getDistanceFromVoltage(double voltage){
        return 0;
    }

}
