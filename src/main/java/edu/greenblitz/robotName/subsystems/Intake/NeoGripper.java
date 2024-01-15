package edu.greenblitz.robotName.subsystems.Intake;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

import static edu.greenblitz.robotName.subsystems.Intake.GripperConstants.neoGripper.*;

public class NeoGripper implements IGripper {

    private GBSparkMax motor;

    public NeoGripper() {
        motor = new GBSparkMax(MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.config(GripperConstants.GRIPPER_CONFIG_OBJECT);
    }


    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void updateInputs(GripperInputs inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.velocity = motor.getEncoder().getVelocity();
    }
}
