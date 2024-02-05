package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;

public class HoldNote extends FunnelCommand{
    private double power;

    public HoldNote(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
    }
}
