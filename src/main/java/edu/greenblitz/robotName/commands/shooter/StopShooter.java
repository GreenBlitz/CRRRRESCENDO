package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.utils.GBCommand;

public class StopShooter extends FlyWheelCommand {

    @Override
    public void execute() {
        flyWheel.stop();
    }
}
