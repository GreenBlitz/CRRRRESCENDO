package edu.greenblitz.robotName.commands.swerve.rotateTo;

import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class RotateToSpeakerByCalculation extends RotateToAngle {

    public RotateToSpeakerByCalculation(){
        super(
                ShootingStateCalculations::getTargetRobotAngle
        );
    }
}
