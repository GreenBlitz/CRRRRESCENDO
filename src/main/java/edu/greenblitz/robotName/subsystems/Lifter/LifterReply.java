package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;

public class LifterReply implements ILifter{

    public void setPowerForMotor1(double power){

    }

    @Override
    public void moveByCalculatedDistance(double distance) {

    }

    public void setVoltageForMotor1(double voltage){

    }

    public void setPositionForMotor1(double position){

    }

    public boolean isMotor1InPosition(double position){
        return false;
    }

    public void updateInputs(LifterInputs lastInputs){

    }
    @Override
    public void stopMotor1() {
    }
    @Override
    public void setIdleModeForMotor1(CANSparkMax.IdleMode mode) {
    }


}
