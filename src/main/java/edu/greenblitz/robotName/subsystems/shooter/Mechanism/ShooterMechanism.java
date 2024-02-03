package edu.greenblitz.robotName.subsystems.shooter.Mechanism;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Mechanism.ShooterMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.shooter.Mechanism.ShooterMechanismConstants.PivotMechanismConstants.*;

public class ShooterMechanism extends GBSubsystem {

    private static Mechanism2d shooterMechanism;

    private MechanismRoot2d rootPivot;

    private final MechanismLigament2d pivotMechanism;


    public static void init() {
        new ShooterMechanism();
    }

    public ShooterMechanism(){
        shooterMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        rootPivot = shooterMechanism.getRoot("pivot_root", PIVOT_COORDINATES.getX(), PIVOT_COORDINATES.getY());
        pivotMechanism = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, Units.radiansToDegrees(PivotConstants.STARTING_ANGLE),LINE_WIDTH, PIVOT_COLOR));
        SmartDashboard.putData("ShooterMech2D", shooterMechanism);
    }

    @Override
    public void periodic() {
        super.periodic();

        double pivotAngle = Pivot.getInstance().getAngle().getRadians();
        pivotMechanism.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngle().getRadians());
    }

}
