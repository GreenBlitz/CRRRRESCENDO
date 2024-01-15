package edu.greenblitz.robotName.subsystems.swerve.Chassis;

import edu.greenblitz.robotName.OdometryConstants;
import edu.greenblitz.robotName.VisionConstants;
import edu.greenblitz.robotName.subsystems.Gyros.GyroFactory;
import edu.greenblitz.robotName.subsystems.Gyros.GyroInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.Gyros.IAngleMeasurementGyro;
import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.Photonvision;
import edu.greenblitz.robotName.subsystems.swerve.Modules.SwerveModule;
import edu.greenblitz.robotName.subsystems.swerve.Modules.mk4iSwerveModule.MK4iSwerveConstants;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;
import org.photonvision.EstimatedRobotPose;

import java.util.Optional;

public class SwerveChassis extends GBSubsystem implements ISwerveChassis {

    public enum Module {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }


    private static SwerveChassis instance;
    private SwerveModule frontRight, frontLeft, backRight, backLeft;
    private IAngleMeasurementGyro gyro;
    private SwerveDriveKinematics kinematics;
    private SwerveDrivePoseEstimator poseEstimator;
    private SwerveDriveOdometry odometry;
    private Field2d field = new Field2d();
    public static final double TRANSLATION_TOLERANCE = 0.05;
    public static final double ROTATION_TOLERANCE = 2;
    private boolean doVision;
    public final double CURRENT_TOLERANCE = 0.5;

    private SwerveChassisInputsAutoLogged ChassisInputs = new SwerveChassisInputsAutoLogged();
    private GyroInputsAutoLogged gyroInputs = new GyroInputsAutoLogged();

    public SwerveChassis() {
        this.frontLeft = new SwerveModule(Module.FRONT_LEFT);
        this.frontRight = new SwerveModule(Module.FRONT_RIGHT);
        this.backLeft = new SwerveModule(Module.BACK_LEFT);
        this.backRight = new SwerveModule(Module.BACK_RIGHT);

        this.gyro = GyroFactory.create();

        doVision = true;


        this.kinematics = new SwerveDriveKinematics(
                ChassisConstants.SWERVE_LOCATIONS_IN_SWERVE_KINEMATICS_COORDINATES
        );
        this.poseEstimator = new SwerveDrivePoseEstimator(this.kinematics,
                getGyroAngle(),
                getSwerveModulePositions(),
                new Pose2d(new Translation2d(), new Rotation2d()),
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(VisionConstants.STANDARD_DEVIATION_ODOMETRY, VisionConstants.STANDARD_DEVIATION_ODOMETRY, VisionConstants.STANDARD_DEVIATION_ODOMETRY),
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(VisionConstants.STANDARD_DEVIATION_VISION2D, VisionConstants.STANDARD_DEVIATION_VISION2D, VisionConstants.STANDARD_DEVIATION_VISION_ANGLE));
        SmartDashboard.putData("field", getField());
        this.odometry = new SwerveDriveOdometry(this.kinematics, getGyroAngle(), getSwerveModulePositions());
    }


    public static SwerveChassis getInstance() {
        init();
        return instance;
    }


    public static void init() {
        if (instance == null) {
            instance = new SwerveChassis();
        }
    }

    @Override
    public void periodic() {
        frontLeft.periodic();
        frontRight.periodic();
        backLeft.periodic();
        backRight.periodic();

        field.setRobotPose(getRobotPose());


        gyro.updateInputs(gyroInputs);
        updateInputs(ChassisInputs);

        Logger.recordOutput("DriveTrain/RobotPose", getRobotPose());
        Logger.recordOutput("DriveTrain/ModuleStates", getSwerveModuleStates());
        Logger.processInputs("DriveTrain/Chassis", ChassisInputs);
        Logger.processInputs("DriveTrain/Gyro", gyroInputs);


        updatePoseEstimationLimeLight();
        updateOdometry();


        SmartDashboard.putData(getField());
    }

    public void resetChassisPosition(Pose2d pose) {
        poseEstimator.resetPosition(getGyroAngle(), getSwerveModulePositions(), pose);
    }

