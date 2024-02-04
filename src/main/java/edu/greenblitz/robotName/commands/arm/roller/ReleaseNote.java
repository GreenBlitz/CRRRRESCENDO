package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ReleaseNote extends SequentialCommandGroup {

    public ReleaseNote(){
        super(
                new RunRollerUntilObjectOut(),
                new WaitCommand(RollerConstants.TIME_UNTIL_NOTE_EXIT_ROLLER),
                new StopRoller()
        );
    }
}
