package frc.robot.All_Constants.Swerve;

import edu.wpi.first.math.geometry.Rotation2d;

public class Swerve_Module_Constants {

    public static class FRONT_LEFT {
        public final static int FRONT_LEFT_DRIVE_MOTOR_ID = 1;
        public final static int FRONT_LEFT_ANGLE_MOTOR_ID = 2;
        public final static int FRONT_LEFT_CANCODER_MOTOR_ID = 1;
        public final static Rotation2d FRONT_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(68.994141);
    }

    public static class BACK_LEFT {
        public final static int BACK_LEFT_DRIVE_MOTOR_ID = 3;
        public final static int BACK_LEFT_ANGLE_MOTOR_ID = 4;
        public final static int BACK_LEFT_CANCODER_MOTOR_ID = 2;
        public final static Rotation2d BACK_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(66.708984);
    }

    public static class BACK_RIGHT {
        public final static int BACK_RIGHT_DRIVE_MOTOR_ID = 5;
        public final static int BACK_RIGHT_ANGLE_MOTOR_ID = 6;
        public final static int BACK_RIGHT_CANCODER_MOTOR_ID = 3;
        public final static Rotation2d BACK_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(200.126);
    }

    public static class FRONT_RIGHT {
        public final static int FRONT_RIGHT_DRIVE_MOTOR_ID = 7;
        public final static int FRONT_RIGHT_ANGLE_MOTOR_ID = 8;
        public final static int FRONT_RIGHT_CANCODER_MOTOR_ID = 4;
        public final static Rotation2d FRONT_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(151.083984);

    }
}
