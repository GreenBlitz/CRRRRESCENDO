package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.Lifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;

public class RetractLifterWithoutStopUnlessInterrupted extends LifterCommand {

    @Override
    public void initialize() {
        lifter.setMaxCurrentToZero();
    }

    @Override
    public void execute() {
        lifter.setPower(LifterConstants.POWER_TO_RETRACT_LIFTER);
    }

    @Override
    public boolean isFinished() {
        return lifter.hasPassedPositionWhenRetractLifter(LifterConstants.LIFTER_RETRACTED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted)
            lifter.stop();
    }

}
