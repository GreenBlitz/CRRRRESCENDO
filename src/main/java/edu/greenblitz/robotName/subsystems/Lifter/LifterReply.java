package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class LifterReply implements ILifter{

    public void setPower(double power){

    }

    @Override
    public void moveByCalculatedDistance(double distance) {

    }

    public void setVoltage(double voltage){

    }

    public void setPosition(double position){

    }

    public boolean isMotorInPosition(double position){
        return false;
    }

    public void updateInputs(LifterInputs lastInputs){

    }
    @Override
    public void stopMotor() {
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
    }


}
