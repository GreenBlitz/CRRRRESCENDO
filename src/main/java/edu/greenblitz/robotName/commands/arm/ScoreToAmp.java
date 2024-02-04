package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.RunRollerUntilObjectOut;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToAmp extends SequentialCommandGroup {
    public ScoreToAmp(){
        super(
                new MoveElbowAndWrist(
                        WristConstants.PresetPositions.SCORE_TO_AMP.ANGLE,
                        ElbowConstants.PresetPositions.SCORE_TO_AMP.ANGLE
                ),
                new RunRollerUntilObjectOut()
        );
    }
}
