package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

import java.util.function.Supplier;

public class MoveNoteBetweenShooterArm extends GBCommand {

    private Funnel funnel;

    private Roller roller;

    private Supplier<Boolean> isArmToShooter;

    public MoveNoteBetweenShooterArm(Supplier<Boolean> isArmToShooter){
        roller = Roller.getInstance();
        require(roller);
        funnel = Funnel.getInstance();
        require(funnel);

        this.isArmToShooter = isArmToShooter;
    }

    @Override
    public void execute() {
        if (isArmToShooter.get()){
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
        return isArmToShooter.get() ? funnel.isObjectIn() : Wrist.getInstance().isObjectInside();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        roller.stop();
    }
}
