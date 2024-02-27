package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ReleaseLifter extends ParallelDeadlineGroup {

	public ReleaseLifter() {
		super(
				new WaitCommand(LifterConstants.TIME_TO_RELEASE_LIFTER),
				new MoveLifterByPower(LifterConstants.POWER_TO_RELEASE_LIFTER)
		);
	}
}
