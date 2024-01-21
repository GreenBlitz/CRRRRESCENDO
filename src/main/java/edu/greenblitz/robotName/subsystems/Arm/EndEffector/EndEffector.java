package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.PIDController;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.EndEffector.EndEffectorConstants.TOLERANCE;


public class EndEffector extends GBSubsystem {

    private static EndEffector instance;

    private EndEffectorInputsAutoLogged endEffectorInputs;

    private IEndEffector endEffector;

    private PIDController controller;

    private double goalAngle;


    public static void init() {
        if (instance == null)
            instance = new EndEffector();
    }

    public static EndEffector getInstance() {
        init();
        return instance;
    }

    private EndEffector() {
        endEffector = EndEffectorFactory.create();
        endEffectorInputs = new EndEffectorInputsAutoLogged();
        endEffector.updateInputs(endEffectorInputs);
        goalAngle = getAngleInRadians();
        controller = new PIDController(endEffectorInputs.kP,endEffectorInputs.kI,endEffectorInputs.kD);
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle))
            standInPlace();
        else
            moveToAngle();
        endEffector.updateInputs(endEffectorInputs);
        Logger.processInputs("Pivot", endEffectorInputs);
    }


    public void setPower(double power) {
        endEffector.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        endEffector.setVoltage(voltage);
    }

    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        endEffector.setIdleMode(idleMode);
    }

    public void setGoalAngle(double targetAngle) {
        goalAngle = targetAngle;
    }

    public void resetAngle(double position) {
        endEffector.resetAngle(position);
        goalAngle = position;
    }

    private void moveToAngle() {
        endEffector.moveToAngle(goalAngle);
    }

    private void standInPlace() {
        endEffector.setPower(getStaticFF());
    }


    public double getStaticFF() {
        return controller.calculate(0);
    }

    public double getDynamicFF(double velocity) {
        return controller.calculate(velocity);
    }

    public double getVoltage() {
        return endEffectorInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return endEffectorInputs.velocity;
    }

    public double getAngleInRadians() {
        return endEffectorInputs.position;
    }

    public double getGoalAngleRadians() {
        return goalAngle;
    }

    public boolean isAtAngle(double angle) {
        return Math.abs(angle - getAngleInRadians()) <= TOLERANCE;
    }

    public boolean isAtGoalAngle() {
        return isAtAngle(goalAngle);
    }



}
