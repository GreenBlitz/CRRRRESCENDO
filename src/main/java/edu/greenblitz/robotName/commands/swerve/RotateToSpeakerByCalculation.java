package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class RotateToSpeakerByCalculation extends RotateToAngle{

    public RotateToSpeakerByCalculation(){
        super(
                ShootingStateCalculations::getTargetRobotAngle
        );
    }
}
