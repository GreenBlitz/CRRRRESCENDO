package edu.greenblitz.robotName.utils.SysId.falconSysId;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.SignalLogger;
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

public class SysIdFalcon extends GBSubsystem {

    private static SysIdFalcon instance;

    private TalonFX motor;

    private VoltageOut sysIdControl;

    private SysIdRoutine.Config config;

    private SysIdRoutine.Mechanism mechanism;

    private SysIdRoutine sysIdRoutine;

    private SysIdFalcon() {
        motor = new TalonFX(SysIdFalconConstants.MOTOR_ID, SysIdFalconConstants.CANBUS_CHAIN);
        motor.getConfigurator().apply(SysIdFalconConstants.LIMIT_SWITCH_CONFIGS);
        motor.getConfigurator().apply(SysIdFalconConstants.FEEDBACK_CONFIGS);

        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> SignalLogger.writeString("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor.setControl(sysIdControl.withOutput(volts.in(Volts))),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

        BaseStatusSignal.setUpdateFrequencyForAll(
                SysIdFalconConstants.SIGNAL_SPEED,
                motor.getPosition(),
                motor.getVelocity(),
                motor.getMotorVoltage()
        );

        motor.optimizeBusUtilization();
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