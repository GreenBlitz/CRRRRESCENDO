package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;

public class RunRollerByPower extends RollerCommand {

	private double power;

	public RunRollerByPower(double power){
		super();
		this.power = power;
	}

	@Override
	public void execute() {
		roller.setPower(power);
	}
}
