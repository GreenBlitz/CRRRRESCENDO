//package edu.greenblitz.robotName.commands.intake;
//
//import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;
//import edu.greenblitz.robotName.subsystems.Arm.Wrist;
//import edu.greenblitz.robotName.subsystems.shooter.Funnel;
//
//public class RunIntake extends IntakeCommand{
//    public Funnel funnel;
//    public Wrist wrist;
//    public RunIntake(){
//        super();
//        funnel = Funnel.getInstance();
//        wrist = Wrist.getInstance();
//        require(funnel);
//        require(wrist);
//    }
//
//    @Override
//    public void execute(){
//        intake.setPower(IntakeConstants.POWER_TO_RUN);
//    }
//
//    @Override
//    public boolean isFinished(){
//        return intake.getEntranceBeamBreakerValue() ||
//                intake.getExitBeamBreakerValue() ||
//                funnel.isObjectIn() ||
//                wrist.isObjectInside();
//    }
//
//    @Override
//    public void end(boolean interrupted){
//        intake.stop();
//    }
//}