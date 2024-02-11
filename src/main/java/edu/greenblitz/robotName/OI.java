package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.Climb.lifter.ExtendLifter;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteToMiddleOfRoller;
import edu.greenblitz.robotName.commands.arm.roller.RunByPower.RunRollerCounterClockwiseUntilNoteIsInside;
import edu.greenblitz.robotName.commands.arm.roller.RunRollerByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingState;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoid;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;

public class OI {

    private static OI instance;

    private final SmartJoystick mainJoystick;

    private final SmartJoystick secondJoystick;

    private final SmartJoystick thirdJoystick;

    private final SmartJoystick fourthJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        thirdJoystick = new SmartJoystick(RobotConstants.Joystick.THIRD);
        fourthJoystick = new SmartJoystick(RobotConstants.Joystick.FOURTH);

        initButtons();
        initializeDefaultCommands();
    }

    public static void init(){
        if (instance == null) {
            instance = new OI();
        }
    }

    public static OI getInstance() {
        init();
        return instance;
    }

    public SmartJoystick getMainJoystick() {
        return mainJoystick;
    }

    public SmartJoystick getSecondJoystick() {
        return secondJoystick;
    }

    public SmartJoystick getThirdJoystick() {
        return thirdJoystick;
    }

    public SmartJoystick getFourthJoystick() {
        return fourthJoystick;
    }
    
    public void initButtons() {
        mainJoystick.A.whileTrue(new ExtendLifter());

        mainJoystick.A.whileTrue(new GoToShootingState());
    }

    public void thirdJoystickButtons(){
        SmartJoystick usedJoystick = thirdJoystick;
        usedJoystick.A.whileTrue(new RunRollerByJoystick(usedJoystick));
        usedJoystick.A.whileTrue(new RunIntakeByJoystick(usedJoystick));
        usedJoystick.B.whileTrue(new RunFunnelByJoystick(usedJoystick));
        usedJoystick.B.whileTrue(new RunFlyWheelByJoystick(usedJoystick));
    }

    public void initializeDefaultCommands() {
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
    }


}
