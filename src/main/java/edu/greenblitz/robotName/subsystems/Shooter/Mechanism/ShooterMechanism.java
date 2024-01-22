package edu.greenblitz.robotName.subsystems.Shooter.Mechanism;

import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Shooter.Mechanism.ShooterMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.Shooter.Mechanism.ShooterMechanismConstants.PivotMechanismConstants.PIVOT_CORDINATES;

public class ShooterMechanism extends GBSubsystem {

    private static Mechanism2d shooterMechanism;

    private MechanismRoot2d rootPivot;

    private final MechanismLigament2d pivot;


    public static void init() {
        new ShooterMechanism();
    }

    public ShooterMechanism(){
        shooterMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        rootPivot = shooterMechanism.getRoot("pivot_root", PIVOT_CORDINATES.getX(), PIVOT_CORDINATES.getY());
        pivot = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, Units.radiansToDegrees(PivotConstants.STARTING_ANGLE),LINE_WIDTH, new Color8Bit(Color.kPurple)));
        SmartDashboard.putData("ShooterMech2D", shooterMechanism);
    }

    @Override
    public void periodic() {
        double pivotAngle = Pivot.getInstance().getAngleInRadians();
        pivot.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngleInRadians());
    }

}