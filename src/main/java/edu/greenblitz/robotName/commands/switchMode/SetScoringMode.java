package edu.greenblitz.robotName.commands.switchMode;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.ScoringMode;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.climber.lifter.Lifter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SetScoringMode extends InstantCommand {

    private ScoringMode scoringMode;

    public SetScoringMode(ScoringMode mode) {
        scoringMode = mode;
    }

    @Override
    public void initialize() {
        ScoringModeSelector.setScoringMode(scoringMode);
        setIdleModesBasedOnScoringMode(scoringMode);
    }

    private static void setIdleModesBasedOnScoringMode(ScoringMode scoringMode) {
        if (scoringMode == ScoringMode.CLIMB) {
            Lifter.getInstance().setIdleMode(CANSparkBase.IdleMode.kBrake);
            Elbow.getInstance().setIdleMode(NeutralModeValue.Brake);
        }
        else {
            Lifter.getInstance().setIdleMode(CANSparkBase.IdleMode.kCoast);
            Elbow.getInstance().setIdleMode(NeutralModeValue.Coast);
        }
    }
}