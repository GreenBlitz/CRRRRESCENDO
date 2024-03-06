package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;

public class MoveNoteInRoller extends RollerCommand {
	
	private boolean isForward;
	
	public MoveNoteInRoller(boolean isForward){
		this.isForward = isForward;
	}
	
	@Override
	public void execute() {
		if (isForward){
			roller.setPower(0.2);
		}
		else {
			roller.setPower(-0.2);
		}
	}
	
	@Override
	public void end(boolean interrupted) {
		roller.stop();
	}
}
