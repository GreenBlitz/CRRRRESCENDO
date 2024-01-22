package edu.greenblitz.robotName.subsystems.Arm.ArmMechanism;

import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.Wrist;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.WristConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanismConstants.WristMechanismConstants.*;

public class ArmMechanism extends GBSubsystem {

    private static Mechanism2d armMechanism;

    private MechanismRoot2d rootWrist;

    private final MechanismLigament2d wrist;


    public static void init() {
        new ArmMechanism();
    }

    private ArmMechanism(){
        armMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        rootWrist = armMechanism.getRoot("wrist_root", WRIST_POSITION.getX(), WRIST_POSITION.getY());
        wrist = rootWrist.append(new MechanismLigament2d("wrist", WristConstants.LENGTH_OF_ENDEFFECTOR, Units.radiansToDegrees(WristConstants.STARTING_ANGLE),LINE_WIDTH, COLOR_OF_WRIST));
        SmartDashboard.putData("ArmMech2D", armMechanism);
    }

    @Override
    public void periodic() {
        super.periodic();
        double wristAngle = Wrist.getInstance().getAngleInRadians();

        wrist.setAngle(Units.radiansToDegrees(wristAngle));

        Logger.recordOutput("Wrist/SimPose3D", Wrist.getInstance().getAngleInRadians());
    }
}
