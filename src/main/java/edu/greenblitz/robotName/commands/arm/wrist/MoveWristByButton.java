package edu.greenblitz.robotName.commands.arm.wrist;

import edu.wpi.first.math.geometry.Rotation2d;

public class MoveWristByButton extends WristCommand {
	
	boolean isForward;
	
	double power = 0.2;
	
	public MoveWristByButton(boolean isForward){
		super();
		this.isForward = isForward;
	}
	
	@Override
	public void execute() {
		if (!isForward) {
			wrist.setPower(-power);
		}
		else{
			wrist.setPower(power);
		}
	}
	
	
	
	
}
