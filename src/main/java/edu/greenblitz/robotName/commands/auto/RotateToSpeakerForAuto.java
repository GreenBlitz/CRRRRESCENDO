package edu.greenblitz.robotName.commands.auto;

import edu.greenblitz.robotName.commands.swerve.rotateTo.RotateToSpeakerByCalculation;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RotateToSpeakerForAuto extends ConditionalCommand {

    public RotateToSpeakerForAuto() {
        super(
                new RotateToSpeakerByCalculationForAuto(),
                new InstantCommand(),
                () -> Intake.getInstance().isObjectIn() || Funnel.getInstance().isObjectIn()
        );
    }

}
