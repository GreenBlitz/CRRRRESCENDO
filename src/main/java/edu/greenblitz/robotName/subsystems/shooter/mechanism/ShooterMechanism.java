package edu.greenblitz.robotName.subsystems.shooter.mechanism;

import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.mechanism.ShooterMechanismConstants.PivotMechanismConstants.*;
import static edu.greenblitz.robotName.subsystems.shooter.mechanism.ShooterMechanismConstants.SIZE_OF_MECHANISM;

public class ShooterMechanism extends GBSubsystem {
	
	private static Mechanism2d shooterMechanism;
	
	private MechanismRoot2d rootPivot;
	
	private final MechanismLigament2d pivotMechanism;
	
	public static void init() {
		new ShooterMechanism();
	}
	
	public ShooterMechanism() {
		shooterMechanism = new Mechanism2d(SIZE_OF_MECHANISM.getX(), SIZE_OF_MECHANISM.getY());
		rootPivot = shooterMechanism.getRoot("pivot_root", PIVOT_COORDINATES.getX(), PIVOT_COORDINATES.getY());
		pivotMechanism = rootPivot.append(
				new MechanismLigament2d(
						"pivot",
						PivotConstants.LENGTH_OF_SHOOTER,
						Units.radiansToDegrees(PivotConstants.BACKWARD_ANGLE_LIMIT.getRadians()),
						LINE_WIDTH,
						PIVOT_COLOR
				)
		);
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