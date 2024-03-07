package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;

public class MoveNoteInRoller extends RollerCommand {
	
	private boolean isForward;
	
	public MoveNoteInRoller(boolean isForward){
		this.isForward = isForward;
	}
	
	@Override
	public void execute() {
		if (isForward){
			roller.setPower(RollerConstants.HAND_CONTROL_POWER);
		}
		else {
			roller.setPower(-RollerConstants.HAND_CONTROL_POWER);
		}
	}
	
	@Override
	public void end(boolean interrupted) {
		roller.stop();
	}
}
