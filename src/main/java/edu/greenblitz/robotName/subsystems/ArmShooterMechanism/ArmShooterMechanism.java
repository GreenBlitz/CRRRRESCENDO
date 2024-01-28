package edu.greenblitz.robotName.subsystems.ArmShooterMechanism;

import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.ArmShooterMechanism.ArmShooterMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.ArmShooterMechanism.ArmShooterMechanismConstants.PivotMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.ArmShooterMechanism.ArmShooterMechanismConstants.WristMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.ArmShooterMechanism.ArmShooterMechanismConstants.ElbowMechanismConstants.*;

public class ArmShooterMechanism {

    private static ArmShooterMechanism instance;

    private Mechanism2d armMechanism;

    private MechanismRoot2d root;

    private MechanismLigament2d wristJoint;

    private MechanismLigament2d elbowJoint;

    private Elbow elbow;
    private Wrist wrist;
    private Pivot pivot;

    private MechanismRoot2d rootPivot;

    private MechanismLigament2d pivotJoint;


    public static void init() {
        if (instance == null)
            instance = new ArmShooterMechanism();
    }

    public static ArmShooterMechanism getInstance() {
        init();
        return instance;
    }

    private ArmShooterMechanism() {
        elbow = Elbow.getInstance();
        wrist = Wrist.getInstance();
        pivot = Pivot.getInstance();


        armMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(), SIZE_OF_MECHANISM.getY());
        root = armMechanism.getRoot("arm_root", POSITION_OF_MECHANISM.getX(), POSITION_OF_MECHANISM.getY());

        elbowJoint = root.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, ElbowConstants.PresetPositions.STARTING.ANGLE.getDegrees(), ELBOW_LINE_WIDTH, COLOR_OF_ELBOW));
        wristJoint = elbowJoint.append(new MechanismLigament2d("wrist", WristConstants.LENGTH_OF_ENDEFFECTOR, WristConstants.PresetPositions.STARTING.ANGLE.getDegrees(), WRIST_LINE_WIDTH, COLOR_OF_WRIST));

        rootPivot = armMechanism.getRoot("pivot_root", PIVOT_COORDINATES.getX(), PIVOT_COORDINATES.getY());
        pivotJoint = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, PivotConstants.PresetPositions.STARTING.ANGLE.getDegrees(),LINE_WIDTH, PIVOT_COLOR));

        SmartDashboard.putData("ArmMech2D", armMechanism);
    }


    public void periodic() {
        double elbowAngle = elbow.getAngleInRadians();
        double wristAngle = wrist.getAngleInRadians();
        double pivotAngle = pivot.getAngle().getRadians();

        System.out.println("elbowAngle: " + Units.radiansToDegrees(elbowAngle) +
                "wristAngle: " + Units.radiansToDegrees(wristAngle) +
                "pivotAngle: " + Units.radiansToDegrees(pivotAngle) +
                "isInDangerZone" + Elbow.getInstance().isInDangerZone()
        );

        elbowJoint.setAngle(Units.radiansToDegrees(elbowAngle));
        wristJoint.setAngle(Units.radiansToDegrees(wristAngle));
        pivotJoint.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Elbow/SimPose3D", elbow.getAngleInRadians());
        Logger.recordOutput("Wrist/SimPose3D", wrist.getAngleInRadians());
        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngle().getRadians());
    }
}
