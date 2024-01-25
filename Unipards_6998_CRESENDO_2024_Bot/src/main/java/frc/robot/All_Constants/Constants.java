package frc.robot.All_Constants;

import edu.wpi.first.math.geometry.Rotation2d;

public class Constants {

    public static boolean NAVX_INVERTED = true;
    public final static double MAX_VOLTAGE = 12.0;

    public final static Rotation2d FRONT_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d BACK_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d BACK_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    public final static Rotation2d FRONT_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(0.0);
    

    
    public final static double SWERVE_DRIVE_KS = 0.0;
    public final static double SWERVE_DRIVE_KV = 0.0;
    public final static double SWERVE_DRIVE_KA = 0.0;
    public final static double SWERVE_VOLTAGE_COMPENSATION = 12.0;
    public final static int SWERVE_CURRENT_LIMIT = 30;


    public final static double[] CHASSIS_PID = {0,0,0,0};
    public final static double[] INTAKE_PID = {0,0,0,0};
    public final static double[] SHOOTER_PID = {0,0,0,0};

    
}
