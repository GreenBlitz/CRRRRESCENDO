package edu.greenblitz.robotName.commands.prototypes;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.prototypes.Prototypes;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;

import static edu.greenblitz.robotName.subsystems.Dashboard.*;

public class runMotors extends GBCommand {


	@Override
	public void execute() {
		for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
			int id = (int) TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id + i).getValue();
			double type = TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.type + i).getValue();
			double power = TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.power + i).getValue();
			if (id != DEFAULT_ID) {
				if (type == SPARK_MAX_TYPE) {
					Prototypes.getSparkMax(id).set(power);
				}
				else if (type == TALON_SRX_TYPE){
					Prototypes.getTalonSRX(id).set(TalonSRXControlMode.PercentOutput, power);
				}
				else if (type == TALON_FX_TYPE) {
					Prototypes.getTalonFX(id).set(power);
				}
			}
		}
	}

	@Override
	public void end(boolean interrupted) {
		for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
			int id = (int) TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id + i).getValue();
			double type = TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.type + i).getValue();
			if (id != DEFAULT_ID) {
				if (type == SPARK_MAX_TYPE) {
					Prototypes.getSparkMax(id).set(0);
				}
				else if (type == TALON_SRX_TYPE) {
					Prototypes.getTalonFX(id).set(0);
				}
				else if (type == TALON_FX_TYPE) {
					Prototypes.getTalonSRX(id).set(TalonSRXControlMode.PercentOutput, 0);
				}
			}
		}
	}
}
