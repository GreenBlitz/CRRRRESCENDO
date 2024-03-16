package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;

public class RotateToSpeakerWhileMove extends MoveByJoystickAndAngleSupplier{

    public RotateToSpeakerWhileMove(){
        super(
                ChassisConstants.DRIVE_MODE
        );
    }
}
