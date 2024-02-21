package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.LED.UpdateLEDStateDefaultCommand;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class OI {
	private static OI instance;

	private SmartJoystick mainJoystick;
	private SmartJoystick secondJoystick;

	private OI() {
		mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
		secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
		System.out.println("This is now running \n\n\n\n\n\n\n\n This is also a line");
		initButtons();
		initializeDefaultCommands();
	}

	public static OI getInstance() {
		init();
		return instance;
	}

	public static void init() {
		if (instance == null) {
			instance = new OI();
		}
	}

	public SmartJoystick getMainJoystick() {
		return mainJoystick;
	}

	public SmartJoystick getSecondJoystick() {
		return secondJoystick;
	}

	public void initButtons() {
		ledButtons();
	}

	public void initializeDefaultCommands() {
//        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
//        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
//        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
//        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
//        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		//LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
	}

	public void ledButtons() {
		//   mainJoystick.X.whileTrue(new InstantCommand(() -> LED.getInstance().turnOff(LEDConstants.ALL_LED)));
//        mainJoystick.Y.onTrue(new Rumble());
		LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
		mainJoystick.A.onTrue(new SetScoringMode(ScoringMode.AMP));
		mainJoystick.X.onTrue(new SetScoringMode(ScoringMode.SPEAKER));
	}

}
