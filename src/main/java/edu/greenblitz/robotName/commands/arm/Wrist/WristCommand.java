package edu.greenblitz.robotName.commands.arm.Wrist;

import edu.greenblitz.robotName.subsystems.Arm.Wrist;
import edu.greenblitz.robotName.utils.GBCommand;

public class WristCommand extends GBCommand {

    protected Wrist wrist;

    public WristCommand(){
        wrist = Wrist.getInstance();
        require(wrist);
    }

}
