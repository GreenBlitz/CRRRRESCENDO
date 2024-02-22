package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRoller;
import edu.greenblitz.robotName.commands.intake.ReverseRunIntake;
import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
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
            return new ReleaseNoteFromRoller();
        }
        if (Intake.getInstance().getEntranceBeamBreakerValue()) {
            return new ReverseRunIntake();
        }
        if (Funnel.getInstance().isObjectIn()) {
            return new ParallelCommandGroup(
                    new PushNoteToFlyWheel(),
                    new RunFlyWheelByVelocity(FlyWheelConstants.SLOW_SHOOTING_VELOCITY)
            );
        }
        return new InstantCommand();
    }
}
