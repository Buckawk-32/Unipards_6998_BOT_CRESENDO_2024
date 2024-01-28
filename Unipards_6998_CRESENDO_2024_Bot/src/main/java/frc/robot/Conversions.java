package frc.robot;

public class Conversions {
    public static double CANCODER_TICKS_to_DEGREES(double POSITION_COUNTS, double GEAR_RATIO) {
        return POSITION_COUNTS * (360.0 / (GEAR_RATIO * 4096.0));
    }

    public static double DEGREES_to_CANCODER_TICKS (double DEGREES, double GEAR_RATIO) {
        return DEGREES / (360.0 / (GEAR_RATIO * 4096.0));
    }

    public static double FALCON_TICKS_to_DEGREES(double POSITION_COUNTS, double GEAR_RATIO) {
        return POSITION_COUNTS * (360.0 / (GEAR_RATIO * 2048.0));
    }

    public static double DEGREES_to_FALCON_TICKS(double DEGREES, double GEAR_RATIO) {
        return DEGREES / (360.0 / (GEAR_RATIO * 2048.0));
    }

    public static double FALCON_TICKS_to_RPM (double VELOCITY_COUNTS, double GEAR_RATIO) {
        double MOTOR_RPM = VELOCITY_COUNTS * (600.0 / 2048.0);
        double MECHANISM_RPM = MOTOR_RPM / GEAR_RATIO;
        return MECHANISM_RPM;
    }

    public static double RPM_to_FALCON_TICKS (double RPM, double GEAR_RATIO) {
        double MOTOR_RPM = RPM * GEAR_RATIO;
        double SENSOR_COUNTS = MOTOR_RPM * (2048.0 / 600.0);
        return SENSOR_COUNTS;
    }

    public static double FALCON_TICKS_to_MPS (double VELOCITY_COUNTS, double CIRCUMFERENCE, double GEAR_RATIO) {
        double WHEEL_RPM = FALCON_TICKS_to_RPM(VELOCITY_COUNTS, GEAR_RATIO);
        double WHEEL_MPS = (WHEEL_RPM * CIRCUMFERENCE) / 60;
        return WHEEL_MPS;
    }

    public static double MPS_to_FALCON_TICKS (double VELOCITY, double CIRCUMFERENCE, double GEAR_RATIO) {
        double WHEEL_RPM = ((VELOCITY * 60) / CIRCUMFERENCE);
        double WHEEL_VELOCITY = RPM_to_FALCON_TICKS(WHEEL_RPM, GEAR_RATIO);
        return WHEEL_VELOCITY;
    }

    public static double FALCON_TICKS_to_METERS (double POSITION_COUNTS, double CIRCUMFERENCE, double GEAR_RATIO) {
        return POSITION_COUNTS * (CIRCUMFERENCE / (GEAR_RATIO * 2048.0));
    }

    public static double METERS_to_FALCON_TICKS (double METERS, double CIRCUMFERENCE, double GEAR_RATIO) {
        return METERS / (CIRCUMFERENCE/ (GEAR_RATIO * 2048.0));
    }
}
