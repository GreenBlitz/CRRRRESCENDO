package edu.greenblitz.robotName.commands.prototypes;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.prototypes.Prototypes;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;

import static edu.greenblitz.robotName.subsystems.Dashboard.*;
import static edu.greenblitz.robotName.subsystems.Dashboard.motorType.SPARK_MAX;
import static edu.greenblitz.robotName.subsystems.Dashboard.motorType.TALON_SRX;

public class runMotors extends GBCommand {


	@Override
	public void execute() {
		double power;
		int id;
		for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
			id = (int) TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id + i).getValue();
			if (id != DEFAULT_ID) {
				power = TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.power + i).getValue();
				Object selected = types.get(i).getSelected();
				if (selected.equals(SPARK_MAX)) {
					Prototypes.getSparkMax(id).set(power);
				}
				else if (selected.equals(TALON_SRX)) {
					Prototypes.getTalonSRX(id).set(TalonSRXControlMode.PercentOutput, power);
				}
				else {
					Prototypes.getTalonFX(id).set(power);
				}
			}
		}
	}

	@Override
	public void end(boolean interrupted) {
		for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
			int id = (int) TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id + i).getValue();
			if (id != DEFAULT_ID) {
				Object selected = types.get(i).getSelected();
				if (selected.equals(SPARK_MAX)) {
					Prototypes.getSparkMax(id).set(0);
				}
				else if (selected.equals(TALON_SRX)) {
					Prototypes.getTalonSRX(id).set(TalonSRXControlMode.PercentOutput, 0);
				}
				else {
					Prototypes.getTalonFX(id).set(0);
				}
			}
		}
	}
}
