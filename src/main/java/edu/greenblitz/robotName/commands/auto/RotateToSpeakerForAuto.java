package edu.greenblitz.robotName.commands.auto;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.commands.swerve.RotateToSpeakerByCalculation;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RotateToSpeakerForAuto extends ConditionalCommand {

    public RotateToSpeakerForAuto() {
        super(
                new RotateToSpeakerByCalculation(),
                new InstantCommand(),
                () -> Intake.getInstance().isObjectIn() || Funnel.getInstance().isObjectIn()
        );
    }

}
