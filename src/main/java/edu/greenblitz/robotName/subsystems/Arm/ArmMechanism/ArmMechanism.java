package edu.greenblitz.robotName.subsystems.Arm.ArmMechanism;

import edu.greenblitz.robotName.subsystems.Arm.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Arm.Wrist;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanismConstants.WristMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanismConstants.ElbowMechanismConstants.*;

public class ArmMechanism extends GBSubsystem {

    private static Mechanism2d armMechanism;

    private MechanismRoot2d root;

    private final MechanismLigament2d wrist;

    private final MechanismLigament2d elbow;


    public static void init() {
        new ArmMechanism();
    }

    private ArmMechanism(){
        armMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        root = armMechanism.getRoot("arm_root",POSITION_OF_MECHANISM.getX(),POSITION_OF_MECHANISM.getY());

        elbow = root.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH,Units.radiansToDegrees(ElbowConstants.STARTING_ANGLE),ELBOW_LINE_WIDTH,COLOR_OF_ELBOW));
        wrist = elbow.append(new MechanismLigament2d("wrist", WristConstants.LENGTH_OF_ENDEFFECTOR, Units.radiansToDegrees(WristConstants.STARTING_ANGLE), WRIST_LINE_WIDTH, COLOR_OF_WRIST));

        SmartDashboard.putData("ArmMech2D", armMechanism);
    }

    @Override
    public void periodic() {
        super.periodic();

        double elbowAngle = Elbow.getInstance().getAngleInRadians();
        double wristAngle = Wrist.getInstance().getAngleInRadians();

        elbow.setAngle(Units.radiansToDegrees(elbowAngle));
        wrist.setAngle(Units.radiansToDegrees(wristAngle));

        Logger.recordOutput("Wrist/SimPose3D", Wrist.getInstance().getAngleInRadians());
    }
}
