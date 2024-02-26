package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import static edu.wpi.first.units.Units.Volts;

public class SysIdFalconSwerve extends GBSubsystem {

    private static SysIdFalconSwerve instance;

    private TalonFX motorFrontLeft;

    private TalonFX motorFrontRight;

    private TalonFX motorBackLeft;

    private TalonFX motorBackRight;

    private VoltageOut sysIdControl;

    private SysIdRoutine.Config config;

    private SysIdRoutine.Mechanism mechanism;

    private SysIdRoutine sysIdRoutine;

    private SysIdFalconSwerve() {
        motorBackLeft = new TalonFX(SysIdFalconSwerveConstants.MOTOR_BACK_LEFT, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorBackRight = new TalonFX(SysIdFalconSwerveConstants.MOTOR_BACK_RIGHT, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorFrontLeft = new TalonFX(SysIdFalconSwerveConstants.MOTOR_FRONT_LEFT, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorFrontRight = new TalonFX(SysIdFalconSwerveConstants.MOTOR_FRONT_RIGHT, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorBackLeft.getConfigurator().apply(new TalonFXConfiguration());
        motorBackRight.getConfigurator().apply(new TalonFXConfiguration());
        motorFrontLeft.getConfigurator().apply(new TalonFXConfiguration());
        motorFrontRight.getConfigurator().apply(new TalonFXConfiguration());
        motorBackLeft.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorBackRight.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorFrontRight.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorFrontLeft.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorBackLeft.setInverted(false);
        motorBackRight.setInverted(false);
        motorFrontLeft.setInverted(false);
        motorFrontRight.setInverted(true);


        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconSwerveConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> setControl(volts),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconSwerveConstants.SIGNAL_SPEED,
                motorBackLeft.getPosition(),
                motorBackLeft.getVelocity(),
                motorBackLeft.getMotorVoltage(),
                motorBackRight.getPosition(),
                motorBackRight.getVelocity(),
                motorBackRight.getMotorVoltage(),
                motorFrontLeft.getPosition(),
                motorFrontLeft.getVelocity(),
                motorFrontLeft.getMotorVoltage(),
                motorFrontRight.getPosition(),
                motorFrontRight.getVelocity(),
                motorFrontRight.getMotorVoltage()
        );

        motorBackLeft.optimizeBusUtilization();
        motorBackRight.optimizeBusUtilization();
        motorFrontLeft.optimizeBusUtilization();
        motorFrontRight.optimizeBusUtilization();
    }

    
    public void setControl(Measure<Voltage> volts){
        motorBackLeft.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorBackRight.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorFrontLeft.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorFrontRight.setControl(sysIdControl.withOutput(volts.in(Volts)));
    }
    public static void init() {
        if (instance == null) {
            instance = new SysIdFalconSwerve();
        }
    }

    public static SysIdFalconSwerve getInstance() {
        init();
        return instance;
    }

    private Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
        Command command = sysIdRoutine.quasistatic(direction);
        command.addRequirements(instance);
        return command;
    }

    private Command sysIdDynamic(SysIdRoutine.Direction direction) {
        Command command = sysIdRoutine.dynamic(direction);
        command.addRequirements(instance);
        return command;
    }

    public void buttons(SmartJoystick joystick) {
        joystick.L1.onTrue(new InstantCommand(SignalLogger::start));

        joystick.Y.whileTrue(instance.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
        joystick.A.whileTrue(instance.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
        joystick.B.whileTrue(instance.sysIdDynamic(SysIdRoutine.Direction.kForward));
        joystick.X.whileTrue(instance.sysIdDynamic(SysIdRoutine.Direction.kReverse));

        joystick.R1.onTrue(new InstantCommand(SignalLogger::stop));
    }
}