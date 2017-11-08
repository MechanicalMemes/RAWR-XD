package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.sun.tools.javac.comp.Check;

import java.lang.reflect.Method;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Victo on 10/23/2017.
 */

public class GamepadMapping {
    public enum BindType {
        LOOP,
        TOGGLE,
        HELD_DOWN,
        HELD_UP,
        PRESSED,
        RELEASED
    }

    public enum ButtonType {
        A,
        B,
        X,
        Y,
        DPAD_UP,
        DPAD_DOWN,
        DPAD_LEFT,
        DPAD_RIGHT,
        LEFT_BUMPER,
        RIGHT_BUMPER
    }
    public class Bind{
        public BindType type;
        public ButtonType button;
        public String toCall;

        public Bind(BindType _type, ButtonType _button, String _toCall){
            type = _type;
            button = _button;
            toCall = _toCall;
        }
    }

    public HashMap<ButtonType, List<Bind>> Binds = new HashMap<>();
    private Controller gamepad;
    private OpMode parent;

    public GamepadMapping(Controller _gamepad, OpMode _parent){
        gamepad = _gamepad;
        parent = _parent;
    }

    public void AddBind(BindType type,ButtonType button, String toCall){
        Bind newBind = new Bind(type,button,toCall);

        if(Binds.get(button) != null){
            Binds.get(button).add(newBind);
        }else{
            List<Bind> newList =  new ArrayList<>();
            newList.add(newBind);
            Binds.put(button,newList);
        }

    }

    public void Update(){
        gamepad.Update();

        for (int i=0;i<ButtonType.values().length;i++){
            ButtonType _button = ButtonType.values()[i];

            List<Bind> _binds = Binds.get(_button);

            if(_binds != null || _binds.size() > 0){
                Controller.ButtonState _bState = GetButtonState(_button);
                CheckBind(_bState, _binds);
            }
        }
    }

    private Controller.ButtonState GetButtonState(ButtonType _button){
        switch (_button){
            case A:
                return gamepad.AState;
            case B:
                return gamepad.BState;
        }
        return Controller.ButtonState.RELEASED;
    }

    private void CheckBind(Controller.ButtonState bState, List<Bind> binds){
        for(int i=0;i<binds.size();i++){
            Bind _bind = binds.get(i);
            switch (_bind.type){
                case PRESSED:
                    if(bState == Controller.ButtonState.JUST_PRESSED)
                        CallBind(_bind);
                    break;
                case RELEASED:
                    if(bState == Controller.ButtonState.JUST_RELEASED)
                        CallBind(_bind);
                    break;
                case HELD_DOWN:
                    if(bState == Controller.ButtonState.PRESSED)
                        CallBind(_bind);
                    break;
                case HELD_UP:
                    if(bState == Controller.ButtonState.RELEASED)
                        CallBind(_bind);
                    break;
            }
        }
    }

    private void CallBind(Bind bindToCall){
        try {
            parent.getClass().getMethod(bindToCall.toCall);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
