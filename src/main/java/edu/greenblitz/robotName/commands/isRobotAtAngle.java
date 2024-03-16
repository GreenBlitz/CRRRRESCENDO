package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.Supplier;

public class isRobotAtAngle extends GBCommand {


    private Supplier<Rotation2d> targetAngle;

    public isRobotAtAngle(Rotation2d targetAngle){
        super();
        this.targetAngle = () -> targetAngle;
    }

    public isRobotAtAngle(Supplier<Rotation2d> targetAngle){
        super();
        this.targetAngle = targetAngle;
    }

    @Override
    public boolean isFinished() {
        return SwerveChassis.getInstance().isAtAngle(targetAngle.get());
    }
}
