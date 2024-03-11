package edu.greenblitz.robotName.subsystems.climbing.solenoid;

public interface ISolenoid {

	void openSolenoid();

	void closeSolenoid();

	void holdSolenoid();

	void setPowerToSolenoid(double powerSolenoid);

	void updateInputs(SolenoidInputsAutoLogged inputs);

}
