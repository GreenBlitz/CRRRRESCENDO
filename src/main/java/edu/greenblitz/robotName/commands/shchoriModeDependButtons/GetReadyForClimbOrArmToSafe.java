package edu.greenblitz.robotName.commands.shchoriModeDependButtons;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.climbing.getLifterReady;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.*;

public class GetReadyForClimbOrArmToSafe extends ConditionalCommand {

    public GetReadyForClimbOrArmToSafe() {
        super(
                new ParallelCommandGroup(
                        new getLifterReady(),
                        new SequentialCommandGroup(
                                new ConditionalCommand(
                                        new TransferNote().raceWith(new WaitCommand(3)),
                                        new InstantCommand(),
                                        () -> !Roller.getInstance().isObjectIn()
                                ),
                                new MoveElbowToAngle(ElbowConstants.PresetPositions.CLIMB_MODE_ANGLE),
                                new MoveWristToAngle(WristConstants.PresetPositions.BEFORE_AND_AFTER_SCORE_TRAP)
                        )
                ),
                new MoveElbowAndWristToSafe(),
                ScoringModeSelector::isClimbMode
        );
    }

}
