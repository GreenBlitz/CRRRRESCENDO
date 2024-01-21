package edu.greenblitz.robotName.subsystems.Arm.Wrist;


import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.PIDController;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.Wrist.WristConstants.TOLERANCE;

public class Wrist extends GBSubsystem {

    private static Wrist instance;

    private WristInputsAutoLogged wristInputs;

    private IWrist wrist;

    private PIDController controller;
  
    private double goalAngle;


    public static void init() {
        if (instance == null)
            instance = new Wrist();
    }

    public static Wrist getInstance() {
        init();
        return instance;
    }

    private Wrist() {
        wrist = WristFactory.create();
        wristInputs = new WristInputsAutoLogged();
        wrist.updateInputs(wristInputs);
        goalAngle = getAngleInRadians();
        controller = new PIDController(wristInputs.kP,wristInputs.kI,wristInputs.kD);
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isAtAngle(goalAngle))
            standInPlace();
        else
            moveToAngle();
        wrist.updateInputs(wristInputs);
        Logger.processInputs("Pivot", wristInputs);
    }


    public void setPower(double power) {
        wrist.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        wrist.setVoltage(voltage);
    }

    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        wrist.setIdleMode(idleMode);
    }

    public void setGoalAngle(double targetAngle) {
        goalAngle = targetAngle;
    }

    public void resetAngle(double position) {
        wrist.resetAngle(position);
        goalAngle = position;
    }

    private void moveToAngle() {
        wrist.moveToAngle(goalAngle);
    }

    private void standInPlace() {
        wrist.setPower(getStaticFF());
    }


    public double getStaticFF() {
        return controller.calculate(0);
    }

    public double getDynamicFF(double velocity) {
        return controller.calculate(velocity);
    }

    public double getVoltage() {
        return wristInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return wristInputs.velocity;
    }

    public double getAngleInRadians() {
        return wristInputs.position;
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
