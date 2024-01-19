package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.subsystems.Arm.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Arm.Pivot.Pivot;
import edu.greenblitz.robotName.utils.Constraints;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;

import static edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants.BACKWARD_ANGLE_LIMIT;

public class Arm extends GBSubsystem {

	private static Arm instance;

	private Elbow elbow;

	private Pivot pivot;

	public static void init(){
		if (instance == null)
			instance = new Arm();
	}

	public static Arm getInstance(){
		init();
		return instance;
	}

	public Arm(){
		elbow = Elbow.getInstance();
		pivot = Pivot.getInstance();
	}

	public Rotation2d optimizeRoute(Rotation2d targetAngle, Rotation2d currentAngle, Constraints noNoZone) {
		Constraints route = new Constraints(currentAngle, targetAngle);

		return route.checkIfConstraintCompletelyInsideAnother(noNoZone) ?
				new Rotation2d(-Math.PI*2+targetAngle.getRadians())
				:
				new Rotation2d(targetAngle.getRadians());
	}

	public void moveArmBy2Angles(Rotation2d elbowAngleTarget, Rotation2d pivotAngleTarget){
		elbow.setGoalAngle(
				optimizeRoute(elbowAngleTarget,
				new Rotation2d(elbow.getAngleInRadians()),
				new Constraints(
						new Rotation2d(ElbowConstants.FORWARD_ANGLE_LIMIT),
						new Rotation2d(Math.PI*2+BACKWARD_ANGLE_LIMIT))
				).getRadians());

		pivot.setGoalAngle(pivotAngleTarget.getRadians());
	}

	public Elbow getElbow() {
		return elbow;
	}

	public Pivot getPivot() {
		return pivot;
	}

	public boolean isAtGoalAngles(){
		return elbow.isAtGoalAngle() && pivot.isAtGoalAngle();
	}
}
