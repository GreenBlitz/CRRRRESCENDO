package edu.greenblitz.robotName.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NoteToIntake extends IntakeCommand {
    
    private boolean isAuto;
    public NoteToIntake(boolean isAuto){
        super();
        this.isAuto = isAuto;
    }
    
    @Override
    public void initialize() {
        SmartDashboard.putNumber("init",1);
    }
    
    @Override
    public void execute() {
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return intake.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        if (!interrupted && !isAuto) {
            new NoteFromIntakeToShooter().schedule();
        }
    }
}