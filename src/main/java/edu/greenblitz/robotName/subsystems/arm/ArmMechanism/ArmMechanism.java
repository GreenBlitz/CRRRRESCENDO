package edu.greenblitz.robotName.subsystems.arm.ArmMechanism;

import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.PivotMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.WristMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.ElbowMechanismConstants.*;

public class ArmMechanism {

    private static ArmMechanism instance;

    private Mechanism2d armMechanism;

    private MechanismRoot2d root;

    private MechanismLigament2d wristJoint;

    private MechanismLigament2d elbowJoint;

    private Elbow elbow;
    private Wrist wrist;

    private MechanismRoot2d rootPivot;

    private MechanismLigament2d pivot;


    public static void init() {
        if (instance == null)
            instance = new ArmMechanism();
    }

    public static ArmMechanism getInstance() {
        init();
        return instance;
    }

    private ArmMechanism() {
        elbow = Elbow.getInstance();
        wrist = Wrist.getInstance();


        armMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(), SIZE_OF_MECHANISM.getY());
        root = armMechanism.getRoot("arm_root", POSITION_OF_MECHANISM.getX(), POSITION_OF_MECHANISM.getY());

        elbowJoint = root.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, Units.radiansToDegrees(ElbowConstants.STARTING_ANGLE), ELBOW_LINE_WIDTH, COLOR_OF_ELBOW));
        wristJoint = elbowJoint.append(new MechanismLigament2d("wrist", WristConstants.LENGTH_OF_ENDEFFECTOR, Units.radiansToDegrees(WristConstants.STARTING_ANGLE), WRIST_LINE_WIDTH, COLOR_OF_WRIST));

        rootPivot = armMechanism.getRoot("pivot_root", PIVOT_COORDINATES.getX(), PIVOT_COORDINATES.getY());
        pivot = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, Units.radiansToDegrees(PivotConstants.STARTING_ANGLE),LINE_WIDTH, PIVOT_COLOR));

        SmartDashboard.putData("ArmMech2D", armMechanism);
    }


    public void periodic() {
        double elbowAngle = elbow.getAngleInRadians();
        double wristAngle = wrist.getAngleInRadians();
        double pivotAngle = Pivot.getInstance().getAngleInRadians();

        System.out.println("elbowAngle: " + Units.radiansToDegrees(elbowAngle));
        System.out.println("wristAngle: " + Units.radiansToDegrees(wristAngle));
        System.out.println("pivotAngle: " + Units.radiansToDegrees(pivotAngle));

        elbowJoint.setAngle(Units.radiansToDegrees(elbowAngle));
        wristJoint.setAngle(Units.radiansToDegrees(wristAngle));
        pivot.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Elbow/SimPose3D", elbow.getAngleInRadians());
        Logger.recordOutput("Wrist/SimPose3D", wrist.getAngleInRadians());
        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngleInRadians());
    }
}
