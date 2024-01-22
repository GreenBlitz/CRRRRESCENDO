<<<<<<<< HEAD:src/main/java/edu/greenblitz/robotName/subsystems/Arm/EndEffector/Wrist/ReplayWrist.java
package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist;

import com.revrobotics.CANSparkMax;

public class ReplayWrist implements IWrist {
========
package edu.greenblitz.robotName.subsystems.Shooter.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class ReplayPivot implements IPivot{
>>>>>>>> dee99b51352662ae3c2fab8acda55e4f8433fded:src/main/java/edu/greenblitz/robotName/subsystems/Shooter/Pivot/ReplayPivot.java

    @Override
    public void setPower(double power) {

    }

    @Override
    public void setVoltage(double voltage) {

    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {

    }

    @Override
    public void resetAngle(double position) {

    }

    @Override
    public void moveToAngle(double goalAngle) {

    }

    @Override
<<<<<<<< HEAD:src/main/java/edu/greenblitz/robotName/subsystems/Arm/EndEffector/Wrist/ReplayWrist.java
    public void updateInputs(WristInputsAutoLogged inputs) {
========
    public void updateInputs(PivotInputsAutoLogged inputs) {
>>>>>>>> dee99b51352662ae3c2fab8acda55e4f8433fded:src/main/java/edu/greenblitz/robotName/subsystems/Shooter/Pivot/ReplayPivot.java

    }
}
