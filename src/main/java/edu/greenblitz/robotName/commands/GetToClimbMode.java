package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.ScoringMode;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.climbing.getLifterReady;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GetToClimbMode extends SequentialCommandGroup {
    public GetToClimbMode(){
        super(
                new SetScoringMode(ScoringMode.CLIMB),
                new ParallelCommandGroup(
                        new getLifterReady(),
                        new SequentialCommandGroup(
                                new TransferNote(),
                                new MoveElbowToAngle(ElbowConstants.PresetPositions.CLIMB_MODE_ANGLE),
                                new MoveWristToAngle(WristConstants.PresetPositions.BEFORE_AND_AFTER_SCORE_TRAP)
                        )
                )
        );
    }

}
