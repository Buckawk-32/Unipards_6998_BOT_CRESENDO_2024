package frc.robot.All_Constants.Swerve;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class Swerve_Motion_Constants {
    public static final class SWEVE_CHASSIS_CONSTANTS {
        public final static double SWERVE_MAX_SPEED = 4.5;
        public final static double SWERVE_MAX_ANGULAR_VELOCITY = 4.5;
    }

    public static final class SWERVE_DRIVE_MOTOR_CONSTANTS {
        public final static int SWERVE_DRIVE_CONTINUOUS_CURRENT_LIMIT = 35;
        public final static int SWERVE_DRIVE_PEAK_CURRENT_LIMIT = 60;
        public final static double SWERVE_DRIVE_PEAK_CURRENT_DURATION = 0.1;
        public final static boolean SWERVE_DRIVE_CURRENT_LIMIT_ENABLE = true;
        public final static NeutralModeValue DRIVE_NEUTRAL_MODE = NeutralModeValue.Brake;
        public final static double SWERVE_DRIVE_MOTOR_OPENLOOPRAMP = 0.25;
        public final static double SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP = 0.0;

        public final static double SWERVE_DRIVE_KP = 0.0;
        public final static double SWERVE_DRIVE_KI = 0.0;
        public final static double SWERVE_DRIVE_KD = 0.0;
        public final static double SWERVE_DRIVE_KS = 0.0;
        public final static double SWERVE_DRIVE_KA = 0.0;
        public final static double SWERVE_DRIVE_KV = 0.0;
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