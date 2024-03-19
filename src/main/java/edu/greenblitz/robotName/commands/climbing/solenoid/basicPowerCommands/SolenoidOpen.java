package edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands;

import edu.greenblitz.robotName.commands.climbing.solenoid.SolenoidCommand;

public class SolenoidOpen extends SolenoidCommand {

    @Override
    public void initialize() {
        solenoid.openSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
