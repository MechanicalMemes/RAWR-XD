package org.firstinspires.ftc.teamcode.lib.control;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Alex on 10/17/2017.
 */

public class Controller {

    Gamepad gamepad = null;

    public enum ButtonState {
        PRESSED, // Button State when Held Down. Constant.
        RELEASED,// Button State when Released / Up. Constant.
        JUST_PRESSED, // Button State when the button was prssed, This is called only once
        JUST_RELEASED // Button State when the button was released, this is called once.
    }
    public ButtonState AState = ButtonState.RELEASED;
    public ButtonState BState = ButtonState.RELEASED;
    public ButtonState XState = ButtonState.RELEASED;
    public ButtonState YState = ButtonState.RELEASED;

    public ButtonState DPadUp = ButtonState.RELEASED;
    public ButtonState DPadDown = ButtonState.RELEASED;
    public ButtonState DPadLeft = ButtonState.RELEASED;
    public ButtonState DPadRight = ButtonState.RELEASED;

    public ButtonState LeftBumper = ButtonState.RELEASED;
    public ButtonState RightBumper = ButtonState.RELEASED;
    public Controller(Gamepad _gamepad){
        gamepad = _gamepad;
    }

    public void Update (){
        // A
        if(gamepad.a){
            if(AState == ButtonState.RELEASED){
                AState = ButtonState.JUST_PRESSED;
            }else if(AState == ButtonState.JUST_PRESSED){
                AState = ButtonState.PRESSED;
            }
        }else{
            if(AState == ButtonState.PRESSED){
                AState = ButtonState.JUST_RELEASED;
            }else if(AState == ButtonState.JUST_RELEASED){
                AState = ButtonState.RELEASED;
            }
        }

        //B
        if(gamepad.b){
            if(BState == ButtonState.RELEASED){
                BState = ButtonState.JUST_PRESSED;
            }else if(BState == ButtonState.JUST_PRESSED){
                BState = ButtonState.PRESSED;
            }
        }else{
            if(BState == ButtonState.PRESSED){
                BState = ButtonState.JUST_RELEASED;
            }else if(BState == ButtonState.JUST_RELEASED){
                BState = ButtonState.RELEASED;
            }
        }

        //Y
        if(gamepad.y){
            if(YState == ButtonState.RELEASED){
                YState = ButtonState.JUST_PRESSED;
            }else if(YState == ButtonState.JUST_PRESSED){
                YState = ButtonState.PRESSED;
            }
        }else{
            if(YState == ButtonState.PRESSED){
                YState = ButtonState.JUST_RELEASED;
            }else if(YState == ButtonState.JUST_RELEASED){
                YState = ButtonState.RELEASED;
            }
        }

        //X
        if(gamepad.x){
            if(XState == ButtonState.RELEASED){
                XState = ButtonState.JUST_PRESSED;
            }else if(XState == ButtonState.JUST_PRESSED){
                XState = ButtonState.PRESSED;
            }
        }else{
            if(XState == ButtonState.PRESSED){
                XState = ButtonState.JUST_RELEASED;
            }else if(XState == ButtonState.JUST_RELEASED){
                XState = ButtonState.RELEASED;
            }
        }



        //X
        if(gamepad.dpad_up){
            if(DPadUp == ButtonState.RELEASED){
                DPadUp = ButtonState.JUST_PRESSED;
            }else if(DPadUp == ButtonState.JUST_PRESSED){
                DPadUp = ButtonState.PRESSED;
            }
        }else{
            if(DPadUp == ButtonState.PRESSED){
                DPadUp = ButtonState.JUST_RELEASED;
            }else if(DPadUp == ButtonState.JUST_RELEASED){
                DPadUp = ButtonState.RELEASED;
            }
        }

        if(gamepad.dpad_down){
            if(DPadDown == ButtonState.RELEASED){
                DPadDown = ButtonState.JUST_PRESSED;
            }else if(DPadDown == ButtonState.JUST_PRESSED){
                DPadDown = ButtonState.PRESSED;
            }
        }else{
            if(DPadDown == ButtonState.PRESSED){
                DPadDown = ButtonState.JUST_RELEASED;
            }else if(DPadDown == ButtonState.JUST_RELEASED){
                DPadDown = ButtonState.RELEASED;
            }
        }

        if(gamepad.dpad_left){
            if(DPadLeft == ButtonState.RELEASED){
                DPadLeft = ButtonState.JUST_PRESSED;
            }else if(DPadLeft == ButtonState.JUST_PRESSED){
                DPadLeft = ButtonState.PRESSED;
            }
        }else{
            if(DPadLeft == ButtonState.PRESSED){
                DPadLeft = ButtonState.JUST_RELEASED;
            }else if(DPadLeft == ButtonState.JUST_RELEASED){
                DPadLeft = ButtonState.RELEASED;
            }
        }

        if(gamepad.left_bumper){
            if(LeftBumper == ButtonState.RELEASED){
                LeftBumper = ButtonState.JUST_PRESSED;
            }else if(LeftBumper == ButtonState.JUST_PRESSED){
                LeftBumper = ButtonState.PRESSED;
            }
        }else{
            if(LeftBumper == ButtonState.PRESSED){
                LeftBumper = ButtonState.JUST_RELEASED;
            }else if(LeftBumper == ButtonState.JUST_RELEASED){
                LeftBumper = ButtonState.RELEASED;
            }
        }

        if(gamepad.right_bumper){
            if(RightBumper == ButtonState.RELEASED){
                RightBumper = ButtonState.JUST_PRESSED;
            }else if(RightBumper == ButtonState.JUST_PRESSED){
                RightBumper = ButtonState.PRESSED;
            }
        }else{
            if(RightBumper == ButtonState.PRESSED){
                RightBumper = ButtonState.JUST_RELEASED;
            }else if(RightBumper == ButtonState.JUST_RELEASED){
                RightBumper = ButtonState.RELEASED;
            }
        }


    }
}
