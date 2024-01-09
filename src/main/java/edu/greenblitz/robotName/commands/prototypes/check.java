package edu.greenblitz.robotName.commands.prototypes;

import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.prototypes.Prototypes;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class check extends GBCommand {
	double numberOfMotors;
	
	@Override
	public void initialize() {
		numberOfMotors = TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.numberOfMotors).getValue();
		
	}
	
	@Override
	public void execute() {
		for (int i = 0; i < numberOfMotors; i++){
		
		}
	}
}
