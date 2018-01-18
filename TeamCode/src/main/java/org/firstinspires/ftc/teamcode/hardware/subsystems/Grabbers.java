package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Victo on 1/17/2018.
 */

public class Grabbers {
    public Servo leftServoTop;
    public Servo leftServoBottom;
    public Servo rightServoTop;
    public Servo rightServoBottom;

    public double LEFT_TOP_OPEN;
    public double LEFT_BOTTOM_OPEN;
    public double RIGHT_TOP_OPEN;
    public double RIGHT_BOTTOM_OPEN;

    public double LEFT_TOP_SOFTOPEN;
    public double LEFT_BOTTOM_SOFTOPEN;
    public double RIGHT_TOP_SOFTOPEN;
    public double RIGHT_BOTTOM_SOFTOPEN;

    public double LEFT_TOP_CLOSE;
    public double LEFT_BOTTOM_CLOSE;
    public double RIGHT_TOP_CLOSE;
    public double RIGHT_BOTTOM_CLOSE;

    public Grabbers(HardwareMap hwd, String servoNames[], double openPos[], double softOpenPos[], double closePos[]){
        leftServoTop     = hwd.servo.get(servoNames[0]);
        leftServoBottom  = hwd.servo.get(servoNames[1]);
        rightServoTop    = hwd.servo.get(servoNames[2]);
        rightServoBottom = hwd.servo.get(servoNames[3]);

        LEFT_TOP_OPEN     = openPos[0];
        LEFT_BOTTOM_OPEN  = openPos[1];
        RIGHT_TOP_OPEN    = openPos[2];
        RIGHT_BOTTOM_OPEN = openPos[3];

        LEFT_TOP_SOFTOPEN     = softOpenPos[0];
        LEFT_BOTTOM_SOFTOPEN  = softOpenPos[1];
        RIGHT_TOP_SOFTOPEN    = softOpenPos[2];
        RIGHT_BOTTOM_SOFTOPEN = softOpenPos[3];

        LEFT_TOP_CLOSE     = closePos[0];
        LEFT_BOTTOM_CLOSE  = closePos[1];
        RIGHT_TOP_CLOSE    = closePos[2];
        RIGHT_BOTTOM_CLOSE = closePos[3];
    }


    public void openGrabbers(){
        leftServoTop.setPosition(LEFT_TOP_OPEN);
        leftServoBottom.setPosition(LEFT_BOTTOM_OPEN);
        rightServoTop.setPosition(RIGHT_TOP_OPEN);
        rightServoBottom.setPosition(RIGHT_BOTTOM_OPEN);
    }

    public void closeGrabbers(){
        leftServoTop.setPosition(LEFT_TOP_CLOSE);
        leftServoBottom.setPosition(LEFT_BOTTOM_CLOSE);
        rightServoTop.setPosition(RIGHT_TOP_CLOSE);
        rightServoBottom.setPosition(RIGHT_BOTTOM_CLOSE);
    }

    public void softOpenGrabbers(){
        leftServoTop.setPosition(LEFT_TOP_SOFTOPEN);
        leftServoBottom.setPosition(LEFT_BOTTOM_SOFTOPEN);
        rightServoTop.setPosition(RIGHT_TOP_SOFTOPEN);
        rightServoBottom.setPosition(RIGHT_BOTTOM_SOFTOPEN);
    }



}
