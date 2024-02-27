package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.greenblitz.robotName.commands.arm.roller.RollerCommand;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;

public class RunRollerClockwiseUntilObjectIsOut extends RollerCommand {

    @Override
    public void execute() {
        roller.rollClockwise();
    }

    @Override
    public boolean isFinished() {
        return !Funnel.getInstance().isObjectIn();
    }
}