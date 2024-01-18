package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.subsystems.Arm.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Arm.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import org.littletonrobotics.junction.Logger;

public class ArmSimulation extends GBSubsystem {


    static  Mechanism2d ARM_MECHANISM;
    MechanismRoot2d root;

    private MechanismLigament2d m_elbow;
    private MechanismLigament2d m_pivot;
    private static ArmSimulation instance;

    public static void init() {
        instance = new ArmSimulation();
    }
    public ArmSimulation(){
        ARM_MECHANISM = new Mechanism2d(0,0);
        root = ARM_MECHANISM.getRoot("arm_root", 0, 0);
        m_elbow = root.append(new MechanismLigament2d("elbow", 0, Units.degreesToRadians(45)));//ElbowConstants.ARM_LENGTH
        m_pivot = m_elbow.append(new MechanismLigament2d("pivot", 0, Units.degreesToRadians(230)));//PivotConstants.LENGTH_OF_SHOOTER
    }

    @Override
    public void periodic() {
//        m_elbow.setAngle(Elbow.getInstance().getAngleInRadians());
//        m_pivot.setAngle(Pivot.getInstance().getAngleInRadians());


        Logger.getInstance().recordOutput("Arm/ArmMechanism", ARM_MECHANISM);
        Logger.getInstance().recordOutput("Arm/SimPose3D",getArmPosition( m_pivot.getAngle(), m_elbow.getAngle()));

//        Logger.getInstance().recordOutput("Arm/SimPose3D", getArmPosition(Pivot.getInstance().getAngleInRadians(), Elbow.getInstance().getAngleInRadians()));
//        Logger.getInstance().recordOutput("Arm/TargetPose3D", getArmPosition(Pivot.getInstance().getGoalAngleRadians(), Elbow.getInstance().getGoalAngleRadians()));

    }
    public static Pose3d getArmPosition(double pivotAngle, double elbowAngle) {
        double cons = Math.PI;
        double yCosBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.cos(pivotAngle + elbowAngle - cons);
        double ySinBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.sin(pivotAngle + elbowAngle - cons);
        if (cons-pivotAngle>cons-elbowAngle){
            return new Pose3d(ElbowConstants.ARM_LENGTH * Math.cos(elbowAngle) + yCosBeta,
                    0,
                    ElbowConstants.ARM_LENGTH * Math.sin(elbowAngle) + ySinBeta,
                    new Rotation3d(0,0,0));
        }
        else {
            return new Pose3d(ElbowConstants.ARM_LENGTH * Math.cos(elbowAngle) + ySinBeta,
                    0,
                    ElbowConstants.ARM_LENGTH * Math.sin(elbowAngle) - yCosBeta,
                    new Rotation3d(0,0,0));
        }
    }


}
