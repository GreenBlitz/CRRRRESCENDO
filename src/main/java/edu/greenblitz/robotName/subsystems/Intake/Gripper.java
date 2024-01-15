package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Intake.GripperConstants.DEFAULT_GRIPPER_POWER;

public class Gripper extends GBSubsystem {
    private static Gripper instance;
    private final IGripper gripper;
    private final GripperInputsAutoLogged gripperInputs;

    public static Gripper getInstance() {
        init();
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new Gripper();
        }
    }

    private Gripper() {
        gripper = GripperFactory.create();
        gripperInputs = new GripperInputsAutoLogged();
    }

    @Override
    public void periodic() {
        gripper.updateInputs(gripperInputs);
        Logger.processInputs("Intake/Gripper", gripperInputs);
    }

    public void roll(double power) {
        gripper.setPower(power);
    }

    public void rollIn() {
        roll(DEFAULT_GRIPPER_POWER);
    }

    public void rollOut() {
        roll(-DEFAULT_GRIPPER_POWER);
    }

    public void stop() {
        roll(0);
    }

}
