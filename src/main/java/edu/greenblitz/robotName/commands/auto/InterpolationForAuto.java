package edu.greenblitz.robotName.commands.auto;

import edu.greenblitz.robotName.commands.shooter.pivot.Interpolation;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class InterpolationForAuto extends ConditionalCommand {

    public InterpolationForAuto(){
        super(
                new Interpolation(),
                new InstantCommand(),
                () -> Funnel.getInstance().isObjectIn() || Intake.getInstance().isObjectIn()
        );
    }

}
