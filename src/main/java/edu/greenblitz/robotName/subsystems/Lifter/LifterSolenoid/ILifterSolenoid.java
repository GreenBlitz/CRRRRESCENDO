package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid;

public interface ILifterSolenoid {

	void openSolenoid();

	void closeSolenoid();

	void holdSolenoid();

	void updateInputs(LifterSolenoidInputsAutoLogged inputs);
}
