package frc.robot.All_Constants.Swerve;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class Swerve_Motion_Constants {
    public static final class SWERVE_CHASSIS_CONSTANTS {
        public final static double SWERVE_MAX_SPEED = 4.5;
        public final static double SWERVE_MAX_ANGULAR_VELOCITY = 4.5;
        public final static double SWERVE_CHASSIS_LENGTH_METERS = 0.62865;
        public final static double SWERVE_CHASSIS_WIDTH_METERS = 0.62865;

        public final static Translation2d SWERVE_FRONT_LEFT_LOCATION = new Translation2d(
                SWERVE_CHASSIS_LENGTH_METERS / 2, SWERVE_CHASSIS_WIDTH_METERS / 2
        );
        public final static Translation2d SWERVE_BACK_LEFT_LOCATION = new Translation2d(
                -SWERVE_CHASSIS_LENGTH_METERS / 2, SWERVE_CHASSIS_WIDTH_METERS / 2
        );
        public final static Translation2d SWERVE_FRONT_RIGHT_LOCATION = new Translation2d(
                SWERVE_CHASSIS_LENGTH_METERS / 2, -SWERVE_CHASSIS_WIDTH_METERS / 2
        );
        public final static Translation2d SWERVE_BACK_RIGHT_LOCATION = new Translation2d(
                -SWERVE_CHASSIS_LENGTH_METERS / 2, -SWERVE_CHASSIS_WIDTH_METERS / 2
        );

        public final static SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_FRONT_LEFT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_BACK_LEFT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_FRONT_RIGHT_LOCATION,
                Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_BACK_RIGHT_LOCATION
        );

        public final static boolean NAVX_INVERTED = true;
        public final static double SWERVE_DEADBAND = 0.05;
        public final static double MAX_VOLTAGE = 12.0;
    }

    public static final class SWERVE_DRIVE_MOTOR_CONSTANTS {
        public final static int SWERVE_DRIVE_CONTINUOUS_CURRENT_LIMIT = 35;
        public final static int SWERVE_DRIVE_PEAK_CURRENT_LIMIT = 60;
        public final static double SWERVE_DRIVE_PEAK_CURRENT_DURATION = 0.1;
        public final static boolean SWERVE_DRIVE_CURRENT_LIMIT_ENABLE = true;
        public final static NeutralModeValue DRIVE_NEUTRAL_MODE = NeutralModeValue.Brake;
        public final static double SWERVE_DRIVE_MOTOR_OPENLOOPRAMP = 0.25;
        public final static double SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP = 0.0;

        public final static double SWERVE_DRIVE_KP = 0.05;
        public final static double SWERVE_DRIVE_KI = 0.0;
        public final static double SWERVE_DRIVE_KD = 0.0;
        public final static double SWERVE_DRIVE_KS = (0.32 / SWERVE_CHASSIS_CONSTANTS.MAX_VOLTAGE);
        public final static double SWERVE_DRIVE_KA = (1.51 / SWERVE_CHASSIS_CONSTANTS.MAX_VOLTAGE);
        public final static double SWERVE_DRIVE_KV = (0.27 / SWERVE_CHASSIS_CONSTANTS.MAX_VOLTAGE);
    }

    public static final class SWERVE_ANGLE_MOTOR_CONSTANTS {
        public static final int SWERVE_ANGLE_CONTINUOUS_CURRENT_LIMIT = 25;
        public static final int SWERVE_ANGLE_CURRENT_THRESHOLD = 40;
        public static final double SWERVE_ANGLE_CURRENT_THRESHOLD_TIME = 0.1;
        public static final boolean SWERVE_ANGLE_ENABLE_CURRENT_LIMIT = true;

        public static final NeutralModeValue SWERVE_ANGLE_NEUTRAL_MODE = NeutralModeValue.Brake;

        public static final double SWERVE_ANGLE_OPENLOOPRAMP = 0.25;
        public static final double SWERVE_ANGLE_CLOSEDLOOPRAMP = 0.0;
    }


}
