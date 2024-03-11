package edu.greenblitz.robotName.commands.climbing.solenoid.basicPowerCommands;


import edu.greenblitz.robotName.commands.climbing.lifter.LifterCommand;
import edu.greenblitz.robotName.commands.climbing.solenoid.SolenoidCommand;
import edu.greenblitz.robotName.subsystems.climbing.lifter.LifterConstants;
import edu.wpi.first.wpilibj.Timer;

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
