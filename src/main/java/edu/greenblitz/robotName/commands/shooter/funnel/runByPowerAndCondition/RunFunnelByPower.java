package edu.greenblitz.robotName.commands.shooter.funnel.runByPowerAndCondition;

import edu.greenblitz.robotName.commands.shooter.funnel.FunnelCommand;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RunFunnelByPower extends FunnelCommand {

    private final double power;

    public RunFunnelByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(power);
    }

}
