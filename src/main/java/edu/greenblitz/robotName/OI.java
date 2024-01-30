package edu.greenblitz.robotName;


import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.LED.LEDConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj.util.Color;
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
		if (LED.getInstance().rumble()) {
			mainJoystick.rumble(true, 1);
		}
		mainJoystick.POV_UP.whileTrue(new InstantCommand(
				() -> LED.getInstance().rumble()
		));
		
		mainJoystick.A.whileTrue(new InstantCommand(
				() -> LED.getInstance().setLEDColor(Color.kGreen, 0, LEDConstants.LED_LENGTH)
		));
		mainJoystick.X.whileTrue(new InstantCommand(
				() -> LED.getInstance().turnOff(0, LEDConstants.LED_LENGTH)
		));
//		mainJoystick.B.whileTrue(new InstantCommand(
//				() -> LED.getInstance().colorByMode(LED.RobotMode.AMP)
//		));
		mainJoystick.Y.whileTrue(new InstantCommand(
				() -> LED.getInstance().colorByMode(LED.RobotMode.SPEAKER)
		));
		mainJoystick.B.whileTrue(
				new InstantCommand(
						() -> LED.getInstance().blinkByNotePlace(LED.RobotMode.NotePlaceInRobot.ARM))
		);
	}
	
}
