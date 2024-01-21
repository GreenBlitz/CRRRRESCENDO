package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.Logger;

public class JointsMechanism extends GBSubsystem {

    private static final Translation2d SIZE_OF_MECHANISM = new Translation2d(2.0 ,2.0);
    private static final int LINE_WIDTH = 6;
    private static Mechanism2d jointsMechanism;
    private MechanismRoot2d rootPivot;

    private final MechanismLigament2d pivot;

    private MechanismRoot2d rootElbow;

    private final MechanismLigament2d elbow;


    public static void init() {
        new JointsMechanism();
    }

    public JointsMechanism(){
        jointsMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        rootPivot = jointsMechanism.getRoot("arm_root", PivotConstants.Simulation.X_POSITION, PivotConstants.Simulation.Y_POSITION);
        pivot = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, Units.radiansToDegrees(PivotConstants.STARTING_ANGLE),LINE_WIDTH, new Color8Bit(Color.kPurple)));
        rootElbow = jointsMechanism.getRoot("pivot_root", ElbowConstants.Simulation.X_POSITION, ElbowConstants.Simulation.Y_POSITION);
        elbow = rootElbow.append(new MechanismLigament2d("elbow", ElbowConstants.ARM_LENGTH, Units.radiansToDegrees(ElbowConstants.STARTING_ANGLE)));
        SmartDashboard.putData("Mech2D", jointsMechanism);
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
