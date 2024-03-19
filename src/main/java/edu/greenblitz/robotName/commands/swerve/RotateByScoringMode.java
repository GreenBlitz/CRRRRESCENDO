package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToAmp;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToSpeaker;
import edu.greenblitz.robotName.commands.swerve.rotateTo.RotateToAmp;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class RotateByScoringMode extends ConditionalCommand {

    public RotateByScoringMode() {
        super(
                new MoveByJoystickAndRotateToSpeaker(ChassisConstants.DRIVE_MODE),
                new MoveByJoystickAndRotateToAmp(ChassisConstants.DRIVE_MODE),
                ScoringModeSelector::isSpeakerMode
        );
    }
}
