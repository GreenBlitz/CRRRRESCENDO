package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition;

import java.util.function.Supplier;

public class RunFunnelByPowerAndCondition extends RunFunnelByPower {

    private Supplier<Boolean> condition;

    public RunFunnelByPowerAndCondition(double power, Supplier<Boolean> condition) {
        super(power);
        this.condition = condition;
    }

    @Override
    public boolean isFinished() {
        return condition.get();
    }
}
