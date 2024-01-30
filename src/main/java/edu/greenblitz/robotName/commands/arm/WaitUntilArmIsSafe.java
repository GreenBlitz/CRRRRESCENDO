package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class WaitUntilArmIsSafe extends GBCommand {
    @Override
    public boolean isFinished() {
        return !Elbow.getInstance().isInShooterCollisionRange();
    }
}