    /**
     * @return returns the swerve module based on its name
     */
    public SwerveModule getModule(Module module) {
        switch (module) {
            case BACK_LEFT:
                return backLeft;
            case BACK_RIGHT:
                return backRight;
            case FRONT_LEFT:
                return frontLeft;
            case FRONT_RIGHT:
                return frontRight;
        }
        return null;
    }

    /**
     * stops all the modules (power(0))
     */
    public void stop() {
        frontRight.stop();
        frontLeft.stop();
        backRight.stop();
        backLeft.stop();
    }

    /**
     * resetting all the angle motor's encoders to 0
     */
    public void resetEncodersByCalibrationRod() {
        getModule(Module.FRONT_LEFT).resetEncoderToValue();
        getModule(Module.FRONT_RIGHT).resetEncoderToValue();
        getModule(Module.BACK_LEFT).resetEncoderToValue();
        getModule(Module.BACK_RIGHT).resetEncoderToValue();
    }

    public void resetAllEncoders() {
        getModule(Module.FRONT_LEFT).resetEncoderByAbsoluteEncoder();
        getModule(Module.FRONT_RIGHT).resetEncoderByAbsoluteEncoder();
        getModule(Module.BACK_LEFT).resetEncoderByAbsoluteEncoder();
        getModule(Module.BACK_RIGHT).resetEncoderByAbsoluteEncoder();

    }


    public Rotation2d getModuleAbsoluteEncoderAngle(Module module) {
        return getModule(module).getAbsoluteEncoderPosition();
    }


    public Rotation2d getModuleAngle(Module module) {
        return getModule(module).getModuleAngle();
    }

    public boolean isModuleAtAngle(Module module, Rotation2d angle, Rotation2d errorAngleTolerance) {
        return getModule(module).isAtAngle(angle, errorAngleTolerance);
    }

    public void resetChassisPose() {
        gyro.updateYaw(Rotation2d.fromRadians(0));
        poseEstimator.resetPosition(getGyroAngle(), getSwerveModulePositions(), new Pose2d());
    }

    public void resetPoseByVision() {
        if (MultiLimelight.getInstance().isConnected()) {
            if (MultiLimelight.getInstance().getFirstAvailableTarget().isPresent()) {
                Pose2d visionPose = MultiLimelight.getInstance().getFirstAvailableTarget().get().getFirst();
                getGyro().updateYaw(Rotation2d.fromRadians(0));
                poseEstimator.resetPosition(getGyroAngle(), getSwerveModulePositions(), visionPose);
                odometry.resetPosition(getGyroAngle(), getSwerveModulePositions(), visionPose);
            }
        } else {
            resetChassisPose();
        }
    }

    /**
     * returns chassis angle in radians
     */
    private Rotation2d getGyroAngle() {
        return Rotation2d.fromRadians(gyroInputs.yaw);
    }

    public Rotation2d getChassisAngle() {
        return getRobotPose().getRotation();
    }


    /**
     * setting module states to all 4 modules
     */
    public void setModuleStates(SwerveModuleState[] states) {
        for (Module module : Module.values()) {
            setModuleStateForModule(module, states[module.ordinal()]);
        }
    }

