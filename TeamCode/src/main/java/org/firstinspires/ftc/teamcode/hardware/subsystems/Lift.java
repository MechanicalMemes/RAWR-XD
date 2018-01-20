package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Victo on 1/20/2018.
 */

public class Lift {
    public DcMotor liftMotors[];
    public boolean usingEncoder = true;
    public int maxPos = 3000;
    public int minPos = 0;
    public Lift(HardwareMap map, String names[], double minPos, double maxPos){
        for(int i=0;i<names.length;i++){
            liftMotors[i] = map.dcMotor.get(names[i]);
            liftMotors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        maxPos = maxPos;
        minPos = minPos;
    }

    public Lift(HardwareMap map, String names[]){
        for(int i=0;i<names.length;i++){
            liftMotors[i] = map.dcMotor.get(names[i]);
        }
        usingEncoder = false;
    }


    public void setReverseMotor(int index){
        liftMotors[index].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetLift(){
        for(DcMotor motor : liftMotors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }
    public void setMode(DcMotor.RunMode mode){
        for(DcMotor motor : liftMotors){
            motor.setMode(mode);
        }
    }

    public void setTarget(int pos){
        for(DcMotor motor : liftMotors){
            motor.setTargetPosition(pos);
        }
    }

    public boolean isLiftBusy(){
        boolean isBusy = false;
        for(DcMotor motor : liftMotors){
            if(motor.isBusy()){
                isBusy = true;
            }
        }

        return isBusy;
    }

    public void liftInput(double input, boolean rawOverride){
        if(usingEncoder && !rawOverride){
            if(input > 0){
                for(DcMotor motor : liftMotors){
                    motor.setPower(input);
                }
            }else{
                boolean canDrop = true;
                for(DcMotor motor : liftMotors){
                    if(motor.getCurrentPosition() <= minPos){
                        canDrop = false;
                    }
                }

                if(canDrop){
                    for(DcMotor motor : liftMotors){
                        motor.setPower(input);
                    }
                }
            }
        }else{
            for(DcMotor motor : liftMotors){
                motor.setPower(input);
            }
        }
    }

}
