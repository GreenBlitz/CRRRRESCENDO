package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToAmp;
import edu.greenblitz.robotName.commands.intake.ReverseRunIntake;
import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.StopFlyWheel;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.wpilibj2.command.*;

public class EjectNote extends ProxyCommand {

    public EjectNote() {
        super(EjectNote::getCommand);
    }

    private static Command getCommand() {
        if (Roller.getInstance().isObjectIn()) {
            return new ReleaseNoteFromRollerToAmp();
        }
        if (Intake.getInstance().isObjectIn()) {
            return new ReverseRunIntake();
        }
        if (Funnel.getInstance().isObjectIn()) {
            return new ParallelDeadlineGroup(
                    new PushNoteToFlyWheel(),
                    new RunFlyWheelByVelocity(FlyWheelConstants.SLOW_SHOOTING_VELOCITY)
            ).andThen(
                    new StopFlyWheel()
            );
        }
        return new InstantCommand();
    }
}
