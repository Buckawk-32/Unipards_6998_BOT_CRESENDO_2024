package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class AngleOptimize {

    public static SwerveModuleState optimize (SwerveModuleState DESIRED_STATE, Rotation2d CURRENT_ANGLE){
        double TARGET_ANGLE = place_in_appropriate_360(CURRENT_ANGLE.getDegrees(), DESIRED_STATE.angle.getDegrees());
        double TARGET_SPEED = DESIRED_STATE.speedMetersPerSecond;
        double DELTA = TARGET_ANGLE -CURRENT_ANGLE.getDegrees();

        if (Math.abs(DELTA) > 90) {
            TARGET_SPEED = - TARGET_SPEED;
            TARGET_ANGLE = DELTA > 90 ? (TARGET_ANGLE -= 180) : (TARGET_ANGLE += 180);
        }
        return new SwerveModuleState(TARGET_SPEED, Rotation2d.fromDegrees(TARGET_ANGLE));
    }

    public static double place_in_appropriate_360 (double SCOPE_REFERENCE, double NEW_ANGLE) {
        double LOWER_BOUND;
        double UPPER_BOUND;
        double LOWER_OFFSET = SCOPE_REFERENCE % 360;
        if (LOWER_OFFSET >= 0) {
            LOWER_BOUND = SCOPE_REFERENCE - LOWER_OFFSET;
            UPPER_BOUND = SCOPE_REFERENCE + (360 - LOWER_OFFSET);
        } else {
            UPPER_BOUND = SCOPE_REFERENCE - LOWER_OFFSET;
            LOWER_BOUND = SCOPE_REFERENCE - (360 + LOWER_OFFSET);
        }
        while (NEW_ANGLE < LOWER_BOUND) {
            NEW_ANGLE += 360;
        }
        while (NEW_ANGLE > UPPER_BOUND) {
            NEW_ANGLE -= 360;
        }
        if (NEW_ANGLE - SCOPE_REFERENCE > 180) {
            NEW_ANGLE -= 360;
        } else if (NEW_ANGLE - SCOPE_REFERENCE < -180) {
            NEW_ANGLE += 360;
        }
        return NEW_ANGLE;
    }
}
