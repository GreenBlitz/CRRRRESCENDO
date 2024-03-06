package edu.greenblitz.robotName.commands.arm.elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;

public class ElbowDefaultCommand extends ElbowCommand {
    
    @Override
    public void initialize() {
        elbow.setCurrentAngle();
        if (elbow.isClimb()){
            elbow.setIdleMode(NeutralModeValue.Brake);
        }
    }

    @Override
    public void execute() {
        if (!elbow.isClimb()){
            elbow.standInPlace();
        }
    }
}