package org.firstinspires.ftc.teamcode.hardware.bots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.subsystems.DriveFrame;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Grabbers;
import org.firstinspires.ftc.teamcode.hardware.subsystems.NavigationHardware;

/**
 * Created by Victo on 1/17/2018.
 */


public abstract class DogeBot {
    public HardwareMap hardwareMap;

    public double P = 0.1;
    public double I = 0.1;
    public double D = 0.1;
    public double PID_THRESH = 1;


    public DriveFrame driveFrame = null;
    public Grabbers grabbers = null;
    public NavigationHardware navigationHardware = null;

    public DogeBot(){

    }

    public abstract void Init();



}
