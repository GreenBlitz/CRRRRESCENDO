package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;

public class ForwardRunFunnelUntilObjectIn extends RunFunnelByPowerUntilCondition {

    public ForwardRunFunnelUntilObjectIn() {
        super(FunnelConstants.ROLL_POWER, () -> Funnel.getInstance().isObjectIn());
    }

}
