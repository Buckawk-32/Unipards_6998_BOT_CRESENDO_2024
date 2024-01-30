package frc.lib.util;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleConstants {
    public final int DRIVE_MOTOR_ID;
    public final int ANGLE_MOTOR_ID;
    public final int CANCODER_MOTOR_ID;
    public final Rotation2d ANGLE_OFFSET;


    /**
     * Swerve Module Constants to be used in the SwerveModule class
     * @param DRIVE_MOTOR_ID
     * @param ANGLE_MOTOR_ID
     * @param CANCODER_MOTOR_ID
     * @param ANGLE_OFFSET
     */
    public SwerveModuleConstants(int DRIVE_MOTOR_ID, int ANGLE_MOTOR_ID, int CANCODER_MOTOR_ID, Rotation2d ANGLE_OFFSET){
        this.DRIVE_MOTOR_ID = DRIVE_MOTOR_ID;
        this.ANGLE_MOTOR_ID = ANGLE_MOTOR_ID;
        this.CANCODER_MOTOR_ID = CANCODER_MOTOR_ID;
        this.ANGLE_OFFSET = ANGLE_OFFSET;
    }
}
