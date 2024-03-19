package edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands;

import edu.greenblitz.robotName.commands.climbing.solenoid.SolenoidCommand;

public class SolenoidHold extends SolenoidCommand {

    @Override
    public void execute() {
        solenoid.holdSolenoid();
    }
}
