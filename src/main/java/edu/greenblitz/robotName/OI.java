package edu.greenblitz.robotName;


import com.ctre.phoenix6.SignalLogger;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.commands.shooter.ShootByPower;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.SysId.FalconSysId.SysIdFalcon;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;
import static edu.greenblitz.robotName.utils.SysId.FalconSysId.SysIdFalconConstants.DYNAMIC_VOLTAGE;
import static edu.wpi.first.units.Units.Volts;

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
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
    }
    
    public void initButtons(){


        SysIdRoutine.Config config = new SysIdRoutine.Config(
                null,
                Volts.of(DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        SysIdRoutine.Mechanism mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> FlyWheel.getInstance().setVoltage(volts.in(Volts), 0),
                null,
                FlyWheel.getInstance()
        );
        SysIdRoutine routine = new SysIdRoutine(config, mechanism);

        buttons(mainJoystick,routine);

    }

    private Command sysIdDynamic(SysIdRoutine.Direction direction, SysIdRoutine routine) {
        Command command = routine.dynamic(direction);
        command.addRequirements(FlyWheel.getInstance());
        return command;
    }

    private Command sysIdQuasistatic(SysIdRoutine.Direction direction, SysIdRoutine routine) {
        Command command = routine.quasistatic(direction);
        command.addRequirements(FlyWheel.getInstance());
        return command;
    }

    public void buttons(SmartJoystick joystick, SysIdRoutine routine){
        joystick.L1.onTrue(new InstantCommand(SignalLogger::start));

        joystick.Y.whileTrue(sysIdQuasistatic(SysIdRoutine.Direction.kForward, routine));
        joystick.A.whileTrue(sysIdQuasistatic(SysIdRoutine.Direction.kReverse, routine));
        joystick.B.whileTrue(sysIdDynamic(SysIdRoutine.Direction.kForward, routine));
        joystick.X.whileTrue(sysIdDynamic(SysIdRoutine.Direction.kReverse, routine));

        joystick.R1.onTrue(new InstantCommand(SignalLogger::stop));
    }
}
