package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandGyroDrive;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandGyroTurn;
import org.firstinspires.ftc.teamcode.hardware.commands.CommandLiftToPosition;
import org.firstinspires.ftc.teamcode.hardware.sensors.VuforiaHardware;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAutoOpMode;


/**
 * Created by Victo on 1/20/2018.
 */

public class AutoRedBottom extends DogeAuto {
    public AutoRedBottom(DogeAutoOpMode parent) {
        super(parent);
    }

    public JewelDetector jewelDetector;
    public VuforiaHardware vuforiaHardware;

    private JewelDetector.JewelOrder jewelOrder;

    @Override
    public void Init() {

        bot.phoneServo.setFrontPos();

        jewelDetector = new JewelDetector();
        jewelDetector.downScaleFactor = 0.4;
        jewelDetector.ratioWeight = 30;
        jewelDetector.perfectRatio = 1.0;
        jewelDetector.areaWeight = 0.003;
        jewelDetector.maxDiffrence = 200;
        jewelDetector.debugContours = true;
        jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.PERFECT_AREA;
        jewelDetector.perfectArea = 5550;
        jewelDetector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance());

        vuforiaHardware = new VuforiaHardware();


        jewelDetector.enable();

        while(opMode.isStarted()){
            jewelOrder = jewelDetector.getLastOrder();
        }
    }

    @Override
    public void Run() {
        bot.phoneServo.setPictoPos();
        bot.jewelArm.Down();
        bot.grabbers.closeGrabbers();
        jewelDetector.disable();
        vuforiaHardware.Init(opMode.hardwareMap);

        new CommandLiftToPosition(opMode,500).Run();

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
        ElapsedTime timer = new ElapsedTime();
        while(vuMark == RelicRecoveryVuMark.UNKNOWN && timer.seconds() < 2.0){
            vuMark = vuforiaHardware.getVuMark();
        }

        if(jewelOrder == JewelDetector.JewelOrder.RED_BLUE){
            new CommandGyroTurn(opMode,0.3,10).Run();
            bot.jewelArm.Up();
            new CommandGyroTurn(opMode,0.3,0).Run();
        }

        new CommandGyroDrive(opMode,0.8,0,12).Run();
        new CommandGyroDrive(opMode,1.0,0,-3).Run();

        new CommandGyroDrive(opMode,1.0,0,24).Run();

        switch(vuMark){
            case RIGHT:
                new CommandGyroDrive(opMode,1.0,0,4).Run();
                break;
            case CENTER:
                new CommandGyroDrive(opMode,1.0,0,10).Run();
                break;
            case LEFT:
                new CommandGyroDrive(opMode,1.0,0,16).Run();
                break;
            case UNKNOWN:
                new CommandGyroDrive(opMode,1.0,0,10).Run();
                break;
        }


        new CommandGyroTurn(opMode, 0.7,90).Run();
        new CommandGyroDrive(opMode,0.5,90,5).Run();
        bot.grabbers.openGrabbers();
        new CommandGyroDrive(opMode, 0.8,90, -5).Run();
        new CommandGyroTurn(opMode, 1.0,-90);
        new CommandGyroDrive(opMode,0.6, -90,-2).Run();
        new CommandLiftToPosition(opMode,0);
        new CommandGyroDrive(opMode,1.0, -90,38).Run();
        bot.grabbers.closeGrabbers();
        bot.grabbers.openGrabbers();
        new CommandGyroDrive(opMode,0.6, -90,1).Run();
        bot.grabbers.closeGrabbers();
        new CommandLiftToPosition(opMode, bot.lift.maxPos - 300);
        new CommandGyroDrive(opMode,0.6, -90,-24).Run();
        new CommandGyroTurn(opMode,1.0,90).Run();
        new CommandGyroDrive(opMode, 0.5, 90, 16).Run();
        bot.grabbers.openGrabbers();
        new CommandLiftToPosition(opMode, bot.lift.maxPos).Run();
        new CommandGyroDrive(opMode, 0.3, 90, -6).Run();
        new CommandLiftToPosition(opMode,0);
        bot.grabbers.closeGrabbers();
        new CommandGyroDrive(opMode,0.5, 90, 5).Run();




    }

    @Override
    public void Stop() {

    }
}
