package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.commands.intake.IntakeCommand;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.intake.IntakeConstants;
import edu.greenblitz.robotName.subsystems.intake.neoIntake.NeoIntakeConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel.NeoFunnelConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunFunnelByVelocity extends FunnelCommand {
	
	private double velocity;
	
	public RunFunnelByVelocity(double velocity){
		super();
		this.velocity = velocity;
	}
	
	@Override
	public void initialize() {
		funnel.setVelocity(velocity);
		SmartDashboard.putString("run funnel", "started");
	}
	
	@Override
	public void end(boolean interrupted) {
		SmartDashboard.putString("run funnel", "ended");
		funnel.stop();
	}
}
