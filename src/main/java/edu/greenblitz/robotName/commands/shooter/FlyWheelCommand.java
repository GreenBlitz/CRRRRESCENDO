package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class FlyWheelCommand extends GBCommand {

    protected FlyWheel flyWheelCommand;

    public FlyWheelCommand() {
        flyWheelCommand = FlyWheel.getInstance();
        require(flyWheelCommand);
    }
}
