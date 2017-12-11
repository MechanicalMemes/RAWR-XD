package org.firstinspires.ftc.teamcode.hardware.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by kskrueger on 10/22/17.
 */

public class IMU {
    BNO055IMU imu;

    public void InitIMU(HardwareMap hwMap, String name){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwMap.get(BNO055IMU.class, name);
        imu.initialize(parameters);
    }

    public double getHeading(double offset) {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return (angles.firstAngle+360+offset)%360;
    }

    public double getError(double targetAngle){
        double angleError = 0;

        angleError = (targetAngle - getHeading(0));
        angleError -= (360*Math.floor(0.5+((angleError)/360.0)));

        return angleError;

    }
}