package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class reverseRunFunnelByConstnat extends RunFunnelByPower {

    public reverseRunFunnelByConstnat() {
        super(FunnelConstants.EJECT_POWER);
    }
}
