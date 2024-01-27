package edu.greenblitz.robotName.subsystems.shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.Logger;


import static edu.greenblitz.robotName.subsystems.shooter.Pivot.FalconPivot.FalconPivotConstants.SIMPLE_MOTOR_FF;
import static edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants.TOLERANCE;


public class Pivot extends GBSubsystem {

    private static Pivot instance;

    private PivotInputsAutoLogged pivotInputs;

    private IPivot pivot;



    public static void init() {
        if (instance == null)
            instance = new Pivot();
    }

    public static Pivot getInstance() {
        init();
        return instance;
    }

    private Pivot() {
        pivot = PivotFactory.create();
        pivotInputs = new PivotInputsAutoLogged();
        pivot.updateInputs(pivotInputs);
    }

    @Override
    public void periodic() {
        super.periodic();

        pivot.updateInputs(pivotInputs);
        Logger.processInputs("Pivot", pivotInputs);
    }


    public void setPower(double power) {
        pivot.setPower(power);
    }

    public void setMotorVoltage(double voltage) {
        pivot.setVoltage(voltage);
    }

    public void setIdleMode(NeutralModeValue idleMode) {
        pivot.setIdleMode(idleMode);
    }
    

    public void resetAngle(Rotation2d position) {
        pivot.resetAngle(position);
    }

    public void moveToAngle(Rotation2d targetAngle) {
        pivot.moveToAngle(targetAngle);
    }

    public void standInPlace() {
        if (RobotConstants.ROBOT_TYPE == Robot.RobotType.SIMULATION) {
            pivot.setPower(0);
        }
        else {
            pivot.setPower(getStaticFeedForward());
        }
    }



    public double getStaticFeedForward() {
        return SIMPLE_MOTOR_FF.calculate(0);
    }

    public double getDynamicFeedForward(double velocity) {
        return SIMPLE_MOTOR_FF.calculate(velocity);
    }

    public double getVoltage() {
        return pivotInputs.appliedOutput * Battery.getInstance().getCurrentVoltage();
    }

    public double getVelocity() {
        return pivotInputs.velocity;
    }

    public double getAngleInRadians() {
        return pivotInputs.position;
    }
    
    public boolean isAtAngle(Rotation2d angle) {
        return Math.abs(angle.getRadians() - getAngleInRadians()) <= TOLERANCE;
    }
    

}
