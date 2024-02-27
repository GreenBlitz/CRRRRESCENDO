package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunBy extends GBCommand {

	FlyWheel flyWheel;

	public RunBy(){
		flyWheel = FlyWheel.getInstance();
		require(flyWheel);
		SmartDashboard.putNumber("powerFlywhell", 0);
	}

	@Override
	public void execute() {
		flyWheel.setPower(SmartDashboard.getNumber("powerFlywhell", 0),
				SmartDashboard.getNumber("powerFlywhell", 0)
		);
	}

	@Override
	public void end(boolean interrupted) {
		flyWheel.stop();
	}
}
