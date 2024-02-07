package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition;

import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition.RunFunnelByPowerAndCondition;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class ReverseRunFunnelUntilObjectOut extends RunFunnelByPowerAndCondition {

    public ReverseRunFunnelUntilObjectOut() {
        super(FunnelConstants.EJECT_POWER, () -> !Funnel.getInstance().isObjectIn());
    }
}
