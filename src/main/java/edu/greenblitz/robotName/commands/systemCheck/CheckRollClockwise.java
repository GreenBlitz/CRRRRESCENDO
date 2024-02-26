package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;

public class CheckRollClockwise extends SystemCheckCommand {

    protected Roller roller;

    public CheckRollClockwise() {
        roller = Roller.getInstance();
        require(roller);
    }

    @Override
    public void execute() {
        roller.rollPlus();
    }

    @Override
    public boolean isFinished(){
        return roller.getVoltage() > 0;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        roller.stop();
    }
}
