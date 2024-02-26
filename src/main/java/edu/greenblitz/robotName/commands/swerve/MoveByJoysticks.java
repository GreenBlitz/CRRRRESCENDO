package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

import java.util.function.DoubleSupplier;

public class MoveByJoysticks extends SwerveCommand {
    static double ANG_SPEED_FACTOR = 5;
    static double LIN_SPEED_FACTOR = ChassisConstants.MAX_VELOCITY;
    static double SLOW_ANG_SPEED_FACTOR = 0.25 * Math.PI;
    static double SLOW_LIN_SPEED_FACTOR = 0.5;
    private DoubleSupplier angSupplier;
    private boolean isSlow;
<<<<<<< HEAD


    public CombineJoystickMovement(boolean isSlow, DoubleSupplier angSupplier) {
        this.isSlow = isSlow;
        this.angSupplier = angSupplier;
    }

    public CombineJoystickMovement(boolean isSlow) {
        this(isSlow, () -> -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X));
=======
    
    
    public MoveByJoysticks(boolean isSlow, DoubleSupplier angSupplier) {
        this.isSlow = isSlow;
        this.angSupplier = angSupplier;
    }
    
    public MoveByJoysticks(boolean isSlow) {
        this(isSlow, () -> -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X));
    }
    public MoveByJoysticks(boolean isSlow, boolean isFast) {
        this(false, () -> -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X) * (isFast ? 2 : 1));
>>>>>>> edfb9acb6f695f7472b28fb25905e2f51ea279fd
    }
    
    @Override
    public void initialize() {
        ANG_SPEED_FACTOR = 5;
        LIN_SPEED_FACTOR = ChassisConstants.MAX_VELOCITY;
        if (isSlow) {
            ANG_SPEED_FACTOR = SLOW_ANG_SPEED_FACTOR;
            LIN_SPEED_FACTOR = SLOW_LIN_SPEED_FACTOR;
        }
    }
<<<<<<< HEAD


=======
    
    
>>>>>>> edfb9acb6f695f7472b28fb25905e2f51ea279fd
    public void execute() {
        double leftwardSpeed = -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X) * LIN_SPEED_FACTOR;
        double forwardSpeed = OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y) * LIN_SPEED_FACTOR;
        double angSpeed = angSupplier.getAsDouble() * ANG_SPEED_FACTOR;
        angSpeed = Math.min(angSpeed, ANG_SPEED_FACTOR);
        if (forwardSpeed == 0 && leftwardSpeed == 0 && angSpeed == 0) {
            swerveChassis.stop();
            return;
        }
        swerveChassis.moveByChassisSpeeds(forwardSpeed, leftwardSpeed, angSpeed,
                swerveChassis.getChassisAngle());
    }
<<<<<<< HEAD

}
=======
    
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        swerveChassis.stop();
    }
}
>>>>>>> edfb9acb6f695f7472b28fb25905e2f51ea279fd
