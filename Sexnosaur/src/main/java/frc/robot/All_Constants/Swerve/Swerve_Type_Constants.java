package frc.robot.All_Constants.Swerve;

import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.util.Units;

public class Swerve_Type_Constants {

    public double WHEEL_DIAMETER;
    public double WHEEL_CIRCUMFERENCE;
    public double ANGLE_GEAR_RATIO;
    public double DRIVE_GEAR_RATIO;
    public double ANGLE_KP;
    public double ANGLE_KI;
    public double ANGLE_KD;
    public double ANGLE_KF;
    public InvertedValue DRIVE_MOTOR_INVERT;
    public boolean ANGLE_MOTOR_INVERT;
    public InvertedValue CANCODER_INVERT;


    public Swerve_Type_Constants(
            double WHEEL_DIAMETER,
            double ANGLE_GEAR_RATIO,
            double DRIVE_GEAR_RATIO,
            double ANGLE_KP,
            double ANGLE_KI,
            double ANGLE_KD,
            double ANGLE_KF,
            InvertedValue DRIVE_MOTOR_INVERT,
            boolean ANGLE_MOTOR_INVERT,
            InvertedValue CANCODER_INVERT
    ) {
        this.WHEEL_DIAMETER = WHEEL_DIAMETER;
        this.WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
        this.ANGLE_GEAR_RATIO = ANGLE_GEAR_RATIO;
        this.DRIVE_GEAR_RATIO = DRIVE_GEAR_RATIO;
        this.ANGLE_KP = ANGLE_KP;
        this.ANGLE_KI = ANGLE_KI;
        this.ANGLE_KD = ANGLE_KD;
        this.ANGLE_KF = ANGLE_KF;
        this.DRIVE_MOTOR_INVERT = DRIVE_MOTOR_INVERT;
        this.ANGLE_MOTOR_INVERT = ANGLE_MOTOR_INVERT;
        this.CANCODER_INVERT = CANCODER_INVERT;
    }

    // TODO: Need to add SDS other types
    public static Swerve_Type_Constants SDS_MK4i_L1() {
        double WHEEL_DIAMETER = Units.inchesToMeters(4.0);

        double ANGLE_GEAR_RATIO = SWERVE_MK4I_ANGLE_GEAR_RATIO;
        double DRIVE_GEAR_RATIO = SWERVE_MK4I_DRIVE_GEAR_RATIO_L1;

        double ANGLE_KP = 0.2;
        double ANGLE_KI = 0.0;
        double ANGLE_KD = 0.0;
        double ANGLE_KF = 0.0;

        InvertedValue DRIVE_MOTOR_INVERT = InvertedValue.Clockwise_Positive;
        boolean ANGLE_MOTOR_INVERT = true;
        InvertedValue CANCODER_MOTOR_INVERT = InvertedValue.Clockwise_Positive;

        return new Swerve_Type_Constants(
                WHEEL_DIAMETER,
                ANGLE_GEAR_RATIO,
                DRIVE_GEAR_RATIO,
                ANGLE_KP,
                ANGLE_KI,
                ANGLE_KD,
                ANGLE_KF,
                DRIVE_MOTOR_INVERT,
                ANGLE_MOTOR_INVERT,
                CANCODER_MOTOR_INVERT);

    }

    @SuppressWarnings("Removal")
    private static final double SWERVE_MK4I_ANGLE_GEAR_RATIO = ((150.0 / 7.0) / 1.0);
    private static final double SWERVE_MK4I_DRIVE_GEAR_RATIO_L1 = (8.14 / 1.0);
}
