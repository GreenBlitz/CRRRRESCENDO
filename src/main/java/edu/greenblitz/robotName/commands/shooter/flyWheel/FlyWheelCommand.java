package edu.greenblitz.robotName.commands.shooter.flyWheel;


import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class FlyWheelCommand extends GBCommand {

    protected FlyWheel flyWheel;

    public FlyWheelCommand() {
        flyWheel = FlyWheel.getInstance();
        require(flyWheel);
    }
}