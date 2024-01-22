package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimulationIntake implements IIntake {
    private DCMotorSim motorSimulation;
    private double appliedOutput;

    private SendableChooser<Boolean> entranceBeamBreaker;
    private SendableChooser<Boolean> exitBeamBreaker;

    public SimulationIntake() {
        motorSimulation = new DCMotorSim(
                DCMotor.getNEO(SimulationIntakeConstants.NUMBER_OF_MOTORS),
                SimulationIntakeConstants.GEAR_RATIO,
                SimulationIntakeConstants.MOMENT_OF_INERTIA
        );
        entranceBeamBreaker = new SendableChooser<>();
        entranceBeamBreaker.setDefaultOption("True", true);
        entranceBeamBreaker.addOption("False", false);
        SmartDashboard.putData("is object in intake", entranceBeamBreaker);
        exitBeamBreaker = new SendableChooser<>();
        exitBeamBreaker.setDefaultOption("True", true);
        exitBeamBreaker.addOption("False", false);
        SmartDashboard.putData("is object in intake", exitBeamBreaker);
    }


    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        motorSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void updateInputs(IntakeInputsAutoLogged intakeInputs) {
        intakeInputs.appliedOutput = appliedOutput;
        intakeInputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
        intakeInputs.velocity = motorSimulation.getAngularVelocityRPM();
        intakeInputs.entranceBeamBreakerValue = entranceBeamBreaker.getSelected();
        intakeInputs.exitBeamBreakerValue = exitBeamBreaker.getSelected();

    }
}
