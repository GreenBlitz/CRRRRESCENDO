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

public class SysIdFalcon extends GBSubsystem {

    private static SysIdFalcon instance;

    private TalonFX motorLB;
    private TalonFX motorRB;
    private TalonFX motorLF;
    private TalonFX motorRF;

    private VoltageOut sysIdControl;

    private SysIdRoutine.Config config;

    private SysIdRoutine.Mechanism mechanism;

    private SysIdRoutine sysIdRoutine;

    private SysIdFalcon() {
        motorLB = new TalonFX(SysIdFalconConstants.MOTOR_LB, SysIdFalconConstants.CANBUS_CHAIN);
        motorRB = new TalonFX(SysIdFalconConstants.MOTOR_RB, SysIdFalconConstants.CANBUS_CHAIN);
        motorLF = new TalonFX(SysIdFalconConstants.MOTOR_LF, SysIdFalconConstants.CANBUS_CHAIN);
        motorRF = new TalonFX(SysIdFalconConstants.MOTOR_RF, SysIdFalconConstants.CANBUS_CHAIN);
        motorLB.getConfigurator().apply(new TalonFXConfiguration());
        motorRB.getConfigurator().apply(new TalonFXConfiguration());
        motorLF.getConfigurator().apply(new TalonFXConfiguration());
        motorRF.getConfigurator().apply(new TalonFXConfiguration());
        motorLB.getConfigurator().apply(SysIdFalconConstants.a);
        motorRB.getConfigurator().apply(SysIdFalconConstants.a);
        motorRF.getConfigurator().apply(SysIdFalconConstants.a);
        motorLF.getConfigurator().apply(SysIdFalconConstants.a);
        motorLB.setInverted(false);
        motorRB.setInverted(false);
        motorLF.setInverted(false);
        motorRF.setInverted(true);


        sysIdControl = new VoltageOut(0);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdFalconConstants.DYNAMIC_VOLTAGE),
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
                SysIdFalconConstants.SIGNAL_SPEED,
                motorLB.getPosition(),
                motorLB.getVelocity(),
                motorLB.getMotorVoltage(),
                motorRB.getPosition(),
                motorRB.getVelocity(),
                motorRB.getMotorVoltage(),
                motorLF.getPosition(),
                motorLF.getVelocity(),
                motorLF.getMotorVoltage(),
                motorRF.getPosition(),
                motorRF.getVelocity(),
                motorRF.getMotorVoltage()
        );

        motorLB.optimizeBusUtilization();
        motorRB.optimizeBusUtilization();
        motorLF.optimizeBusUtilization();
        motorRF.optimizeBusUtilization();
    }

    
    public void setControl(Measure<Voltage> volts){
        motorLB.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorRB.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorLF.setControl(sysIdControl.withOutput(volts.in(Volts)));
        motorRF.setControl(sysIdControl.withOutput(volts.in(Volts)));
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