    public void moveByChassisSpeeds(double forwardSpeed, double leftwardSpeed, double angSpeed, Rotation2d currentAng) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                forwardSpeed,
                leftwardSpeed,
                angSpeed,
                currentAng
        );
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
        SwerveModuleState[] desaturatedStates = desaturateSwerveModuleStates(states);
        setModuleStates(desaturatedStates);
    }

    public void moveByChassisSpeeds(ChassisSpeeds fieldRelativeSpeeds, Rotation2d currentAng) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                fieldRelativeSpeeds,
                currentAng
        );
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
        SwerveModuleState[] desaturatedStates = desaturateSwerveModuleStates(states);
        setModuleStates(desaturatedStates);
    }

    /**
     * makes sure no module is requested to move faster than possible by linearly scaling all module velocities to comply with the constraint
     *
     * @param states original velocity states computed from the kinematics
     * @return states that create the same ratio between speeds but scaled down
     */
    private static SwerveModuleState[] desaturateSwerveModuleStates(SwerveModuleState[] states) {
        double desaturationFactor = 1;
        for (SwerveModuleState state : states) {
            desaturationFactor = Math.max(desaturationFactor, state.speedMetersPerSecond / ChassisConstants.MAX_VELOCITY);
        }
        SwerveModuleState[] desaturatedStates = new SwerveModuleState[states.length];
        for (int i = 0; i < states.length; i++) {
            desaturatedStates[i] = new SwerveModuleState(states[i].speedMetersPerSecond / desaturationFactor, states[i].angle);
        }
        return desaturatedStates;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return kinematics.toChassisSpeeds(
                getSwerveModuleStates()
        );
    }

    public SwerveModulePosition[] getSwerveModulePositions() {
        return new SwerveModulePosition[]{
                frontLeft.getCurrentPosition(),
                frontRight.getCurrentPosition(),
                backLeft.getCurrentPosition(),
                backRight.getCurrentPosition()
        };
    }

    public SwerveModuleState[] getSwerveModuleStates() {
        SwerveModuleState[] moduleStates = new SwerveModuleState[Module.values().length];
        for (Module module : Module.values()) {
            moduleStates[module.ordinal()] = getModuleState(module);
        }
        return moduleStates;
    }

    public SwerveDriveKinematics getKinematics() {
        return this.kinematics;
    }

    public IAngleMeasurementGyro getGyro() {
        return gyro;
    }

    /**
     * moving a single module by module state
     */
    private void setModuleStateForModule(Module module, SwerveModuleState state) {
        getModule(module).setModuleState(state);
    }

    /**
     * for calibration purposes
     */
    public void rotateModuleByPower(Module module, double power) {
        getModule(module).setRotationalPower(power);
    }

    public void updatePoseEstimationPhotonVision() {
        poseEstimator.update(getGyroAngle(), getSwerveModulePositions());
        Photonvision.getInstance().getUpdatedPoseEstimator().ifPresent((EstimatedRobotPose pose) -> poseEstimator.addVisionMeasurement(pose.estimatedPose.toPose2d(), pose.timestampSeconds));
    }

    public void updatePoseEstimationLimeLight() {
        boolean poseDifference = odometry.getPoseMeters().getTranslation().getDistance(getRobotPose().getTranslation()) < OdometryConstants.MAX_DISTANCE_TO_FILTER_OUT;
        boolean shouldUpdateByOdometry = poseDifference && isRobotOnGround();
        if (shouldUpdateByOdometry) {
            poseEstimator.update(getGyroAngle(), getSwerveModulePositions());
        }
        if (doVision) {
            for (Optional<Pair<Pose2d, Double>> target : MultiLimelight.getInstance().getAllEstimates()) {
                target.ifPresent(this::addVisionMeasurement);
            }
        }
    }

    private boolean isModuleAtFreeCurrent(SwerveModule module) {
        return module.getLinearCurrent() > MK4iSwerveConstants.LINEAR_MOTOR_FREE_CURRENT - CURRENT_TOLERANCE && module.getLinearCurrent() < CURRENT_TOLERANCE + MK4iSwerveConstants.LINEAR_MOTOR_FREE_CURRENT;
    }

    public boolean isRobotOnGround() {

        boolean frontLeft = isModuleAtFreeCurrent(this.frontLeft);
        boolean frontRight = isModuleAtFreeCurrent(this.frontRight);
        boolean backLeft = isModuleAtFreeCurrent(this.backLeft);
        boolean backRight = isModuleAtFreeCurrent(this.backRight);

        return !(((frontLeft && frontRight) || (backLeft && backRight) || (frontLeft && backLeft) || (backRight && frontRight) || (frontLeft && backRight) || (frontRight && backLeft)));
    }

    public void updateOdometry() {
        odometry.update(getGyroAngle(), getSwerveModulePositions());
    }

    private void addVisionMeasurement(Pair<Pose2d, Double> poseTimestampPair) {
        Pose2d visionPose = poseTimestampPair.getFirst();
        if (!(visionPose.getTranslation().getDistance(SwerveChassis.getInstance().getRobotPose().getTranslation()) > VisionConstants.MIN_DISTANCE_TO_FILTER_OUT)) {
            resetToVision();
        }
    }

    public Pose2d getRobotPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void resetToVision() {
        Optional<Pair<Pose2d, Double>> visionOutput = MultiLimelight.getInstance().getFirstAvailableTarget();
        if (visionOutput.isPresent()) {
            poseEstimator.setVisionMeasurementStdDevs(new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0, 0, 0.6));
            visionOutput.ifPresent((pose2dDoublePair) -> resetChassisPose(pose2dDoublePair.getFirst()));
            poseEstimator.setVisionMeasurementStdDevs(new MatBuilder<>(Nat.N3(), Nat.N1()).fill(VisionConstants.STANDARD_DEVIATION_VISION2D, VisionConstants.STANDARD_DEVIATION_VISION2D, VisionConstants.STANDARD_DEVIATION_VISION_ANGLE));
            odometry.resetPosition(getGyroAngle(), getSwerveModulePositions(), MultiLimelight.getInstance().getFirstAvailableTarget().get().getFirst());
        }
    }

    public boolean isAtPose(Pose2d goalPose) {
        Pose2d robotPose = getRobotPose();

        boolean isAtX = Math.abs(goalPose.getX() - robotPose.getX()) <= TRANSLATION_TOLERANCE;
        boolean isAtY = Math.abs(goalPose.getY() - robotPose.getY()) <= TRANSLATION_TOLERANCE;

        Rotation2d angDifference = (goalPose.getRotation().minus(robotPose.getRotation()));
        boolean isAtAngle = angDifference.getRadians() <= ROTATION_TOLERANCE
                || (Math.PI * 2) - angDifference.getRadians() <= ROTATION_TOLERANCE;

        return isAtAngle && isAtX && isAtY;
    }

    public Sendable getField() {
        return field;
    }

    public SwerveModuleState getModuleState(Module module) {
        return getModule(module).getModuleState();
    }


    public boolean isModuleAtAngle(Module module, Rotation2d errorTolerance) {
        return getModule(module).isAtAngle(errorTolerance);
    }

    public void resetChassisPose(Pose2d pose) {
        poseEstimator.resetPosition(getGyroAngle(), getSwerveModulePositions(), pose);
    }

    public void moveByChassisSpeeds(ChassisSpeeds chassisSpeeds) {
        moveByChassisSpeeds(chassisSpeeds.vxMetersPerSecond,
                chassisSpeeds.vyMetersPerSecond,
                chassisSpeeds.omegaRadiansPerSecond,
                getChassisAngle()
        );
    }

    /**
     * set the idle mode of the linear motor to brake
     */
    public void setIdleModeBrake() {
        for (Module module : Module.values()) {
            getModule(module).setLinearIdleModeBrake();
        }
    }

    public void setIdleModeCoast() {
        for (Module module : Module.values()) {
            getModule(module).setLinearIdleModeCoast();
        }
    }

    public void setAngleMotorsIdleModeToBrake(boolean isBrake) {
        if (isBrake) {
            for (Module module : Module.values()) {
                getModule(module).setAngularIdleModeBrake();
            }
        } else {
            for (Module module : Module.values()) {
                getModule(module).setRotIdleModeCoast();
            }
        }
    }

    public boolean isEncoderBroken(Module module) {
        return getModule(module).isEncoderBroken();
    }

    public boolean isEncoderBroken() {
        boolean broken = false;
        for (Module module : Module.values()) {
            broken |= isEncoderBroken(module);
        }
        return broken;
    }

    public void disableVision() {
        doVision = false;
    }

    public void enableVision() {
        doVision = true;
    }

    @Override
    public void updateInputs(SwerveChassisInputsAutoLogged inputs) {
        inputs.isVisionEnabled = doVision;
        inputs.numberOfDetectedAprilTag = MultiLimelight.getInstance().getAllEstimates().size();
        inputs.omegaRadiansPerSecond = getChassisSpeeds().omegaRadiansPerSecond;
        inputs.xAxisSpeed = getChassisSpeeds().vxMetersPerSecond;
        inputs.yAxisSpeed = getChassisSpeeds().vyMetersPerSecond;
    }
}