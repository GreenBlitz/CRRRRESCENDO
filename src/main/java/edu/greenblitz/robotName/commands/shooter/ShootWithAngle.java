package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;

public class ShootWithAngle extends GBCommand {

    private FlyWheel flyWheel;
    private Pivot pivot;
    public ShootWithAngle(double angle) {

    }
}
