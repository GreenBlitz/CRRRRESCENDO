package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveNoteBetweenShooterAndArm extends GBCommand {

    private Funnel funnel;

    private Roller roller;

    private boolean isTargetModeSpeaker;
    
    public MoveNoteBetweenShooterAndArm() {
        roller = Roller.getInstance();
        require(roller);
        funnel = Funnel.getInstance();
        require(funnel);
    }

    @Override
    public void initialize() {
        isTargetModeSpeaker = ScoringModeSelector.isSpeakerMode();
    }

    @Override
    public void execute() {
        if (isTargetModeSpeaker) {
            funnel.rollIn();
            roller.rollCounterClockwise();
        } else {
            funnel.rollOut();
            roller.rollClockwise();
        }
    }

    @Override
    public boolean isFinished() {
        return isTargetModeSpeaker ? funnel.isObjectIn() : roller.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        roller.stop();
    }
}