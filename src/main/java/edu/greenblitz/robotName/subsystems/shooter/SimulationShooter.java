package edu.greenblitz.robotName.subsystems.shooter;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationShooter implements IShooter{
    FlywheelSim shooterSim;
    private double appliedVoltage;

    public SimulationShooter(){
        shooterSim = new FlywheelSim(
                DCMotor.getNEO(ShoooterConstants.NUM_MOTORS),
                ShoooterConstants.GEARING,
                ShoooterConstants.J_KG_METERS_SQUARED
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
    public void updateInputs(ShooterInputs inputs) {
        shooterSim.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedVoltage;
        inputs.velocity = shooterSim.getAngularVelocityRPM();
    }



}
