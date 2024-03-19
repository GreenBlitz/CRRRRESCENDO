package edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands;


import edu.greenblitz.robotName.commands.climbing.solenoid.SolenoidCommand;

public class SolenoidClose extends SolenoidCommand {


    @Override
    public void initialize() {
        solenoid.closeSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
