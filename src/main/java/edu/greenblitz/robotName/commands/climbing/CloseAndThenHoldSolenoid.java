package edu.greenblitz.robotName.commands.climbing;

import edu.greenblitz.robotName.commands.climbing.lifter.PushSolenoidWheel;
import edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands.SolenoidClose;
import edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands.SolenoidHold;
import edu.greenblitz.robotName.subsystems.climber.solenoid.SolenoidConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class CloseAndThenHoldSolenoid extends SequentialCommandGroup {

	public CloseAndThenHoldSolenoid() {
		super(
				new PushSolenoidWheel()
		);
	}
}
