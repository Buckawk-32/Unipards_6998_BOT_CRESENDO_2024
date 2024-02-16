package frc.robot.subsystems;


import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;

public class CollectSubsystem extends SubsystemBase {
    private final static CollectSubsystem INSTANCE = new CollectSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static CollectSubsystem getInstance() {
        return INSTANCE;
    }

    private CANSparkMax INTAKE_MOTOR_1;
    private CANSparkMax INTAKE_MOTOR_2;
    private RelativeEncoder INTAKE_MOTOR_1_ENCODER;
    private RelativeEncoder INTAKE_MOTOR_2_ENCODER;
    public boolean INTAKE_ENABLED;

    private CANSparkMax SLIDER_MOTOR;
    private RelativeEncoder SLIDER_MOTOR_ENCODER;


    public CollectSubsystem() {
        INTAKE_MOTOR_1 = new CANSparkMax(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_1_ID, CANSparkLowLevel.MotorType.kBrushless);
        INTAKE_MOTOR_2 = new CANSparkMax(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_2_ID, CANSparkLowLevel.MotorType.kBrushless);

        INTAKE_MOTOR_1_ENCODER = INTAKE_MOTOR_1.getEncoder();
        INTAKE_MOTOR_2_ENCODER = INTAKE_MOTOR_2.getEncoder();
        config_intake();

        SLIDER_MOTOR = new CANSparkMax(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

        SLIDER_MOTOR_ENCODER = SLIDER_MOTOR.getEncoder();
        config_slider();
    }

    public void setIntaketoRPM() {
        INTAKE_MOTOR_1.getPIDController().setReference(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_RPM, CANSparkMax.ControlType.kVelocity);
    }

    public void stopIntake() {
        INTAKE_MOTOR_1.set(0);
        INTAKE_ENABLED = false;
    }

    private void config_intake() {
        INTAKE_MOTOR_1.restoreFactoryDefaults();

        INTAKE_MOTOR_1.getPIDController().setP(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KP, 0);
        INTAKE_MOTOR_1.getPIDController().setI(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KI, 0);
        INTAKE_MOTOR_1.getPIDController().setD(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KD, 0);
        INTAKE_MOTOR_1.getPIDController().setI(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KI, 0);
        INTAKE_MOTOR_1.getPIDController().setFF(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KF, 0);

        INTAKE_MOTOR_1.setIdleMode(CANSparkBase.IdleMode.kCoast);
        INTAKE_MOTOR_1.setSmartCurrentLimit(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_CURRENT_LIMIT);

        INTAKE_MOTOR_1_ENCODER.setPositionConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO);
        INTAKE_MOTOR_1_ENCODER.setVelocityConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO / 60);

        INTAKE_MOTOR_1.burnFlash();

        INTAKE_MOTOR_2.restoreFactoryDefaults();

        INTAKE_MOTOR_2.getPIDController().setP(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KP, 0);
        INTAKE_MOTOR_2.getPIDController().setI(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KI, 0);
        INTAKE_MOTOR_2.getPIDController().setD(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KD, 0);
        INTAKE_MOTOR_2.getPIDController().setI(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KI, 0);
        INTAKE_MOTOR_2.getPIDController().setFF(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KF, 0);

        INTAKE_MOTOR_2.setIdleMode(CANSparkBase.IdleMode.kCoast);
        INTAKE_MOTOR_2.setSmartCurrentLimit(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_CURRENT_LIMIT);

        INTAKE_MOTOR_2_ENCODER.setPositionConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO);
        INTAKE_MOTOR_2_ENCODER.setVelocityConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO / 60);

        INTAKE_MOTOR_2.burnFlash();

        INTAKE_MOTOR_2.follow(INTAKE_MOTOR_1);
    }

    public void config_slider() {
        SLIDER_MOTOR.restoreFactoryDefaults();

        SLIDER_MOTOR.getPIDController().setP(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_KP, 0);
        SLIDER_MOTOR.getPIDController().setI(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_KI, 0);
        SLIDER_MOTOR.getPIDController().setD(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_KD, 0);
        SLIDER_MOTOR.getPIDController().setFF(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_KF, 0);

        SLIDER_MOTOR.setIdleMode(CANSparkBase.IdleMode.kBrake);
        SLIDER_MOTOR.setSmartCurrentLimit(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_CURRENT_LIMIT);
        SLIDER_MOTOR.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_MAXIMUM_LENGTH);
        SLIDER_MOTOR.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_MINIMUM_LENGTH);

        SLIDER_MOTOR_ENCODER.setPositionConversionFactor(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_GEAR_RATIO);
        SLIDER_MOTOR_ENCODER.setVelocityConversionFactor(Mechanism_Constants.SLIDER_CONSTANTS.SLIDER_GEAR_RATIO / 60);

        SLIDER_MOTOR.burnFlash();
    }
}

