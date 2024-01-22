<<<<<<<< HEAD:src/main/java/edu/greenblitz/robotName/subsystems/Arm/EndEffector/Wrist/WristInputs.java
package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist;
========
package edu.greenblitz.robotName.subsystems.Shooter.Pivot;
>>>>>>>> dee99b51352662ae3c2fab8acda55e4f8433fded:src/main/java/edu/greenblitz/robotName/subsystems/Shooter/Pivot/PivotInputs.java

import org.littletonrobotics.junction.AutoLog;

@AutoLog
<<<<<<<< HEAD:src/main/java/edu/greenblitz/robotName/subsystems/Arm/EndEffector/Wrist/WristInputs.java
public class WristInputs {
========
public class PivotInputs {
>>>>>>>> dee99b51352662ae3c2fab8acda55e4f8433fded:src/main/java/edu/greenblitz/robotName/subsystems/Shooter/Pivot/PivotInputs.java

    public double appliedOutput;

    public double outputCurrent;

    public double position;

    public double velocity;

    public double absoluteEncoderPosition;

    public double temperature;

    public boolean hasHitForwardLimit;

    public boolean hasHitBackwardsLimit;

    public boolean isObjectInside;
}
