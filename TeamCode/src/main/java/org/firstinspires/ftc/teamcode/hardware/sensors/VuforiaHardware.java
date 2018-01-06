package org.firstinspires.ftc.teamcode.hardware.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.ClosableVuforiaLocalizer;

/**
 * Created by Victo on 12/10/2017.
 */

public class VuforiaHardware {
    OpenGLMatrix lastLocation = null;

    ClosableVuforiaLocalizer vuforia;
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
    VuforiaTrackable relicTemplate;
    public void Init(HardwareMap hardwareMap){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AZGP7yb/////AAAAGemC2ySCWUjXovfSvQ/o1LxKDa+o33wYvGEXilQWfv0ZMexExgC4Z2BIptlGJUsyZgTx+OMG4nPn6O6rHYjDwuwsTIJPKghmLGpB5yuyZLWhzsVsleB0rwuc9gKSw3/Z6oMtT5t3gz64RN+fCt7ddT/m9yQwFTBfzSO0qPSKJwTTAgZ8WRFBcIRIFxSVyf5jb+m0u68JOZScx1EM5rwEWd134nja2NiNrQu/0udx+CNOm+UJXoTsS9CH8ohI7r+Hn4IyITTHsiQ09h5htsKAxlo6wgPonHwRoiRvsBIZrTdqW2j6s1EI/OzgG3MN5K23mEEhSjTqP2v+1m1GTcSrurOadgSlwe+YO9e5g12yTlPc";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = new ClosableVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate= relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        relicTrackables.activate();


    }

    public void Loop(){
        if(RelicRecoveryVuMark.from(relicTemplate) != RelicRecoveryVuMark.UNKNOWN){
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
        }
    }

    public void Stop(){
        vuforia.close();
    }

    public RelicRecoveryVuMark getVuMark() {
        return vuMark;
    }
}
