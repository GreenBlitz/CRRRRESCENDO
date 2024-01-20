package edu.greenblitz.robotName.subsystems.Elbow;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElbowMechanism extends GBSubsystem {

    static Mechanism2d ELBOW_MECHANISM;

    MechanismRoot2d root;

    private final MechanismLigament2d elbow;


    public static void init() {
        new ElbowMechanism();
    }

    public ElbowMechanism(){
        ELBOW_MECHANISM = new Mechanism2d(2,2);
        root = ELBOW_MECHANISM.getRoot("arm_root", 1, 1);
        elbow = root.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, 45));
        SmartDashboard.putData("Mech2DDDDD", ELBOW_MECHANISM);
    }

    @Override
    public void periodic() {
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
