package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class ForwardRunFunnelUntilObjectOut extends RunFunnelByPowerAndCondition {

    public ForwardRunFunnelUntilObjectOut() {
        super(FunnelConstants.ROLL_POWER, () -> !Funnel.getInstance().isObjectIn());
    }

}
