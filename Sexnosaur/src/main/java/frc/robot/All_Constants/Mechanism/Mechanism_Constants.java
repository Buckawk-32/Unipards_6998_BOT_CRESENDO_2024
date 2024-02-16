package frc.robot.All_Constants.Mechanism;

public class Mechanism_Constants {

    public static final class INTAKE_CONSTANTS {
        public static final int INTAKE_MOTOR_1_ID = 5;
        public static final int INTAKE_MOTOR_2_ID = 6;

        public static final double INTAKE_KP = 0.01;
        public static final double INTAKE_KI = 0.0;
        public static final double INTAKE_KD = 0.0;
        public static final double INTAKE_KF = 0.0;

        public static final int INTAKE_CURRENT_LIMIT = 35;

        public static final double INTAKE_GEAR_RATIO = 15/1;

        public static final double INTAKE_RPM = 20;
    }

    public static final class SLIDER_CONSTANTS {
        public static final int SLIDER_MOTOR_ID = 7;

        public static final double SLIDER_KP = 0.0;
        public static final double SLIDER_KI = 0.0;
        public static final double SLIDER_KD = 0.0;
        public static final double SLIDER_KF = 0.0;

        public static final int SLIDER_CURRENT_LIMIT = 35;

        public static final double SLIDER_GEAR_RATIO = 10/1;

        public static final float SLIDER_MAXIMUM_LENGTH = 7.0f;
        public static final float SLIDER_MINIMUM_LENGTH = 0.0f;
    }

    public static final class SHOOTER_ANGLE_CONSTANTS {
        public static final int SHOOT_ANGLE_MOTOR_ID = 10;

        public static final double SHOOT_ANGLE_KP = 0.0;
        public static final double SHOOT_ANGLE_KI = 0.0;
        public static final double SHOOT_ANGLE_KD = 0.0;
        public static final double SHOOT_ANGLE_KF = 0.0;

        public static final int SHOOT_ANGLE_CURRENT_LIMIT = 35;

        public static final double SHOOT_ANGLE_GEAR_RATIO = 144/1;

        public static final float SHOOT_DEFAULT_ANGLE = 50.0f;
        public static final float SHOOT_MAXIMUM_ANGLE = 70.0f;
        public static final float SHOOT_MINIMUM_ANGLE = 30.0f;
    }

    public static class SHOOTER_DRIVE_CONSTANTS {

        public static final int SHOOTER_DRIVE_MOTOR_1_ID = 5;
        public static final int SHOOTER_DRIVE_MOTOR_2_ID = 6;

        public static final double SHOOTER_DRIVE_KP = 0.0;
        public static final double SHOOTER_DRIVE_KI = 0.0;
        public static final double SHOOTER_DRIVE_KD = 0.0;

        public static final int SHOOTER_DRIVE_CONTINOUS_CURRENT_LIMIT = 35;
        public static final int SHOOTER_DRIVE_PEAK_CURRENT_LIMIT = 40;
        public static final double SHOOTER_DRIVE_PEAK_CURRENT_DURATION = 0.1;
        public static final boolean SHOOTER_DRIVE_CURRENT_LIMIT_ENABLE = true;

        public static final double SHOOTER_DRIVE_GEAR_RATIO = 30/1;

        public static final double SHOOTER_DRIVE_RPM = 0.5;
    }

    public static class HANGER_CONSTANTS {
        public static final int HANG_MOTOR_1_ID = 8;
        public static final int HANG_MOTOR_2_ID = 9;

        public static final double HANGER_KP = 0.0;
        public static final double HANGER_KI = 0.0;
        public static final double HANGER_KD = 0.0;

        public static final int HANGER_CURRENT_LIMIT = 35;
        public static final float HANGER_MAXIMUM_HEIGHT = 100f;
        public static final float HANGER_MINIMUM_HEIGHT = 0f;

        public static final double HANGER_SETPOINT_MINIMUM = 0.0;
        public static final double HANGER_SETPOINT_MAXIMUM = 100.0;

        public static final double HANGER_GEAR_RATIO = 1/1;


    }
}
