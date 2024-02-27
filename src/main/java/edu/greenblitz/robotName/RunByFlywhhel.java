package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunByFlywhhel extends FlyWheelCommand {
	
	public RunByFlywhhel(){
		super();
		SmartDashboard.putNumber("fly vel", 0);
	}
	
	
	@Override
	public void initialize() {
		flyWheel.setVelocity(SmartDashboard.getNumber("fly vel", 0), SmartDashboard.getNumber("fly vel", 0) * 0.6);
	}
	
	@Override
	public void end(boolean interrupted) {
		flyWheel.stop();
	}
}
