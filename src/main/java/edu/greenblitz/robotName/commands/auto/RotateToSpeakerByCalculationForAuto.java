package edu.greenblitz.robotName.commands.auto;

import edu.greenblitz.robotName.commands.swerve.rotateTo.RotateToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class RotateToSpeakerByCalculationForAuto extends RotateToAngle {

    public RotateToSpeakerByCalculationForAuto(){
        super(
                ShootingStateCalculations::getTargetRobotAngleAuto
        );
    }
}
