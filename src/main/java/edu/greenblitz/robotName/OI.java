package edu.greenblitz.robotName;


import com.ctre.phoenix6.SignalLogger;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands.setPower;
import edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands.sysIdDefault;
import edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands.sysIdDynamic;
import edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands.sysIdQuasistatic;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot.SystemIdenficationPivot;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;


public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        initButtons();
        DefaultCommands();
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

    public void DefaultCommands(){
//        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        //Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
    }
    
    public void initButtons(){
//        m_mechanism.setDefaultCommand(new sysIdDefault().joystickDriveCommand(() -> secondJoystick.getAxisValue(SmartJoystick.Axis.LEFT_X)));
        secondJoystick.A.whileTrue(new setPower());
        /**
         * Joystick Y = quasistatic forward
         * Joystick B = dynamic forward
         * Joystick A = quasistatic reverse
         * Joystick X = dyanmic reverse
         */
//        secondJoystick.Y.whileTrue(new sysIdQuasistatic().sysIdQuasistatic(SysIdRoutine.Direction.kForward));
//        secondJoystick.A.whileTrue(new sysIdQuasistatic().sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
//        secondJoystick.B.whileTrue(new sysIdDynamic().sysIdDynamic(SysIdRoutine.Direction.kForward));
//        secondJoystick.X.whileTrue(new sysIdDynamic().sysIdDynamic(SysIdRoutine.Direction.kReverse));


        /* Manually stop logging with left bumper after we're done with the tests */
        /* This isn't necessary, but is convenient to reduce the size of the hoot file */
        //.secondJoystick.R1.onTrue(new RunCommand(SignalLogger::stop));
    }
}
