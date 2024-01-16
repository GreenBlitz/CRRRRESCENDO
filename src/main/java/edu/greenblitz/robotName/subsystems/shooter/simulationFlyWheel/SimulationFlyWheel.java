package edu.greenblitz.robotName.subsystems.shooter.simulationFlyWheel;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.ShooterInputs;
import edu.greenblitz.robotName.subsystems.shooter.ShooterInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationFlyWheel implements IFlyWheel {
    FlywheelSim shooterSim;
    private double appliedVoltage;

    public SimulationFlyWheel(){
        shooterSim = new FlywheelSim(
                DCMotor.getNEO(SimulationFlyWheelConstants.NUMBER_OF_MOTORS),
                SimulationFlyWheelConstants.GEARING,
                SimulationFlyWheelConstants.J_KG_METERS_SQUARED
        );
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        shooterSim.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setVelocity(double velocity) {
        //todo add
    }

    @Override
    public void updateInputs(ShooterInputsAutoLogged inputs) {
        shooterSim.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedVoltage;
        inputs.velocity = shooterSim.getAngularVelocityRPM();
    }



}
