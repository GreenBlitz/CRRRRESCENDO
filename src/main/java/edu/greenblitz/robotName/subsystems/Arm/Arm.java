package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.utils.GBMath;
import edu.greenblitz.robotName.utils.GBSubsystem;

import static edu.greenblitz.robotName.subsystems.Arm.ElbowUtils.ElbowConstants.*;

public class Arm extends GBSubsystem {

	private static Arm instance;
	private final Elbow elbow;
	private final Pivot pivot;

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

	public Elbow getElbow() {
		return elbow;
	}

	public Pivot getPivot() {
		return pivot;
	}

	public boolean isAtGoalAngles(){
		return elbow.isAtGoalAngle() && pivot.isAtGoalAngle();
	}



	public double optimizeRoute(double currentAngle, double targetAngle, double edge1, double edge2) {
		boolean isCrossingNoNoZone = GBMath.isRangeContainsAnotherRange(
				currentAngle,
				targetAngle,
				edge1,
				edge2
		);

		return isCrossingNoNoZone ? GBMath.reverseAngle(targetAngle) : targetAngle;
	}


	public void moveArmBy2Angles(double elbowAngleTarget, double pivotAngleTarget){
		double elbowBestRoute = optimizeRoute(
				elbow.getAngleInRadians(),
				elbowAngleTarget,
				GBMath.reverseAngle(BACKWARD_ANGLE_LIMIT),
				FORWARD_ANGLE_LIMIT
		);

		elbow.setGoalAngle(elbowBestRoute);
		pivot.setGoalAngle(pivotAngleTarget);
	}
}
