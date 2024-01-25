package edu.greenblitz.robotName.subsystems.arm.ArmMechanism;

import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.WristMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanismConstants.ElbowMechanismConstants.*;

public class ArmMechanism {

    private static ArmMechanism instance;

    private static Mechanism2d armMechanism;

    private MechanismRoot2d root;

    private final MechanismLigament2d wristJoint;

    private final MechanismLigament2d elbowJoint;

    private final Elbow elbow;
    private final Wrist wrist;


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

        SmartDashboard.putData("ArmMech2D", armMechanism);
    }


    public void periodic() {

        double elbowAngle = elbow.getAngleInRadians();
        double wristAngle = wrist.getAngleInRadians();

        elbowJoint.setAngle(Units.radiansToDegrees(elbowAngle));
        wristJoint.setAngle(Units.radiansToDegrees(wristAngle));

        Logger.recordOutput("Elbow/SimPose3D", elbow.getAngleInRadians());
        Logger.recordOutput("Wrist/SimPose3D", wrist.getAngleInRadians());
    }
}
