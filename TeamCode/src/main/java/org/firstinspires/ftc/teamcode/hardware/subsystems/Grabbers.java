package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Victo on 1/17/2018.
 */

public class Grabbers {


    public Servo grabServos[]        = new Servo[4];
    public double positionOpen[]     = new double[4];
    public double positionSoftOpen[] = new double[4];
    public double positionClose[]    = new double[4];


    public Grabbers(HardwareMap hwd, String servoNames[], double openPos[], double softOpenPos[], double closePos[]){
       for(int i=0;i<grabServos.length;i++){
           grabServos[i] = hwd.servo.get(servoNames[i]);
       }

        this.positionOpen = openPos;
        this.positionSoftOpen = softOpenPos;
        this.positionClose = closePos;
    }


    public void openGrabbers(){
        for(int i=0;i<grabServos.length;i++){
            grabServos[i].setPosition(positionOpen[i]);
        }
    }

    public void closeGrabbers(){
        for(int i=0;i<grabServos.length;i++){
            grabServos[i].setPosition(positionClose[i]);
        }
    }

    public void softOpenGrabbers(){
        for(int i=0;i<grabServos.length;i++){
            grabServos[i].setPosition(positionSoftOpen[i]);
        }
    }



}
