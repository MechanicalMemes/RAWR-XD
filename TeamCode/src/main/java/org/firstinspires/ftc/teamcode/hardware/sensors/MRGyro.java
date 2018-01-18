package org.firstinspires.ftc.teamcode.hardware.sensors;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.subsystems.NavigationHardware;

/**
 * Created by Victo on 1/17/2018.
 */

public class MRGyro extends NavigationHardware {
    public GyroSensor mrGyro;
    public MRGyro(HardwareMap map, String name) {
        super(map, name);
        mrGyro = map.gyroSensor.get(name);

        mrGyro.calibrate();
    }

    @Override
    public double getHeading() {
        return (mrGyro.getHeading()+360)%360;
    }

    @Override
    public double getError(double targetAngle) {
        double angleError = 0;

        angleError = (targetAngle - getHeading());
        angleError -= (360*Math.floor(0.5+((angleError)/360.0)));

        return angleError;
    }
}
