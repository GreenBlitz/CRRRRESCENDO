
package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;

import java.util.function.BooleanSupplier;


public class RunIntake extends IntakeCommand {

    BooleanSupplier isObjectInArmOrShooter;

    public RunIntake(BooleanSupplier isObjectInside) {
        super();
        isObjectInArmOrShooter = isObjectInside;
    }

    @Override
    public void execute() {
        intake.setPower(IntakeConstants.POWER_TO_RUN);
    }

    @Override
    public boolean isFinished() {
        return intake.getEntranceBeamBreakerValue() ||
                intake.getExitBeamBreakerValue() ||
                isObjectInArmOrShooter.getAsBoolean();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }

}

