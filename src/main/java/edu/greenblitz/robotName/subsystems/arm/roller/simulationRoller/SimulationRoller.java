package edu.greenblitz.robotName.subsystems.arm.roller.simulationRoller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

public class SimulationRoller implements IRoller {

    private DCMotorSim rollerSimulation;

    private double appliedOutput;
    
    private PIDController pidController;

    private RollerInputsAutoLogged lastInputs;

    public SimulationRoller() {
        rollerSimulation = new DCMotorSim(
                DCMotor.getBag(SimulationRollerConstants.NUMBER_OF_MOTORS),
                SimulationRollerConstants.GEAR_RATIO,
                SimulationRollerConstants.MOMENT_OF_INERTIA
        );
        
        pidController = SimulationRollerConstants.SIMULATION_PID.getPIDController();

        lastInputs = new RollerInputsAutoLogged();
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(
                voltage,
                -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
        rollerSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoder(Rotation2d position) {
        Logger.recordOutput("Roller", "tried to reset the position to " + position);
    }

    @Override
    public void moveToPosition(Rotation2d position) {
        pidController.setSetpoint(position.getRadians());
        setVoltage(pidController.calculate(lastInputs.position.getRadians()));
    }

    @Override
    public void updateInputs(RollerInputsAutoLogged inputs) {
        rollerSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = rollerSimulation.getCurrentDrawAmps();
        inputs.position = Rotation2d.fromRadians(rollerSimulation.getAngularPositionRad());

        lastInputs = inputs;
    }
}