package edu.greenblitz.robotName.commands.shooter.funnel;


import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class RunFunnelByRollConstant extends RunFunnelByPower {

    public RunFunnelByRollConstant() {
        super(FunnelConstants.ROLL_POWER);
    }
}
