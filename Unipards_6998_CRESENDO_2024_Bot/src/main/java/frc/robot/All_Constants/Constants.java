package frc.robot.All_Constants;

import edu.wpi.first.math.geometry.Rotation2d;

public class Constants {

    public static boolean NAVX_INVERTED = true;
    public static byte NAVX_UPDATE_RATE = 127;
    public final static double MAX_VOLTAGE = 12.0;

    // to be tuned by offset after 1/31
    public final static Rotation2d FRONT_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d BACK_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d BACK_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d FRONT_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);


    
    public final static double SWERVE_DRIVE_KS = 0.0;
    public final static double SWERVE_DRIVE_KV = 0.0;
    public final static double SWERVE_DRIVE_KA = 0.0;
    public final static double SWERVE_VOLTAGE_COMPENSATION = 12.0;
    public final static int SWERVE_CURRENT_LIMIT = 30;
    public final static double SWERVE_MAX_VELOCITY = 0.0;
    public final static double SWERVE_MAX_ACCELERATION = 0.0;

    // Current Limit
    public final static int INTAKE_CURRENT_LIMIT = 40;
    public final static int SHOOTER_CURRENT_LIMIT = 40;
    public final static int HANG_CURRENT_LIMIT = 40;
    public final static int ARM_CURRENT_LIMIT = 40;


    // ToDo : PID tuning after 1/31
    public final static double[] CHASSIS_PID = {0,0,0,0};
    public final static double[] INTAKE_PID = {0.0005,0,0,0};
    public final static double[] SHOOTER_PID = {0.0008,0,0,0};
    public final static double[] HANG_PID = {0.0008,0,0,0};
    public final static double[] ARM_PID = {0.0008,0,0,0};

    // Gear Ratios:
    public final static double armGearRatio = 200.0;
    public final static double shooterGearRatio = 1.0;
    public final static double intakeGearRatio = 30.0;

   

    
}
