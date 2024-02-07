package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.utils.GBCommand;

public class WristCommand extends GBCommand {

    protected Wrist wrist;

    public WristCommand(){
        wrist = Wrist.getInstance();
        require(wrist);
    }

    public void end(boolean interrupted) {
        wrist.setCurrentAngle();
    }
}
