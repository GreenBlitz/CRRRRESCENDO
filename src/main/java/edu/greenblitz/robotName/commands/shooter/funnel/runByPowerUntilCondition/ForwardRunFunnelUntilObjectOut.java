package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class ForwardRunFunnelUntilObjectOut extends RunFunnelByPowerUntilCondition {

    public ForwardRunFunnelUntilObjectOut() {
        super(FunnelConstants.ROLL_POWER, () -> !funnel.isObjectIn());
    }

}
