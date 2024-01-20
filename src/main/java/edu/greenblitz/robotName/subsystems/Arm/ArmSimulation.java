package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.Logger;

public class ArmSimulation extends GBSubsystem {

    static Mechanism2d ARM_MECHANISM;

    MechanismRoot2d root;

    private final MechanismLigament2d elbow;

    private final MechanismLigament2d pivot;

    public static void init() {
        new ArmSimulation();
    }

    public ArmSimulation(){
        ARM_MECHANISM = new Mechanism2d(2,2);
        root = ARM_MECHANISM.getRoot("arm_root", 1, 1);
        elbow = root.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, 45));
        pivot = elbow.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, 300,6, new Color8Bit(Color.kPurple)));
        SmartDashboard.putData("Mech2D", ARM_MECHANISM);
    }

    @Override
    public void periodic() {
        double pivotAngle = Pivot.getInstance().getAngleInRadians();
        double elbowAngle = Elbow.getInstance().getAngleInRadians();
        elbow.setAngle(Units.radiansToDegrees(elbowAngle));
        pivot.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Arm/SimPose3D", getArmPosition(pivotAngle, elbowAngle));
        Logger.recordOutput("Arm/TargetPose3D", getArmPosition(pivotAngle,elbowAngle));
    }
    public static Pose3d getArmPosition(double pivotAngle, double elbowAngle) {
        double PI = Math.PI;
        double yCosBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.cos(pivotAngle + elbowAngle - PI);
        double ySinBeta = PivotConstants.LENGTH_OF_SHOOTER * Math.sin(pivotAngle + elbowAngle - PI);
        if (PI-pivotAngle>PI-elbowAngle){
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
