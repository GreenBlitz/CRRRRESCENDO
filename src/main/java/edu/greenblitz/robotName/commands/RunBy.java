package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.intake.neoIntake.NeoIntakeConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.neoFunnel.NeoFunnelConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunBy extends GBCommand {
	private Funnel funnel;
	
	public RunBy(){
		funnel = Funnel.getInstance();
		require(funnel);
		SmartDashboard.putNumber("voltage funnel", -3);
	}
	
	@Override
	public void execute() {
		funnel.setVoltage(NeoFunnelConstants.kS + SmartDashboard.getNumber("voltage funnel", 0));
	}
	
	@Override
	public void end(boolean interrupted) {
		funnel.stop();
	}
}
