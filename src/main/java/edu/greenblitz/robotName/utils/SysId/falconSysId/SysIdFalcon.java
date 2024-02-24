package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.modules.mk4iSwerveModule.MK4iSwerveConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import static edu.wpi.first.units.Units.Volts;

public class SysIdFalcon extends GBSubsystem {

    private static SysIdFalcon instance;

    private TalonFX motor1;
    private TalonFX motor2;
    private TalonFX motor3;
    private TalonFX motor4;

    private VoltageOut sysIdControl;

    private SysIdRoutine.Config config;

    private SysIdRoutine.Mechanism mechanism;

    private SysIdRoutine sysIdRoutine;

    SysIdFalcon(int motorId1, int motorId2, int motorId3, int motorId4) {
        motor1 = new TalonFX(motorId1, SysIdFalconConstants.CANBUS_CHAIN);
        motor1.getConfigurator().apply(new TalonFXConfiguration());
        motor1.getConfigurator().apply(SysIdFalconConstants.SOFTWARE_LIMIT_SWITCH_CONFIGS);
        motor1.getConfigurator().apply(SysIdFalconConstants.HARDWARE_LIMIT_SWITCH_CONFIGS);
        motor1.getConfigurator().apply(SysIdFalconConstants.FEEDBACK_CONFIGS);

        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor1.setControl(sysIdControl.withOutput(volts.in(Volts))),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconConstants.SIGNAL_SPEED,
                motor1.getPosition(),
                motor1.getVelocity(),
                motor1.getMotorVoltage()
        );

        motor1.optimizeBusUtilization();


        motor2 = new TalonFX(motorId2, SysIdFalconConstants.CANBUS_CHAIN);
        motor2.getConfigurator().apply(new TalonFXConfiguration());
        motor2.getConfigurator().apply(SysIdFalconConstants.SOFTWARE_LIMIT_SWITCH_CONFIGS);
        motor2.getConfigurator().apply(SysIdFalconConstants.HARDWARE_LIMIT_SWITCH_CONFIGS);
        motor2.getConfigurator().apply(SysIdFalconConstants.FEEDBACK_CONFIGS);

        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor2.setControl(sysIdControl.withOutput(volts.in(Volts))),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconConstants.SIGNAL_SPEED,
                motor2.getPosition(),
                motor2.getVelocity(),
                motor2.getMotorVoltage()
        );

        motor2.optimizeBusUtilization();


        motor3 = new TalonFX(motorId3, SysIdFalconConstants.CANBUS_CHAIN);
        motor3.getConfigurator().apply(new TalonFXConfiguration());
        motor3.getConfigurator().apply(SysIdFalconConstants.SOFTWARE_LIMIT_SWITCH_CONFIGS);
        motor3.getConfigurator().apply(SysIdFalconConstants.HARDWARE_LIMIT_SWITCH_CONFIGS);
        motor3.getConfigurator().apply(SysIdFalconConstants.FEEDBACK_CONFIGS);

        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor3.setControl(sysIdControl.withOutput(volts.in(Volts))),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconConstants.SIGNAL_SPEED,
                motor3.getPosition(),
                motor3.getVelocity(),
                motor3.getMotorVoltage()
        );

        motor3.optimizeBusUtilization();


        motor4 = new TalonFX(motorId4, SysIdFalconConstants.CANBUS_CHAIN);
        motor4.getConfigurator().apply(new TalonFXConfiguration());
        motor4.getConfigurator().apply(SysIdFalconConstants.SOFTWARE_LIMIT_SWITCH_CONFIGS);
        motor4.getConfigurator().apply(SysIdFalconConstants.HARDWARE_LIMIT_SWITCH_CONFIGS);
        motor4.getConfigurator().apply(SysIdFalconConstants.FEEDBACK_CONFIGS);

        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor4.setControl(sysIdControl.withOutput(volts.in(Volts))),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconConstants.SIGNAL_SPEED,
                motor4.getPosition(),
                motor4.getVelocity(),
                motor4.getMotorVoltage()
        );

        motor4.optimizeBusUtilization();
    }

    public SysIdFalcon() {
        this(
                MK4iSwerveConstants.MK4I_MODULE_BACK_LEFT.linearMotorID,
                MK4iSwerveConstants.MK4I_MODULE_BACK_RIGHT.linearMotorID,
                MK4iSwerveConstants.MK4I_MODULE_FRONT_LEFT.linearMotorID,
                MK4iSwerveConstants.MK4I_MODULE_FRONT_RIGHT.linearMotorID
        );
    }

    public static void init() {
        if (instance == null) {
            instance = new SysIdFalcon();
        }
    }

    public static SysIdFalcon getInstance() {
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