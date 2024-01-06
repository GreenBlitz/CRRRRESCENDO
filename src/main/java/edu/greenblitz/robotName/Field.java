package edu.greenblitz.robotName;

import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.geometry.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Field {
    /**
     * gets pose, returns it fitted to the other alliance ("mirrored") and rotated by 180 degrees.
     * */

    public static final boolean IS_AT_HADREAM_MIRRORD = false;

    public static Pose2d mirrorPositionToOtherSide(Pose2d pose){
        Pose2d mirroredPose = new Pose2d(
                pose.getX(),
                FieldConstants.fieldWidth - pose.getY(),
                pose.getRotation());
        return mirroredPose;
    }
    public static Pose2d mirrorToHadarimPosition(Pose2d pose){
        Pose2d mirroredPose = new Pose2d(
                pose.getX() - FieldConstants.fieldLength,
                pose.getY(),
                pose.getRotation());
        return mirroredPose;
    }


    /**
     * gets pose[], returns it fitted to the other alliance ("mirrored") and rotated by 180 degrees.
     * */
    public static Pose2d[] mirrorPositionsToOtherSide(Pose2d[] poses){
        Pose2d[] mirroredPoses = new Pose2d[poses.length];
        for (int i = 0; i< poses.length; i++ ) {
            mirroredPoses[i] = mirrorPositionToOtherSide(poses[i]);
        }
        return mirroredPoses;
    }

    public static Pose2d[] mirrorPositionsToHadarim(Pose2d[] poses){
        Pose2d[] mirroredPoses = new Pose2d[poses.length];
        for (int i = 0; i< poses.length; i++ ) {
            mirroredPoses[i] = mirrorToHadarimPosition(poses[i]);
        }
        return mirroredPoses;
    }

    public static class Apriltags{
        public static int selectedTagId = 1;
        public static final Pose3d redApriltagLocationId1 = new Pose3d(new Translation3d(15.513558, 1.071626, 0), new Rotation3d(0, 0, Math.PI));
        public static final Pose3d redApriltagLocationId2 = new Pose3d(new Translation3d(15.513558, 2.748026, 0), new Rotation3d(0, 0, Math.PI));
        public static final Pose3d redApriltagLocationId3 = new Pose3d(new Translation3d(15.513558, 4.424426, 0), new Rotation3d(0, 0, Math.PI));
        public static final Pose3d blueApriltagLocationId1 = new Pose3d(new Translation3d(1.02743, 4.424426, 0), new Rotation3d(0, 0, Math.PI));
        public static final Pose3d blueApriltagLocationId2 = new Pose3d(new Translation3d(1.02743, 2.748026, 0), new Rotation3d(0, 0, Math.PI));
        public static final Pose3d blueApriltagLocationId3 = new Pose3d(new Translation3d(1.02743, 1.071626, 0), new Rotation3d(0, 0, Math.PI));
        static List<AprilTag> apriltags = new ArrayList<>(9) ;

        static {
            apriltags.add(new AprilTag(1, redApriltagLocationId1));
            apriltags.add(new AprilTag(2,redApriltagLocationId2));
            apriltags.add(new AprilTag(3,redApriltagLocationId3));
            apriltags.add(new AprilTag(6,blueApriltagLocationId1));
            apriltags.add(new AprilTag(7,blueApriltagLocationId2));
            apriltags.add(new AprilTag(8,blueApriltagLocationId3));
        }
        public static final AprilTagFieldLayout aprilTagFieldLayout = new AprilTagFieldLayout(apriltags,10,10);

    }
    public static class PlacementLocations{
        // the locations of the grid plus half of the robot length.
        private static final Pose2d[] locationsOnBlueSide = {
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 0.508 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 1.067 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 1.626 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 2.184 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 2.743 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 3.302 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 3.861 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 4.420 +0.36), new Rotation2d(Math.PI)),
                new Pose2d(new Translation2d(1.43 + (0.5 * RobotConstants.Frankenstein.ROBOT_LENGTH_IN_METERS) + RobotConstants.Frankenstein.BUMPER_LENGTH + 0.45 , 4.978 +0.36), new Rotation2d(Math.PI))
        };

        public static Pose2d[] getLocationsOnBlueSide(){
            if(IS_AT_HADREAM_MIRRORD){
                return mirrorPositionsToHadarim(locationsOnBlueSide);
            }
            return locationsOnBlueSide;
        }

        public static Pose2d[] getLocationsOnRedSide(){
            if(IS_AT_HADREAM_MIRRORD){
                return mirrorPositionsToHadarim(mirrorPositionsToOtherSide(locationsOnBlueSide));
            }
            return mirrorPositionsToOtherSide(locationsOnBlueSide);
        }

        private static final HashSet<Integer> CUBE_INDICES= new HashSet<>(Arrays.asList(1,4,7));

        public static boolean isGridPositionIDofCube(int index){
            return CUBE_INDICES.contains(index);
        }

        public static boolean isGridPositionIDofCone(int index){
            return !isGridPositionIDofCube(index);
        }

        public static final Pose2d OUT_PRE_BALANCE_BLUE = new Pose2d(5.40, 2.67, new Rotation2d(Math.PI));
    }
    public static class FieldConstants{
        public final static double fieldLength = 16.54175;
        public final static double fieldWidth = 8.0137;
    }
}
