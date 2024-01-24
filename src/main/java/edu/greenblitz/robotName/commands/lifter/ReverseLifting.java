package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class ReverseLifting extends LifterCommand{
    @Override
    public void initialize() {
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    @Override
    public void execute() {
        lifter.goToPositionByPID(LifterConstants.MOTOR_FINAL_POSITION_WHEN_REVERSE_LIFTING);
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorAtPosition(0);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stopMotor();
        new ResetEncoderBySwitch();
    }
}
