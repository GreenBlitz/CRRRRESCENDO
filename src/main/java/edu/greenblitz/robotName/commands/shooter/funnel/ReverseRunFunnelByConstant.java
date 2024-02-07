package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class ReverseRunFunnelByConstant extends RunFunnelByPower {

    public ReverseRunFunnelByConstant() {
        super(FunnelConstants.EJECT_POWER);
    }
}
