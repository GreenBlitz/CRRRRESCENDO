package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRoller;
import edu.greenblitz.robotName.commands.lifter.ExtendLifter;
import edu.greenblitz.robotName.commands.lifter.RetractLifter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToTrap extends SequentialCommandGroup {
    public ScoreToTrap(){
        super(
                new MoveElbowAndWristToClimb(),
                new ExtendLifter(),
                new ReleaseNoteFromRoller(),
                new RetractLifter(),
                new MoveElbowAndWristToSafe()
        );
    }

}
