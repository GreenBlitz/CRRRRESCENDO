package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;

public class RunIntakeByVelocityConstant extends IntakeCommand{

	@Override
	public void execute() {
		intake.setVelocity(FunnelConstants.INTAKE_VELOCITY * FunnelConstants.FUNNEL_TO_INTAKE_SPEED_CONVERSION);
	}

	@Override
	public void end(boolean interrupted) {
		intake.stop();
	}
}
