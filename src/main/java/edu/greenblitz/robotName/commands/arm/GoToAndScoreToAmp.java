package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.swerve.MoveToAmp;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToAndScoreToAmp extends SequentialCommandGroup {

    public GoToAndScoreToAmp() {
        super(
                new MoveToAmp(),
                new ScoreToAmp()
        );
    }
}