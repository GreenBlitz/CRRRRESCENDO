package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerClockwiseUntilObjectIsOut;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToAmp extends SequentialCommandGroup {

    public ScoreToAmp() {
        super(
                new MoveElbowAndWrist(
                        ElbowConstants.PresetPositions.SCORE.ANGLE,
                        WristConstants.PresetPositions.SCORE.ANGLE
                ),
                new RunRollerClockwiseUntilObjectIsOut()
        );
    }
}
