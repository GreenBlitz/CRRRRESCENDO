package edu.greenblitz.robotName.commands.arm.Elbow.MoveElbowToAngle;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MoveElbowToAngle extends ConditionalCommand {

    public MoveElbowToAngle(double goalAngle, boolean isSimulation) {
        super(new SimulationMoveElbowToAngle(goalAngle), new MotorMoveElbowToAngle(goalAngle), () -> isSimulation);
    }

}
