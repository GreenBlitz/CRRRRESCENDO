package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToTrap;
import edu.greenblitz.robotName.commands.climbing.lifter.ExtendLifter;
import edu.greenblitz.robotName.commands.climbing.lifter.RetractLifter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ScoreToTrap extends SequentialCommandGroup {
    public ScoreToTrap(){
        super(
                new MoveElbowAndWristToClimb(),
                new ExtendLifter(),
                new ReleaseNoteFromRollerToTrap(),
                new RetractLifter(),
                new MoveElbowAndWristToSafe()
        );
    }

}
