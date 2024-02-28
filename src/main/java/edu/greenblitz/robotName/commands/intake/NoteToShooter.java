package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerClockwiseUntilObjectIsOut;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooter extends SequentialCommandGroup {

    public NoteToShooter() {
        super(
                new NoteToIntake(),
                new NoteFromIntakeToShooter()//.alongWith(new RunRollerClockwiseUntilObjectIsOut())
        );
    }
}
