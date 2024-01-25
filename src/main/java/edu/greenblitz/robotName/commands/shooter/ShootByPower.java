package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootByPower extends FlyWheelCommand {

    private double power;

    public ShootByPower(double power) {
        this.power = power;
    }

    @Override
    public void execute() {
        flyWheel.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return !Funnel.getInstance().isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        new SequentialCommandGroup(
                new WaitCommand(FlyWheelConstants.DELAY_SECONDS_TILL_EXIT),
                new StopShooter()
        ).schedule();
    }
}
