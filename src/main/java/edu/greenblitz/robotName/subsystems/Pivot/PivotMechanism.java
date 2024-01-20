package edu.greenblitz.robotName.subsystems.Pivot;

import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.Logger;

public class PivotMechanism extends GBSubsystem {

    static Mechanism2d ARMS_MECHANISM;
    MechanismRoot2d rootPivot;

    private final MechanismLigament2d pivot;

    MechanismRoot2d rootElbow;

    private final MechanismLigament2d elbow;


    public static void init() {
        new PivotMechanism();
    }

    public PivotMechanism(){
        ARMS_MECHANISM = new Mechanism2d(2,2);
        rootPivot = ARMS_MECHANISM.getRoot("arm_root", 1.5, 1);
        pivot = rootPivot.append(new MechanismLigament2d("pivot", 2, 230,6, new Color8Bit(Color.kPurple)));
        rootElbow = ARMS_MECHANISM.getRoot("pivot_root", 0.5, 1.5);
        elbow = rootElbow.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, 45));
        SmartDashboard.putData("Mech2D", ARMS_MECHANISM);
    }

    @Override
    public void periodic() {
        double pivotAngle = Pivot.getInstance().getAngleInRadians();
        pivot.setAngle(Units.radiansToDegrees(pivotAngle));
        double elbowAngle = Elbow.getInstance().getAngleInRadians();
        elbow.setAngle(Units.radiansToDegrees(elbowAngle));

        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngleInRadians());
        Logger.recordOutput("Elbow/TargetPose3D", Elbow.getInstance().getAngleInRadians());
    }



}
