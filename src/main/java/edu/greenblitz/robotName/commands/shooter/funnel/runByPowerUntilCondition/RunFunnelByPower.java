package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;

public class RunFunnelByPower extends FunnelCommand {

    private final double power;

    public RunFunnelByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        FlyWheel.getInstance().stop();
    }
}