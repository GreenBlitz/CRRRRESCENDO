package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;

public class CheckRunFunnel extends SystemCheckCommand {

    protected Funnel funnel;

    public CheckRunFunnel() {
        funnel = Funnel.getInstance();
        require(funnel);
    }

    @Override
    public void execute() {
        funnel.setPower(FunnelConstants.ROLL_POWER);
    }

    @Override
    public boolean isFinished() {
        return funnel.getVoltage() > 0;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        funnel.stop();
    }
}
