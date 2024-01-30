package frc.lib.util;

import edu.wpi.first.math.util.Units;

public class Swerve_Type_Constants {

    public double WHEEL_DIAMETER;
    public double WHEEL_CIRCUMFERENCE;
    public double ANGLE_GEAR_RATIO;
    public double DRIVE_GEAR_RATIO;
    public double ANGLE_PID[];

    public boolean DRIVE_MOTOR_INVERT;
    public boolean ANGLE_MOTOR_INVERT;
    public boolean CANCODER_INVERT;


    public Swerve_Type_Constants(
            double WHEEL_DIAMETER,
            double ANGLE_GEAR_RATIO,
            double DRIVE_GEAR_RATIO,
            double ANGLE_PID[],
            boolean DRIVE_MOTOR_INVERT,
            boolean ANGLE_MOTOR_INVERT,
            boolean CANCODER_INVERT
    ) {
        this.WHEEL_DIAMETER = WHEEL_DIAMETER;
        this.WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
        this.ANGLE_GEAR_RATIO = ANGLE_GEAR_RATIO;
        this.DRIVE_GEAR_RATIO = DRIVE_GEAR_RATIO;
        this.ANGLE_PID = ANGLE_PID;

        this.DRIVE_MOTOR_INVERT = DRIVE_MOTOR_INVERT;
        this.ANGLE_MOTOR_INVERT = ANGLE_MOTOR_INVERT;
        this.CANCODER_INVERT = CANCODER_INVERT;
    }

    // TODO: Need to add SDS other types
    public static Swerve_Type_Constants SDS_MK4i_L1() {
        double WHEEL_DIAMETER = Units.inchesToMeters(4.0);

        double ANGLE_GEAR_RATIO = SWERVE_MK4I_ANGLE_GEAR_RATIO;
        double DRIVE_GEAR_RATIO = SWERVE_MK4I_DRIVE_GEAR_RATIO_L1;

        double ANGLE_PID[] = {0.0, 0.0, 0.0, 0.0};

        boolean DRIVE_MOTOR_INVERT = false;
        boolean ANGLE_MOTOR_INVERT = true;
        boolean CANCODER_MOTOR_INVERT = false;

        return new Swerve_Type_Constants(
                WHEEL_DIAMETER,
                ANGLE_GEAR_RATIO,
                DRIVE_GEAR_RATIO,
                ANGLE_PID,
                DRIVE_MOTOR_INVERT,
                ANGLE_MOTOR_INVERT,
                CANCODER_MOTOR_INVERT);

    }

    @SuppressWarnings("Removal")
    private static final double SWERVE_MK4I_ANGLE_GEAR_RATIO = 150.0/7.0;
    private static final double SWERVE_MK4I_DRIVE_GEAR_RATIO_L1 = 8.14;
}
