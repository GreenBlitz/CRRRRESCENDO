package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;

public class RunFunnelByPower extends FunnelCommand {

    private final double power;

    public RunFunnelByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(power);
    }

}
