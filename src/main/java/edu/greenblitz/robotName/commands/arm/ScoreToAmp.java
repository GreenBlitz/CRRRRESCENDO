package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNote;
import edu.greenblitz.robotName.commands.arm.roller.RunRollerUntilObjectOut;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToAmp extends SequentialCommandGroup {
    public ScoreToAmp(){
        super(
                new MoveElbowAndWrist(
                        WristConstants.PresetPositions.SCORE_TO_AMP.ANGLE,
                        ElbowConstants.PresetPositions.SCORE_TO_AMP.ANGLE
                ),
                new ReleaseNote()
        );
    }
}
