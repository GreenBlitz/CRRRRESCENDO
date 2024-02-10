package edu.greenblitz.robotName.utils.SysId.NeoSysId;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.utils.SysId.NeoSysId.SysIdNeoConstants.MOTOR_ID;
import static edu.wpi.first.units.Units.Volts;

public class SysIdNeo extends GBSubsystem {
    private static SysIdNeo instance;

    private GBSparkMax motor;
    private SysIdRoutine.Config config;
    private SysIdRoutine.Mechanism mechanism;
    private SysIdRoutine sysIdRoutine;


    private SysIdNeo() {
        motor = new GBSparkMax(MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, SysIdNeoConstants.ENABLE_FORWARD_LIMIT);
        motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, SysIdNeoConstants.ENABLE_BACKWARD_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, SysIdNeoConstants.FORWARD_LIMIT);
        motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, SysIdNeoConstants.BACKWARD_LIMIT);

        config = new SysIdRoutine.Config(
                null,
                Volts.of(SysIdNeoConstants.DYNAMIC_VOLTAGE),
                null,
                (state) -> Logger.recordOutput("state", state.toString())
        );
        mechanism = new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> motor.setVoltage(volts.in(Volts)),
                null,
                this
        );
        sysIdRoutine = new SysIdRoutine(config, mechanism);

    }

    public static void init(){
        if (instance==null) {
            instance = new SysIdNeo();
        }
    }

    public static SysIdNeo getInstance(){
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

    public void buttons(SmartJoystick joystick){
        joystick.Y.whileTrue(instance.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
        joystick.A.whileTrue(instance.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
        joystick.B.whileTrue(instance.sysIdDynamic(SysIdRoutine.Direction.kForward));
        joystick.X.whileTrue(instance.sysIdDynamic(SysIdRoutine.Direction.kReverse));
    }

}
