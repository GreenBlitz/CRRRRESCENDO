package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.utils.GBCommand;

public class SystemCheckCommand extends GBCommand {

    private boolean hasFinished = false;

    @Override
    public void end(boolean interrupted) {
        if (!interrupted) {
            hasFinished = true;
        }
    }

    public boolean hasFinished() {
        return hasFinished;
    }
}
