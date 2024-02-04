package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RunFunnelByPower extends FunnelCommand{

    private double power;

    private double rotationsSinceObjectExited;

    public RunFunnelByPower(double power) {
        this.power = power;
        rotationsSinceObjectExited = 0;
    }

    @Override
    public void initialize() {
        funnel.setPower(power);
    }

    @Override
    public void execute() {
        if (!funnel.isObjectIn()) {

        }
    }

    @Override
    public boolean isFinished() {
        return !funnel.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        new SequentialCommandGroup(
                new DoRotations(FunnelConstants.SAFETY_ROTATIONS_TILL_OBJECT_EXITED),
                new StopFunnel()
        );
    }
}
