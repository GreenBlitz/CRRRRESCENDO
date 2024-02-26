package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RUnBY extends GBCommand {

    private String sysnam;
    private String controlMode;

    private Elbow elbow;

    public RUnBY(){
        elbow = Elbow.getInstance();
        sysnam = "elbow";
        controlMode = "voltage";
        require(elbow);
        SmartDashboard.putNumber(sysnam+", "+controlMode, 0);
    }

    @Override
    public void execute() {
        elbow.setMotorVoltage(SmartDashboard.getNumber(sysnam+", "+controlMode, 0));
    }

    @Override
    public void end(boolean interrupted) {
        elbow.setMotorVoltage(0);
    }
}
