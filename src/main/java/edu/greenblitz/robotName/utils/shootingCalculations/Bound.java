package edu.greenblitz.robotName.utils.shootingCalculations;

import edu.wpi.first.math.geometry.Translation2d;

public class Bound {
    private Translation2d lowerLimit;
    private Translation2d upperLimit;

    public Bound(Translation2d lowerLimit, Translation2d upperLimit){
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public Translation2d getLowerLimit(){
        return lowerLimit;
    }

    public Translation2d getUpperLimit(){
        return upperLimit;
    }
}
