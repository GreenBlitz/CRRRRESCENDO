package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.LED.UpdateLEDState;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        initButtons();
        initializeDefaultCommands();
    }

    public static OI getInstance() {
        init();
        return instance;
    }
    
    public static void init(){
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

    public void initializeDefaultCommands(){
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
//        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
//        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
//        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
//        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
        LED.getInstance().setDefaultCommand(new UpdateLEDState());
    }

    public void ledButtons(){
//        mainJoystick.A.onTrue(new BlinkIfInArm());
//        mainJoystick.B.onTrue(new BlinkIfInShooter());
     //   mainJoystick.X.whileTrue(new InstantCommand(() -> LED.getInstance().turnOff(LEDConstants.ALL_LED)));
//        mainJoystick.Y.onTrue(new Rumble());
        mainJoystick.R1.onTrue(new SetScoringMode(ScoringMode.AMP));
        mainJoystick.L1.onTrue(new SetScoringMode(ScoringMode.SPEAKER));
    }
  
}
