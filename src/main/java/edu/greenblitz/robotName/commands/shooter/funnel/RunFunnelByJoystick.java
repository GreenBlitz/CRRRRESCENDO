//package edu.greenblitz.robotName.commands.shooter.funnel;
//
//import edu.greenblitz.robotName.RobotConstants;
//import edu.greenblitz.robotName.utils.hid.SmartJoystick;
//
//public class RunFunnelByJoystick extends FunnelCommand{
//
//    private SmartJoystick joystick;
//
//    public RunFunnelByJoystick(SmartJoystick joystick){
//        super();
//        this.joystick = joystick;
//    }
//
//    @Override
//    public void execute() {
//        funnel.setPower(joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR);
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        funnel.setPower(0);
//    }
//
//}
