package edu.greenblitz.robotName.commands.Climb.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.Lifter;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public abstract class LifterCommand extends GBCommand {
    protected Lifter lifter;
    protected LifterSolenoid lifterSolenoid;
    protected Timer timer;

    public LifterCommand() {
        lifter = Lifter.getInstance();
        lifterSolenoid = LifterSolenoid.getInstance();
        timer = new Timer();
        require(lifter);
        require(lifterSolenoid);
    }
}
