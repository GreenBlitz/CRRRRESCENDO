package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPower;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ReverseRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AlignNoteInFunnel extends SequentialCommandGroup {

    public AlignNoteInFunnel(){
        super(
                new ReverseRunFunnelUntilObjectOut(),
                new RunFunnelByVelocity(FunnelConstants.INTAKE_VELOCITY).until(() -> Funnel.getInstance().isObjectIn())
        );
    }

}
