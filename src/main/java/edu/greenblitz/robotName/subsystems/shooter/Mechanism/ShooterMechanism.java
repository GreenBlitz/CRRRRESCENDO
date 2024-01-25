package edu.greenblitz.robotName.subsystems.shooter.Mechanism;

import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Mechanism.ShooterMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.shooter.Mechanism.ShooterMechanismConstants.PivotMechanismConstants.*;

public class ShooterMechanism {

    private static ShooterMechanism instance;

    private Mechanism2d shooterMechanism;

    private MechanismRoot2d rootPivot;

    private MechanismLigament2d pivot;


    public static void init() {
        if (instance == null)
            instance = new ShooterMechanism();
    }

    public static ShooterMechanism getInstance() {
        init();
        return instance;
    }

    private ShooterMechanism(){
        shooterMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(),SIZE_OF_MECHANISM.getY());
        rootPivot = shooterMechanism.getRoot("pivot_root", PIVOT_COORDINATES.getX(), PIVOT_COORDINATES.getY());
        pivot = rootPivot.append(new MechanismLigament2d("pivot", PivotConstants.LENGTH_OF_SHOOTER, Units.radiansToDegrees(PivotConstants.STARTING_ANGLE),LINE_WIDTH, PIVOT_COLOR));
        SmartDashboard.putData("ShooterMech2D", shooterMechanism);
    }


    public void periodic() {

        double pivotAngle = Pivot.getInstance().getAngleInRadians();
        pivot.setAngle(Units.radiansToDegrees(pivotAngle));

        Logger.recordOutput("Pivot/SimPose3D", Pivot.getInstance().getAngleInRadians());
    }

}