package edu.greenblitz.robotName.subsystems.swerve.Modules.simulationSwerveModule;


import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.Modules.ISwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.SwerveModuleInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.Conversions;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.Logger;

public class SimulationSwerveModule implements ISwerveModule {

    private final DCMotorSim linearMotor;
    private final DCMotorSim angularMotor;
    private SwerveModuleInputsAutoLogged lastInputs = new SwerveModuleInputsAutoLogged();
    private final SwerveChassis.Module module;
    private final PIDController angularController = new PIDController(
            SimulationConstants.ANGULAR_PID_CONSTANTS.kP,
            SimulationConstants.ANGULAR_PID_CONSTANTS.kI,
            SimulationConstants.ANGULAR_PID_CONSTANTS.kD
    );

    private double angularAppliedVoltage, linearAppliedVoltage;

    public SimulationSwerveModule(SwerveChassis.Module module) {
        this.module = module;

        this.linearMotor = new DCMotorSim(
                DCMotor.getFalcon500(SimulationConstants.SIMULATION_LINEAR_MOTOR.NUMBER_OF_MOTORS),
                SimulationConstants.SIMULATION_LINEAR_MOTOR.GEAR_RATIO,
                SimulationConstants.SIMULATION_LINEAR_MOTOR.MOMENT_OF_INERTIA
                );
        this.angularMotor = new DCMotorSim(
                DCMotor.getFalcon500(SimulationConstants.SIMULATION_ANGULAR_MOTOR.NUMBER_OF_MOTORS),
                SimulationConstants.SIMULATION_ANGULAR_MOTOR.GEAR_RATIO,
                SimulationConstants.SIMULATION_ANGULAR_MOTOR.MOMENT_OF_INERTIA
        );
    }


    @Override
    public void setLinearVelocity(double speed) {
        final double power = speed / ChassisConstants.MAX_VELOCITY;
        final double voltage = power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
        setLinearVoltage(voltage);
    }

    @Override
    public void rotateToAngle(Rotation2d angle) {
        angularController.setSetpoint(angle.getRadians());
        final double voltage = angularController.calculate(lastInputs.angularPositionRadians);
        setAngularVoltage(voltage);
    }

    @Override
    public void setLinearVoltage(double voltage) {
        linearAppliedVoltage = voltage;
        linearMotor.setInputVoltage(voltage);
    }

    @Override
    public void setAngularVoltage(double voltage) {
        angularAppliedVoltage = voltage;
        angularMotor.setInputVoltage(voltage);
    }
    @Override
    public void stop() {
        setLinearVoltage(0);
        setAngularVoltage(0);
    }

    @Override
    public void updateInputs(SwerveModuleInputsAutoLogged inputs) {
        linearMotor.update(RobotConstants.SimulationConstants.TIME_STEP);
        angularMotor.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.linearVelocity = Conversions.MK4IConversions.convertRPMToMeterPerSecond(linearMotor.getAngularVelocityRPM()); // [m/s]
        inputs.angularVelocity = angularMotor.getAngularVelocityRadPerSec(); // [Rad/s]

        inputs.linearVoltage = linearAppliedVoltage;
        inputs.angularVoltage = angularAppliedVoltage;

        inputs.linearCurrent = linearMotor.getCurrentDrawAmps();
        inputs.angularCurrent = angularMotor.getCurrentDrawAmps();

        inputs.linearMetersPassed = Conversions.MK4IConversions.convertRevolutionToMeters(linearMotor.getAngularPositionRotations());
        inputs.angularPositionRadians = angularMotor.getAngularPositionRad();

        inputs.absoluteEncoderPosition = inputs.angularPositionRadians;
        inputs.isAbsoluteEncoderConnected = true;

        lastInputs = inputs;
    }


    @Override
    public void setLinearIdleModeBrake(boolean isBrake) {
        Logger.recordOutput("DriveTrain/Module"+module.name(), "tried setting linear idleMode to " + (isBrake ? "Brake" : "Coast"));

    }

    @Override
    public void setAngularIdleModeBrake(boolean isBrake) {
        Logger.recordOutput("DriveTrain/Module"+module.name(), "tried setting angular idleMode to " + (isBrake ? "Brake" : "Coast"));
    }

    @Override
    public void resetAngle(Rotation2d angle) {
        Logger.recordOutput("DriveTrain/Module"+module.name(), "tried setting the module angle to " + angle.getDegrees());
    }

}
