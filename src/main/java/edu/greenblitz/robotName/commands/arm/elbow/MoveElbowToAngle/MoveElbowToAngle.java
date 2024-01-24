package edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveElbowToAngle extends ConditionalCommand {

    public MoveElbowToAngle(double targetAngle, boolean isSimulation) {
        super(new SimulationMoveElbowToAngle(targetAngle), new MotorMoveElbowToAngle(targetAngle), () -> isSimulation);
    }

}
