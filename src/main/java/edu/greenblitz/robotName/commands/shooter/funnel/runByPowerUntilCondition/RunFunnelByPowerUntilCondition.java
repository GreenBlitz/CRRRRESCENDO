package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import java.util.function.Supplier;

public class RunFunnelByPowerUntilCondition extends RunFunnelByPower {

    private Supplier<Boolean> condition;

    public RunFunnelByPowerUntilCondition(double power, Supplier<Boolean> condition) {
        super(power);
        this.condition = condition;
    }

    @Override
    public boolean isFinished() {
        return condition.get();
    }
}