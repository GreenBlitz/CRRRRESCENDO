package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnel;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.wpi.first.math.geometry.Rotation2d;

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
        secondJoystick.A.whileTrue(new MoveElbowToAngle(Rotation2d.fromDegrees(90)));
        secondJoystick.B.whileTrue(new MoveElbowToAngle(Rotation2d.fromDegrees(0)));
    }

    public void initializeDefaultCommands(){
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
    }

  
}
