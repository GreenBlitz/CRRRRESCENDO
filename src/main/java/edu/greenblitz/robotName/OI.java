package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.CollectNote;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.intake.ReverseRunIntake;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.ShootSimulationNote;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.RunFunnelByPower;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
		
		initButtons();
		initializeDefaultCommands();
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
		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(false));
		mainJoystick.R1.whileTrue(new CollectNote());
		mainJoystick.L1.whileTrue(
				new RotateToAngle( () -> ShootingStateCalculations.getTargetRobotAngle(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE.getWrapperZone()))
		);
		mainJoystick.Y.onTrue(new InstantCommand(
				() -> SwerveChassis.getInstance().resetChassisPose()
		));
	}
	
	public void schoriButtons() {
		secondJoystick.BACK.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 1)));
		secondJoystick.START.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 0)));
		secondJoystick.B.whileTrue(new MovePivotToAngle(() ->
				ShootingStateCalculations.getTargetShooterAngle(ShootingPositionConstants.LEGAL_SHOOTING_ZONE)
		));
		secondJoystick.A.whileTrue(new PanicMode());
		secondJoystick.POV_LEFT.whileTrue(new ReverseRunIntake());
		secondJoystick.X.onTrue(new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE));
<<<<<<< HEAD
		secondJoystick.L1.whileTrue(new RunFlyWheelByVelocity(2000));
		secondJoystick.POV_UP.whileTrue(new RunFunnelByPower(0.4));
		secondJoystick.POV_DOWN.whileTrue(new RunFunnelByPower(-0.4));
=======
		secondJoystick.L1.whileTrue(new RunFlyWheelByVelocity(300));
		secondJoystick.POV_UP.whileTrue(new RunFunnelByPower(0.8));
		secondJoystick.POV_DOWN.whileTrue(new RunFunnelByPower(-0.2).deadlineWith(new WaitCommand(0.5)));
>>>>>>> edfb9acb6f695f7472b28fb25905e2f51ea279fd
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
//		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		Pivot.getInstance().setDefaultCommand(new MovePivotByJoystick(secondJoystick));
	}
}