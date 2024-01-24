package edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveElbowToAngle extends ConditionalCommand {

    public MoveElbowToAngle(double goalAngle, boolean isSimulation) {
        super(new SimulationMoveElbowToAngle(goalAngle), new MotorMoveElbowToAngle(goalAngle), () -> isSimulation);
    }

}
