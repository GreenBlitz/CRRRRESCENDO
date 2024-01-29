package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

import java.util.function.Supplier;

public class MoveNoteBetweenShooterArm extends GBCommand {

    private Funnel funnel;

    private Roller roller;

    private Supplier<Boolean> isShooterToArm;

    public MoveNoteBetweenShooterArm(){
        roller = Roller.getInstance();
        require(roller);
        funnel = Funnel.getInstance();
        require(funnel);

        this.isShooterToArm = ScoringModeSelector::isSpeakerToAmp;
    }

    @Override
    public void execute() {
        if (isShooterToArm.get()){
            funnel.rollOut();
            roller.rollIn();
        }
        else {
            funnel.rollIn();
            roller.rollOut();
        }
    }

    @Override
    public boolean isFinished() {
        return isShooterToArm.get() ? funnel.isObjectIn() : Wrist.getInstance().isObjectInside();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        roller.stop();
    }
}
