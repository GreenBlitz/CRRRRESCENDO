package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ReleaseNoteFromRollerToTrap extends SequentialCommandGroup {

	public ReleaseNoteFromRollerToTrap(){
		super(
				new RunByPowerAndCountRotations(RollerConstants.SAFETY_ROTATIONS_TILL_OBJECT_OUT, false),
				new InstantCommand(() -> Roller.getInstance().setObjectOut())
		);
	}
}
