package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.CollectNote;
import edu.greenblitz.robotName.commands.RunBy;
import edu.greenblitz.robotName.commands.getNoteToSystem.EjectNote;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.intake.NoteToShooter;
import edu.greenblitz.robotName.commands.intake.ReverseRunIntake;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.ShootSimulationNote;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.MoveRobotToShootingPosition;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.SysId.neoSysId.SysIdNeo;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {

	private static OI instance;

	private final SmartJoystick mainJoystick;

	private final SmartJoystick secondJoystick;

	private final SmartJoystick thirdJoystick;

	private final SmartJoystick fourthJoystick;

	private OI() {
		mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
		secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
		thirdJoystick = new SmartJoystick(RobotConstants.Joystick.THIRD);
		fourthJoystick = new SmartJoystick(RobotConstants.Joystick.FOURTH);

//		secondJoystick.A.whileTrue(new NoteToShooter());
//		secondJoystick.Y.whileTrue(new EjectNote());
//		secondJoystick.POV_UP.whileTrue(new MovePivotToAngle(Rotation2d.fromDegrees(50)));
//		secondJoystick.POV_DOWN.whileTrue(new MovePivotToAngle(Rotation2d.fromDegrees(30)));
//		secondJoystick.X.whileTrue(new MovePivotByJoystick(secondJoystick));
//		secondJoystick.Y.whileTrue(new RunFunnelByJoystick(secondJoystick));
//		initButtons();
//		initializeDefaultCommands();
//		SysIdNeo.getInstance().buttons(secondJoystick);
		secondJoystick.POV_DOWN.whileTrue(new RunBy());
		secondJoystick.X.whileTrue(new RunFunnelByVelocity(250));
		secondJoystick.A.whileTrue(new RunFunnelByVelocity(300));
		secondJoystick.B.whileTrue(new RunFunnelByVelocity(200));
		secondJoystick.Y.whileTrue(new RunFunnelByVelocity(-200));
		secondJoystick.POV_UP.whileTrue(new NoteToShooter());
	}

	public static void init() {
		if (instance == null) {
			instance = new OI();
		}
	}

	public static OI getInstance() {
		init();
		return instance;
	}

	public SmartJoystick getMainJoystick() {
		return mainJoystick;
	}

	public SmartJoystick getSecondJoystick() {
		return secondJoystick;
	}

	public SmartJoystick getThirdJoystick() {
		return thirdJoystick;
	}

	public SmartJoystick getFourthJoystick() {
		return fourthJoystick;
	}

	public void initButtons() {
		romyButtons();
		schoriButtons();
	}

	public void romyButtons() {
		mainJoystick.R1.whileTrue(new CollectNote());
		mainJoystick.L1.whileTrue(new MoveRobotToShootingPosition(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE));
		mainJoystick.A.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetPoseByVision()));
	}

	public void schoriButtons() {
		secondJoystick.BACK.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 1)));
		secondJoystick.START.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 0)));
		secondJoystick.B.whileTrue(new MovePivotToAngle(() ->
				ShootingStateCalculations.getTargetShooterAngle(ShootingPositionConstants.LEGAL_SHOOTING_ZONE)
		));
		secondJoystick.R1.whileTrue(new CollectNote());
		secondJoystick.A.whileTrue(new PanicMode());
		secondJoystick.POV_LEFT.whileTrue(new ReverseRunIntake());
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
		usedJoystick.A.whileTrue(new NoteFromIntakeToShooter());
		usedJoystick.B.whileTrue(new RunIntakeByJoystick(usedJoystick));
		usedJoystick.X.whileTrue(new RunFunnelByJoystick(usedJoystick));
		usedJoystick.Y.whileTrue(new RunFlyWheelByJoystick(usedJoystick));
		fourthJoystick.Y.whileTrue(new ShootSimulationNote());
	}

	public void initializeDefaultCommands() {
		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
	}
}