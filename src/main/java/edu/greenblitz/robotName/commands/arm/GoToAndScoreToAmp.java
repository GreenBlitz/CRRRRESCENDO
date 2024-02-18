package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.swerve.MoveToAmp;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class GoToAndScoreToAmp extends ParallelCommandGroup {

    public GoToAndScoreToAmp() {
        super(
                new ScoreToAmp(),
                new MoveToAmp()
        );
    }
}