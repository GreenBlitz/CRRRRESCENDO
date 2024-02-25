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

    private TalonFX motorLB;
    private TalonFX motorRB;
    private TalonFX motorLF;
    private TalonFX motorRF;

    private VoltageOut sysIdControl;

    private SysIdRoutine.Config config;

    private SysIdRoutine.Mechanism mechanism;

    private SysIdRoutine sysIdRoutine;

    private SysIdFalconSwerve() {
        motorLB = new TalonFX(SysIdFalconSwerveConstants.MOTOR_LB, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorRB = new TalonFX(SysIdFalconSwerveConstants.MOTOR_RB, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorLF = new TalonFX(SysIdFalconSwerveConstants.MOTOR_LF, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorRF = new TalonFX(SysIdFalconSwerveConstants.MOTOR_RF, SysIdFalconSwerveConstants.CANBUS_CHAIN);
        motorLB.getConfigurator().apply(new TalonFXConfiguration());
        motorRB.getConfigurator().apply(new TalonFXConfiguration());
        motorLF.getConfigurator().apply(new TalonFXConfiguration());
        motorRF.getConfigurator().apply(new TalonFXConfiguration());
        motorLB.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorRB.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorRF.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorLF.getConfigurator().apply(SysIdFalconSwerveConstants.a);
        motorLB.setInverted(false);
        motorRB.setInverted(false);
        motorLF.setInverted(false);
        motorRF.setInverted(true);


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