package edu.greenblitz.robotName.commands.shooter.funnel;


import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;

public class RunFunnelByConstant extends RunFunnelByPower {

    public RunFunnelByConstant() {
        super(FunnelConstants.ROLL_POWER);
    }
}
