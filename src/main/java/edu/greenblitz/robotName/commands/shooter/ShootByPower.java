package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootByPower extends FlyWheelCommand {

    public ShootByPower() {
    }

    @Override
    public void execute() {
        flyWheel.setPower(FlyWheelConstants.SHOOTING_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        new SequentialCommandGroup(
                new RunFunnel(),
                new WaitCommand(FlyWheelConstants.DELAY_SECONDS_TILL_EXIT),
                new StopFlyWheel()
        ).schedule();
    }
}
