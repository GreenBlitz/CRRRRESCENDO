package edu.greenblitz.robotName.subsystems.Limelight;

import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

import java.util.Optional;


class Limelight {
    private NetworkTableEntry robotPoseEntry, idEntry;
    private String name;

    Limelight(String limelightName) {
        this.name = limelightName;
        String robotPoseQuery = "botpose_wpiblue";
        robotPoseEntry = NetworkTableInstance.getDefault().getTable(name).getEntry(robotPoseQuery);
        idEntry = NetworkTableInstance.getDefault().getTable(name).getEntry("tid");
    }
    
    public void updateRobotPoseEntry(){
        String robotPoseQuery = FMSUtils.getAlliance() == DriverStation.Alliance.Red ? "botpose_wpired" : "botpose_wpiblue";
        robotPoseEntry = NetworkTableInstance.getDefault().getTable(name).getEntry(robotPoseQuery);
    }



    public Optional<Pair<Pose2d, Double>> getUpdatedPoseEstimation() {
        //the botpose array is comprised of {0:x, 1:y, 2:z, 3:Roll, 4:Pitch, 5:Yaw, 6:total latency from capture to send}
        double[] poseArray = robotPoseEntry.getDoubleArray(new double[7]);
        double processingLatency = poseArray[6]/1000;
        double timestamp = Timer.getFPGATimestamp() -  processingLatency;
        int id = (int) idEntry.getInteger(-1);

        if (id == -1){
            return Optional.empty();
        }
        Pose2d robotPose = new Pose2d(poseArray[0], poseArray[1], Rotation2d.fromDegrees(poseArray[5]));
        return Optional.of(new Pair<>(robotPose, timestamp));

    }

    public boolean hasTarget() {
        return getUpdatedPoseEstimation().isPresent();
    }



}





