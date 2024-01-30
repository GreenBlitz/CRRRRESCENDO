package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveNoteBetweenShooterAndArm extends GBCommand {

    private Funnel funnel;

    private Roller roller;

    private boolean isTargetModeSpeaker;

    public MoveNoteBetweenShooterAndArm(){
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
        if (isTargetModeSpeaker){
            funnel.rollIn();
            roller.rollOut();
        }
        else {
            funnel.rollOut();
            roller.rollIn();
        }
    }

    @Override
    public boolean isFinished() {
        return isTargetModeSpeaker ? roller.isObjectInside() : funnel.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        roller.stop();
    }
}
