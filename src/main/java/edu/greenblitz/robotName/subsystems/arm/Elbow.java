package edu.greenblitz.robotName.subsystems.arm;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowFactory;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.FalconElbow.FalconElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.IElbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.SimulationElbow.SimulationElbowConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.Logger;

public class Elbow extends GBSubsystem {

    private static Elbow instance;

    private IElbow elbow;

    private ElbowInputsAutoLogged elbowInputs;


    public static void init() {
        if (instance == null) {
            instance = new Elbow();
        }
    }

    public static Elbow getInstance() {
        init();
        return instance;
    }

    private Elbow() {
        elbow = ElbowFactory.create();
        elbowInputs = new ElbowInputsAutoLogged();
        elbow.updateInputs(elbowInputs);
    }

    @Override
    public void periodic() {
        super.periodic();

        elbow.updateInputs(elbowInputs);
        Logger.processInputs("Elbow", elbowInputs);
        Logger.recordOutput("Elbow", getPose3D());
    }


    public void setPower(double power) {
        elbow.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        elbow.setVoltage(voltage);
    }

    public void setIdleMode(NeutralModeValue idleMode) {
        elbow.setIdleMode(idleMode);
    }


    public void resetAngle(Rotation2d position) {
        elbow.resetAngle(position);
    }

    public void moveToAngle(Rotation2d targetAngle) {
        elbow.moveToAngle(targetAngle);
    }

	public void standInPlace() {
		if (Robot.isSimulation()){
            elbow.setPower(0);
        }
        else{
            elbow.setPower(getStaticFeedForward());
        }
	}


    public double getStaticFeedForward() {
        return FalconElbowConstants.SIMPLE_MOTOR_FEED_FORWARD.calculate(0);
    }

    public double getDynamicFeedForward(double velocity) {
        return FalconElbowConstants.SIMPLE_MOTOR_FEED_FORWARD.calculate(velocity);
    }

    public double getVoltage() {
        return elbowInputs.appliedOutput;
    }

    public double getVelocity() {
        return elbowInputs.velocity;
    }

    public double getAngleInRadians() {
        return elbowInputs.position;
    }

    public boolean isAtAngle(Rotation2d targetHeight) {
        return Math.abs(targetHeight.getRadians() - getAngleInRadians()) <= ElbowConstants.TOLERANCE;
    }

	public boolean isInShooterCollisionRange() {
		return elbowInputs.position > ElbowConstants.SHOOTER_COLLISION_RANGE.getFirst().getRadians() &&
				elbowInputs.position < ElbowConstants.SHOOTER_COLLISION_RANGE.getSecond().getRadians();
	}

    public Pose3d getPose3D (){
        return new Pose3d(
                ElbowConstants.ELBOW_POSITION_RELATIVE_TO_ROBOT,
                new Rotation3d(elbowInputs.position + SimulationElbowConstants.SIMULATION_OFFSET,0, 0)
        );
    }


}
