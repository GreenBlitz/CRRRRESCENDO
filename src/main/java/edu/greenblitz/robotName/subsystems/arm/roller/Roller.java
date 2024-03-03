package edu.greenblitz.robotName.subsystems.arm.roller;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants.ROLL_BACKWARD_POWER;
import static edu.greenblitz.robotName.subsystems.arm.roller.RollerConstants.ROLL_FORWARD_POWER;

public class Roller extends GBSubsystem {

    private static Roller instance;

    private RollerInputsAutoLogged rollerInputs;

	private IRoller roller;

    private boolean objectWasIn;

    private Roller() {
        roller = RollerFactory.create();
        rollerInputs = new RollerInputsAutoLogged();
        roller.updateInputs(rollerInputs);
        objectWasIn = rollerInputs.isObjectIn;
    }

    public static void init() {
        if (instance == null) {
            instance = new Roller();
        }
    }

    public static Roller getInstance() {
        init();
        return instance;
    }

    public void periodic() {
        super.periodic();

        roller.updateInputs(rollerInputs);
        Logger.processInputs("Roller", rollerInputs);
        if (rollerInputs.isObjectIn){
            objectWasIn = true;
        }
        SmartDashboard.putBoolean("is ob in ", isObjectIn());
    }

    public void setPower(double power) {
        roller.setPower(power);
    }

    public void setVoltage(double voltage) {
        roller.setVoltage(voltage);
    }


    public void rollClockwise() {
        setPower(ROLL_FORWARD_POWER);
    }

    public void rollCounterClockwise() {
        setPower(ROLL_BACKWARD_POWER);
    }

    public void stop() {
        setPower(0);
    }

    public void resetEncoder(Rotation2d position) {
        roller.resetEncoder(position);
    }

    public void moveToPosition(Rotation2d position) {
        roller.moveToPosition(position);
    }

    public double getVoltage() {
        return rollerInputs.appliedOutput;
    }

    public void setObjectOut() {
        objectWasIn = false;
    }


    public boolean isObjectIn() {
        return rollerInputs.isObjectIn || objectWasIn;
    }

    public Rotation2d getAngle() {
        return rollerInputs.position;
    }

    public boolean isAtAngle(Rotation2d targetAngle) {
        return Math.abs(targetAngle.getRadians() - getAngle().getRadians()) <= RollerConstants.TOLERANCE.getRadians();
    }
}