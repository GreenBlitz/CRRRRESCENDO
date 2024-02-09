package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class ReverseRunFunnelUntilObjectOut extends RunFunnelByPowerUntilCondition {

    public ReverseRunFunnelUntilObjectOut() {
        super(FunnelConstants.EJECT_POWER, () -> !funnel.isObjectIn());
    }
}
