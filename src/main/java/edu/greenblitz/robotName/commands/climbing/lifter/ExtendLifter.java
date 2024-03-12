package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.solenoid.Solenoid;

public class ExtendLifter extends LifterCommand {

    private Solenoid solenoid;

    public ExtendLifter(){
        super();
        solenoid = Solenoid.getInstance();
        require(solenoid);
    }

    @Override
    public void execute() {
        lifter.setPower(LifterConstants.POWER_TO_EXTEND_LIFTER);
        solenoid.holdSolenoid();
    }


    @Override
    public boolean isFinished() {
        return lifter.hasPassedPositionWhenExtendLifter(LifterConstants.LIFTER_EXTENDED_POSITION);
    }

    @Override
    public void end(boolean interrupted) {
        lifter.stop();
        solenoid.openSolenoid();
    }
}