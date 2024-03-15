package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.utils.FMSUtils;

public class RotateToAmp extends RotateToAngle {

    public RotateToAmp() {
        super(
                FMSUtils.isRedAlliance() ? () -> FieldConstants.RED_AMP_ANGLE : () -> FieldConstants.BLUE_AMP_ANGLE
        );
    }
}
