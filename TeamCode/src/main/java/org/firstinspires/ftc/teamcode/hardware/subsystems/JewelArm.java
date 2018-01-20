package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;

/**
 * Created by Victo on 1/20/2018.
 */

public class JewelArm {
    public Servo servo;
    public double UP_POS;
    public double DOWN_POS;

    public JewelArm(HardwareMap hwd, String name, double upPos, double downPos){
        servo = hwd.servo.get(name);

        UP_POS = upPos;
        DOWN_POS = downPos;
    }

    public void Up(){
        servo.setPosition(UP_POS);
    }
    public void Down(){
        servo.setPosition(DOWN_POS);
    }
}
