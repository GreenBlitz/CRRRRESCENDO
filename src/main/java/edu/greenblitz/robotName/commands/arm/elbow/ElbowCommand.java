package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class ElbowCommand extends GBCommand {

    protected Elbow elbow;

    public ElbowCommand() {
        elbow = Elbow.getInstance();
        require(elbow);
    }

    public void end(boolean interrupted) {
//        elbow.setCurrentAngle();
//        elbow.standInPlace();
    }
}