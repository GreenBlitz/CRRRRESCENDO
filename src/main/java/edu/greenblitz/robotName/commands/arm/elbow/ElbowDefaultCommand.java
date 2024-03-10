package edu.greenblitz.robotName.commands.arm.elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElbowDefaultCommand extends ElbowCommand {
	
	@Override
	public void initialize() {
		elbow.setCurrentAngle();
	}
	
	@Override
	public void execute() {
		if (ScoringModeSelector.isClimbMode()) {
			elbow.setIdleMode(NeutralModeValue.Brake);
			SmartDashboard.putBoolean("is elbow running", true);
			elbow.setPower(0);
		}
		else {
			elbow.setIdleMode(NeutralModeValue.Coast);
			elbow.standInPlace();
		}
	}
}