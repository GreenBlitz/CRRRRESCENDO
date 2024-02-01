package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.LED.BlinkIfInArm;
import edu.greenblitz.robotName.commands.LED.ConditionalRumble;
import edu.greenblitz.robotName.commands.LED.Rumble;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
	private static OI instance;
	
	private SmartJoystick mainJoystick;
	private SmartJoystick secondJoystick;
	
	private OI() {
		mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
		secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
		initButtons();
	}
	
	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
	
	public SmartJoystick getMainJoystick() {
		return mainJoystick;
	}
	
	public SmartJoystick getSecondJoystick() {
		return secondJoystick;
	}
	
	public void initButtons() {
		mainJoystick.A.whileTrue(new InstantCommand(
						() -> LED.getInstance().blinkIfInArm())
		);
		mainJoystick.X.whileTrue(new InstantCommand(
				() -> LED.getInstance().turnOff(0, LEDConstants.LED_LENGTH)
		));
		mainJoystick.B.onTrue(new BlinkIfInArm());
		mainJoystick.Y.onTrue(new ConditionalRumble());
	}
}
