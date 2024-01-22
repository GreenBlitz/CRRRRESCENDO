package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class ReverseLifting extends LifterCommand{
    @Override
    public void initialize() {
        lifter.resetEncoderTo(0);
        lifter.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void execute() {
        lifter.goToPosByPID(LifterConstants.MOTOR_FINAL_POSITION_WHEN_REVERSE_LIFTING);
    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorInPosition(0);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
