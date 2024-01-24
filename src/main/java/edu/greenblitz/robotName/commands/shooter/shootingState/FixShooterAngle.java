package edu.greenblitz.robotName.commands.shooter.shootingState;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle.MovePivotToAngle;
import edu.greenblitz.robotName.shootingStateService.ShootingState;

public class FixShooterAngle extends PivotCommand {
    boolean simulation;
    protected FixShooterAngle(){
        super();
        this.simulation = false;
    }

    public FixShooterAngle(boolean simulation) {
        super();
        this.simulation = simulation;
    }
    @Override
    public void execute() {
        new MovePivotToAngle(ShootingState.getInstance().getShooterTargetAngle().getDegrees(), simulation);
    }
}
