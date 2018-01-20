package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Victo on 1/20/2018.
 */

public class PhoneServo {
    public Servo servo;
    public double frontPos;
    public double pictoPos;
    public double jewelPos;

    public PhoneServo(HardwareMap hwd, String name, double positions[]){
        servo = hwd.servo.get(name);
        frontPos = positions[0];
        pictoPos = positions[1];
        jewelPos = positions[2];
    }

    public void setFrontPos(){
        servo.setPosition(frontPos);
    }

    public void setPictoPos(){
        servo.setPosition(pictoPos);
    }

    public  void setJewelPos(){
        servo.setPosition(jewelPos);
    }
}
