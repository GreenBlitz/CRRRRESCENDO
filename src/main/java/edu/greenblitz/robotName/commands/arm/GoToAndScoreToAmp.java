package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerClockwiseUntilObjectIsOut;
import edu.greenblitz.robotName.commands.swerve.MoveToAmp;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToAndScoreToAmp extends SequentialCommandGroup {

    public GoToAndScoreToAmp() {
        super(
                new ParallelCommandGroup(
                        new MoveToAmp(),
                        new MoveElbowAndWrist(
                                ElbowConstants.PresetPositions.SCORE.ANGLE,
                                WristConstants.PresetPositions.SCORE.ANGLE
                        )
                ),
                new RunRollerClockwiseUntilObjectIsOut()
        );
    }
}