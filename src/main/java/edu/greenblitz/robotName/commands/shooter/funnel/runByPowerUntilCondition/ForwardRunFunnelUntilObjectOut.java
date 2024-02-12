package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition;

import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;

public class ForwardRunFunnelUntilObjectOut extends RunFunnelByPowerUntilCondition {

    public ForwardRunFunnelUntilObjectOut() {
        super(FunnelConstants.ROLL_POWER, () -> !Funnel.getInstance().isObjectIn());
    }

}
