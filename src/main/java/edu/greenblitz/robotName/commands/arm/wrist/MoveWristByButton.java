package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveWristByButton extends WristCommand {
	
	private boolean isForward;
	public MoveWristByButton(boolean isForward){
		super();
		this.isForward = isForward;
	}
	
	@Override
	public void execute() {
		if (isForward) {
			wrist.setPower(WristConstants.HAND_CONTROL_POWER);
		}
		else{
			wrist.setPower(-WristConstants.HAND_CONTROL_POWER);
		}
	}
	
	
	
	
}
