package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.NeoFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.greenblitz.robotName.utils.tuneableNumber.AutoSetterTunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class NeoFlyWheel implements IFlyWheel {
    private GBSparkMax rightMotor, leftMotor;

    public NeoFlyWheel() {
        rightMotor = new GBSparkMax(NeoFlyWheelConstants.RightMotor.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightMotor.config(NeoFlyWheelConstants.RightMotor.CONFIG);
        leftMotor = new GBSparkMax(NeoFlyWheelConstants.LeftMotor.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftMotor.config(NeoFlyWheelConstants.LeftMotor.CONFIG);
        
    }

    @Override
    public void setPower(double rightPower, double leftPower) {
        rightMotor.set(rightPower);
        leftMotor.set(leftPower);
    }

    @Override
    public void setVoltage(double rightVoltage, double leftVoltage) {
        rightMotor.setVoltage(rightVoltage);
        leftMotor.setVoltage(leftVoltage);
    }

    @Override
    public void setVelocity(double rightVelocity, double leftVelocity) {
        rightMotor.getPIDController().setReference(
                rightVelocity,
                CANSparkMax.ControlType.kVelocity,
                NeoFlyWheelConstants.RightMotor.PID_SLOT,
                NeoFlyWheelConstants.RightMotor.FEEDFORWARD.calculate(rightVelocity)
        );
        leftMotor.getPIDController().setReference(
                leftVelocity,
                CANSparkMax.ControlType.kVelocity,
                NeoFlyWheelConstants.LeftMotor.PID_SLOT,
                NeoFlyWheelConstants.LeftMotor.FEEDFORWARD.calculate(leftVelocity)
        );
        
        
        SmartDashboard.putNumber("f l v",  NeoFlyWheelConstants.LeftMotor.FEEDFORWARD.calculate(leftVelocity));
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.appliedOutput = leftMotor.getAppliedOutput();
        inputs.velocity = leftMotor.getEncoder().getVelocity();
        inputs.position = leftMotor.getEncoder().getPosition();



        SmartDashboard.putNumber("left-velocity", leftMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("left-current", leftMotor.getOutputCurrent());
        SmartDashboard.putNumber("left-voltage", leftMotor.getAppliedOutput()  * Battery.getInstance().getCurrentVoltage());

        SmartDashboard.putNumber("right-velocity", rightMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("right-current", rightMotor.getOutputCurrent());
        SmartDashboard.putNumber("right-voltage", rightMotor.getAppliedOutput() * Battery.getInstance().getCurrentVoltage());
    }
}
