package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.NoteToShooter;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.ShootSimulationNote;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

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

	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
		usedJoystick.A.whileTrue(new NoteToShooter());
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