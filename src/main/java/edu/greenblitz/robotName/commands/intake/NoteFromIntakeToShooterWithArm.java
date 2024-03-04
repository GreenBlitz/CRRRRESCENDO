package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollClockwise;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollCounterClockwise;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteFromIntakeToShooterWithArm extends ParallelDeadlineGroup {

    public NoteFromIntakeToShooterWithArm() {
        super(
                new NoteFromIntakeToShooter(),
                new MoveElbowAndWristToSafe(),
                new RollCounterClockwise()
        );
    }

}
