package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RunFunnelByPower extends FunnelCommand {

    private double power;

    public RunFunnelByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn();
    }

}
