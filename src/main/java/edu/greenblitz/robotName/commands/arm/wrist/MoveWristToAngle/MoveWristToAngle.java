package edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveWristToAngle extends ConditionalCommand {

    public MoveWristToAngle(double goalAngle, boolean isSimulation) {
        super(new SimulationMoveWristToAngle(goalAngle), new MotorMoveWristToAngle(goalAngle), () -> isSimulation);
    }

}
