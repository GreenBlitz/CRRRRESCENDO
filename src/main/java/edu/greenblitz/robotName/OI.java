package edu.greenblitz.robotName;



import com.pathplanner.lib.path.PathConstraints;
import edu.greenblitz.robotName.commands.auto.MoveToPose;
import edu.greenblitz.robotName.commands.swerve.SwerveCommand;
import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Pose2d;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;

import edu.wpi.first.math.geometry.Rotation2d;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        initButtons();
    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public SmartJoystick getMainJoystick() {
        return mainJoystick;
    }

    public SmartJoystick getSecondJoystick() {
        return secondJoystick;
    }


    public void initButtons(){
        // put buttons here
    }

}
