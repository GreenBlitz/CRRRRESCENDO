package edu.greenblitz.robotName.commands.arm.Wrist.MoveWristToAngle;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MoveWristToAngle extends ConditionalCommand {

    public MoveWristToAngle(double goalAngle, boolean isSimulation) {
        super(new SimulationMoveWristToAngle(goalAngle), new MotorMoveWristToAngle(goalAngle), () -> isSimulation);
    }

}
