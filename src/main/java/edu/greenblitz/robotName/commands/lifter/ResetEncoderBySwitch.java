package edu.greenblitz.robotName.commands.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;

public class ResetEncoderBySwitch extends LifterCommand {
    @Override
    public void initialize() {
        lifter.setDestination(LifterConstants.LIFTER_RETRACTED_POSITION);
        lifter.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    @Override
    public boolean isFinished() {
        return lifter.isSwitchPressed();
    }

    @Override
    public void end(boolean interrupted) {
        lifter.resetEncoder();
        lifter.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
