package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;

public class ReverseRunFunnelUntilObjectOut extends RunFunnelByPowerUntilCondition {

    public ReverseRunFunnelUntilObjectOut() {
        super(
                FunnelConstants.EJECT_POWER,
                () -> !Funnel.getInstance().isObjectIn()
        );
    }
}