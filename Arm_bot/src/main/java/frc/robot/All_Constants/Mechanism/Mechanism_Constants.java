package frc.robot.All_Constants.Mechanism;

import com.revrobotics.CANSparkBase;

public class Mechanism_Constants {

    public static class INTAKE_CONSTANTS {
        public static final int INTAKE_MOTOR_ID = 4;

        public final static double INTAKE_KP = 0.0;
        public final static double INTAKE_KI = 0.0;
        public final static double INTAKE_KD = 0.0;
        public final static double INTAKE_KF = 0.0;

        public final static boolean INTAKE_INVERTED = false;

        public final static CANSparkBase.IdleMode INTAKE_IDLE_MODE = CANSparkBase.IdleMode.kCoast;

        public final static int INTAKE_CURRENT_LIMIT = 35;

        public final static double INTAKE_GEAR_RATIO = (30/1);

        public final static double INTAKE_RPM = 300;
    }

    public static class SHOOTER_CONSTANTS {
        public static final int SHOOTER_MOTOR_ID = 3;

        public static final double SHOOTER_KP = 0.0;
        public static final double SHOOTER_KI = 0.0;
        public static final double SHOOTER_KD = 0.0;
        public static final double SHOOTER_KF = 0.0;

        public static final int SHOOTER_CURRENT_LIMIT = 35;
        public static final double SHOOTER_GEAR_RATIO = 0.0;

        public static final double SHOOTER_RPM = 800;
    }

    public static class ARM_CONSTANTS {
        public static final int ARM_MOTOR_1_ID = 1;
        public static final int ARM_MOTOR_2_ID = 2;

        public static final double ARM_KP = 0.02;
        public static final double ARM_KI = 0.0;
        public static final double ARM_KD = 0.0;
        public static final double ARM_KF = 0.0;

        public static final int ARM_MOTOR_LIMIT = 35;
        public static final double ARM_GEAR_RATIO = (100/1);

        public static final double INTAKE_ARM_ANGLE = 4.0;
        public static final double AMP_ARM_ANGLE = 58.0;
        public static final double SPEAKER_ARM_ANGLE = 42.0;
    }

}
