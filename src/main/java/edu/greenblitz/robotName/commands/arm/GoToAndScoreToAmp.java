package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerClockwiseUntilObjectIsOut;
import edu.greenblitz.robotName.commands.swerve.MoveToAmp;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToAndScoreToAmp extends ParallelCommandGroup {

    public GoToAndScoreToAmp() {
        super(
                new SequentialCommandGroup(
                        new MoveElbowAndWrist(
                                ElbowConstants.PresetPositions.SCORE,
                                WristConstants.PresetPositions.SCORE
                        ),
                        new RunRollerClockwiseUntilObjectIsOut()
                ),
                new MoveToAmp()

        );
    }
}