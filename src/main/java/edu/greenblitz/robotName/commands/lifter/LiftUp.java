package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class LiftUp extends LifterCommand {

    @Override
    public void initialize() {
        lifter.resetEncoderTo(0);
        lifter.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public void execute() {

        lifter.goToPosByPID(LifterConstants.MOTOR_FINAL_POSITION_WHEN_LIFTING_UP);

    }

    @Override
    public boolean isFinished() {
        return lifter.isMotorInPosition(LifterConstants.MOTOR_FINAL_POSITION_WHEN_LIFTING_UP);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
