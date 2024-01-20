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
        pivot = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, 45,6, new Color8Bit(Color.kPurple)));
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

//        Logger.recordOutput("Arm/SimPose3D", getArmPosition(elbowAngle));
//        Logger.recordOutput("Arm/TargetPose3D", getArmPosition(elbowAngle));
    }
//    public static Pose3d getArmPosition(double elbowAngle) {
////        double PI = Math.PI;
////        double yCosBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.cos(pivotAngle + elbowAngle - PI);
////        double ySinBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.sin(pivotAngle + elbowAngle - PI);
////        if (PI-pivotAngle>PI-elbowAngle){
////            return new Pose3d(ElbowConstants.ARM_LENGTH * Math.cos(elbowAngle) + yCosBeta,
////                    0,
////                    ElbowConstants.ARM_LENGTH * Math.sin(elbowAngle) + ySinBeta,
////                    new Rotation3d(0,0,0));
////        }
////        else {
////            return new Pose3d(ElbowConstants.ARM_LENGTH * Math.cos(elbowAngle) + ySinBeta,
////                    0,
////                    ElbowConstants.ARM_LENGTH * Math.sin(elbowAngle) - yCosBeta,
////                    new Rotation3d(0,0,0));
////        }
//    }


}
