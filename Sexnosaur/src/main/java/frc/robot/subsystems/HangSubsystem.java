package frc.robot.subsystems;


import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;

public class HangSubsystem extends SubsystemBase {

    private final static HangSubsystem INSTANCE = new HangSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static HangSubsystem getInstance() {
        return INSTANCE;
    }

    private CANSparkMax HANG_MOTOR_1;
    private CANSparkMax HANG_MOTOR_2;

    private RelativeEncoder HANG_MOTOR_1_ENCODER;
    private RelativeEncoder HANG_MOTOR_2_ENCODER;

    public HangSubsystem() {

        HANG_MOTOR_1 = new CANSparkMax(Mechanism_Constants.HANGER_CONSTANTS.HANG_MOTOR_1_ID, CANSparkLowLevel.MotorType.kBrushless);
        HANG_MOTOR_2 = new CANSparkMax(Mechanism_Constants.HANGER_CONSTANTS.HANG_MOTOR_2_ID, CANSparkLowLevel.MotorType.kBrushless);

        HANG_MOTOR_1_ENCODER = HANG_MOTOR_1.getEncoder();
        HANG_MOTOR_2_ENCODER = HANG_MOTOR_2.getEncoder();
        configHanger();
    }

    public void setHangerPosition(double POSITION) {
        HANG_MOTOR_1.getPIDController().setReference(POSITION, CANSparkBase.ControlType.kPosition);
    }

    private void configHanger() {
        HANG_MOTOR_1.restoreFactoryDefaults();

        HANG_MOTOR_1.getPIDController().setP(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KP);
        HANG_MOTOR_1.getPIDController().setI(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KI);
        HANG_MOTOR_1.getPIDController().setD(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KD);

        HANG_MOTOR_1.setIdleMode(CANSparkBase.IdleMode.kCoast);

        HANG_MOTOR_1.setSmartCurrentLimit(Mechanism_Constants.HANGER_CONSTANTS.HANGER_CURRENT_LIMIT);
        HANG_MOTOR_1.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, Mechanism_Constants.HANGER_CONSTANTS.HANGER_MAXIMUM_HEIGHT);
        HANG_MOTOR_1.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, Mechanism_Constants.HANGER_CONSTANTS.HANGER_MINIMUM_HEIGHT);

        HANG_MOTOR_1_ENCODER.setPositionConversionFactor(Mechanism_Constants.HANGER_CONSTANTS.HANGER_GEAR_RATIO);
        HANG_MOTOR_1_ENCODER.setVelocityConversionFactor(Mechanism_Constants.HANGER_CONSTANTS.HANGER_GEAR_RATIO / 60);

        HANG_MOTOR_2.restoreFactoryDefaults();

        HANG_MOTOR_2.getPIDController().setP(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KP);
        HANG_MOTOR_2.getPIDController().setI(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KI);
        HANG_MOTOR_2.getPIDController().setD(Mechanism_Constants.HANGER_CONSTANTS.HANGER_KD);

        HANG_MOTOR_2.setIdleMode(CANSparkBase.IdleMode.kCoast);

        HANG_MOTOR_2.setSmartCurrentLimit(Mechanism_Constants.HANGER_CONSTANTS.HANGER_CURRENT_LIMIT);
        HANG_MOTOR_2.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, Mechanism_Constants.HANGER_CONSTANTS.HANGER_MAXIMUM_HEIGHT);
        HANG_MOTOR_2.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, Mechanism_Constants.HANGER_CONSTANTS.HANGER_MINIMUM_HEIGHT);

        HANG_MOTOR_2_ENCODER.setPositionConversionFactor(Mechanism_Constants.HANGER_CONSTANTS.HANGER_GEAR_RATIO);
        HANG_MOTOR_2_ENCODER.setVelocityConversionFactor(Mechanism_Constants.HANGER_CONSTANTS.HANGER_GEAR_RATIO / 60);

        HANG_MOTOR_2.follow(HANG_MOTOR_1);


    }
}

