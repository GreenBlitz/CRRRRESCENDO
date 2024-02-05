package edu.greenblitz.robotName.commands.shooter;

public class Kicker extends FunnelCommand{

    private double power;

    public Kicker(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(-power);
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
    }
}
