package frc.robot.subsystem;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Swerve.Swerve_Module_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Motion_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Type_Constants;
import frc.robot.SwerveModule;

public class SwerveSubsystem extends SubsystemBase {
    private final static SwerveSubsystem INSTANCE = new SwerveSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static SwerveSubsystem getInstance() {
        return INSTANCE;
    }

    public SwerveDriveOdometry SWERVE_ODOMETRY;
    public SwerveDriveKinematics SWERVE_KINEMATICS;
    public SwerveModule[] SWERVE_MODULES;
    public AHRS NAVx;

    public SwerveSubsystem() {

        SWERVE_KINEMATICS = new SwerveDriveKinematics(
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_FRONT_LEFT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_BACK_LEFT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_FRONT_RIGHT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_BACK_RIGHT_LOCATION
        );

        NAVx = new AHRS(SerialPort.Port.kMXP);
        NAVx.reset();

        SWERVE_MODULES = new SwerveModule[] {
            new SwerveModule(
                    0,
                    Swerve_Type_Constants.SDS_MK4i_L1(),
                    Swerve_Module_Constants.FRONT_LEFT.FRONT_LEFT_DRIVE_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_LEFT.FRONT_LEFT_ANGLE_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_LEFT.FRONT_LEFT_CANCODER_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_LEFT.FRONT_LEFT_ANGLE_OFFSET
            ),

            new SwerveModule(
                    1,
                    Swerve_Type_Constants.SDS_MK4i_L1(),
                    Swerve_Module_Constants.BACK_LEFT.BACK_LEFT_DRIVE_MOTOR_ID,
                    Swerve_Module_Constants.BACK_LEFT.BACK_LEFT_ANGLE_MOTOR_ID,
                    Swerve_Module_Constants.BACK_LEFT.BACK_LEFT_CANCODER_MOTOR_ID,
                    Swerve_Module_Constants.BACK_LEFT.BACK_LEFT_ANGLE_OFFSET
            ),

            new SwerveModule(
                    2,
                    Swerve_Type_Constants.SDS_MK4i_L1(),
                    Swerve_Module_Constants.BACK_RIGHT.BACK_RIGHT_DRIVE_MOTOR_ID,
                    Swerve_Module_Constants.BACK_RIGHT.BACK_RIGHT_ANGLE_MOTOR_ID,
                    Swerve_Module_Constants.BACK_RIGHT.BACK_RIGHT_CANCODER_MOTOR_ID,
                    Swerve_Module_Constants.BACK_RIGHT.BACK_RIGHT_ANGLE_OFFSET
            ),

            new SwerveModule(
                    3,
                    Swerve_Type_Constants.SDS_MK4i_L1(),
                    Swerve_Module_Constants.FRONT_RIGHT.FRONT_RIGHT_DRIVE_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_RIGHT.FRONT_RIGHT_ANGLE_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_RIGHT.FRONT_RIGHT_CANCODER_MOTOR_ID,
                    Swerve_Module_Constants.FRONT_RIGHT.FRONT_RIGHT_ANGLE_OFFSET
            )

        };
        Timer.delay(1);
        resetModulesToAbsolute();

        SWERVE_ODOMETRY = new SwerveDriveOdometry(
                SWERVE_KINEMATICS, getYaw(), getModulePositions()
        );

    }

    public void drive(Translation2d TRANSLATION, double ROTATION, boolean IS_FIELD_RELATIVE, boolean IS_OPEN_LOOP) {
        SwerveModuleState[] SWERVE_MODULE_STATES =
                SWERVE_KINEMATICS.toSwerveModuleStates(
                    IS_FIELD_RELATIVE ? ChassisSpeeds.fromFieldRelativeSpeeds(
                            TRANSLATION.getX(),
                            TRANSLATION.getY(),
                            ROTATION,
                            getYaw()
                    )
                    : new ChassisSpeeds(
                            TRANSLATION.getX(),
                            TRANSLATION.getY(),
                            ROTATION
                    )
                );

        SwerveDriveKinematics.desaturateWheelSpeeds(SWERVE_MODULE_STATES, Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED);

        for (SwerveModule module : SWERVE_MODULES) {
            module.setDesiredState(SWERVE_MODULE_STATES[module.module_num], IS_OPEN_LOOP);
        }

    }

    public void setModuleStates(SwerveModuleState[] DESIRED_STATES) {
        SwerveDriveKinematics.desaturateWheelSpeeds(DESIRED_STATES, Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED);

        for (SwerveModule module : SWERVE_MODULES) {
            module.setDesiredState(DESIRED_STATES[module.module_num], false);
        }
    }

    public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (SwerveModule module : SWERVE_MODULES) {
            states[module.module_num] = module.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (SwerveModule module : SWERVE_MODULES) {
            positions[module.module_num] = module.getPosition();
        }
        return positions;
    }

    public Pose2d getPose() {
        return SWERVE_ODOMETRY.getPoseMeters();
    }

    public void resetOdometry (Pose2d POSE) {
        SWERVE_ODOMETRY.resetPosition(getYaw(), getModulePositions(), POSE);
    }

    public void zeroGyro() {
        NAVx.reset();
    }

    public Rotation2d getYaw() {
        return (Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.NAVX_INVERTED) ? Rotation2d.fromDegrees(360 - NAVx.getYaw()) : Rotation2d.fromDegrees(NAVx.getYaw());
    }


    public void resetModulesToAbsolute() {
        for(SwerveModule module : SWERVE_MODULES) {
            module.reset_to_Absolute_0();
        }
    }

    @Override
    public void periodic() {
        SWERVE_ODOMETRY.update(getYaw(), getModulePositions());

        for (SwerveModule module : SWERVE_MODULES) {
            SmartDashboard.putNumber("Swerve Module" + module.module_num + "Angle --> CANCoder Value (In Degrees)", module.getCANcoder().getDegrees());
            SmartDashboard.putNumber("Swerve Module" + module.module_num + "Angle --> CANCoder Value (In Rotations)", module.getCANcoder().getRotations());
            SmartDashboard.putNumber("Swerve Module" + module.module_num + "Angle --> Angle Motor Value", module.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Swerve Module" + module.module_num + "Velocity", module.getState().speedMetersPerSecond);
        }
    }
}

