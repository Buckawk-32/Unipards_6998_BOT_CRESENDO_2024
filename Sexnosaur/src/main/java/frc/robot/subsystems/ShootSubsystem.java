package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.*;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;
import frc.robot.Conversions;

public class ShootSubsystem extends SubsystemBase {
    private final static ShootSubsystem INSTANCE = new ShootSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static ShootSubsystem getInstance() {
        return INSTANCE;
    }

    private CANSparkMax SHOOT_ANGLE_MOTOR;
    private RelativeEncoder SHOOT_ANGLE_MOTOR_ENCODER;

    private TalonFX SHOOT_DRIVE_MOTOR_1;
    private TalonFX SHOOT_DRIVE_MOTOR_2;

    private VelocityVoltage SHOOT_DRIVE_VELOCITY_VOLTAGE = new VelocityVoltage(0);

    public ShootSubsystem() {
        SHOOT_ANGLE_MOTOR = new CANSparkMax(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        SHOOT_ANGLE_MOTOR_ENCODER = SHOOT_ANGLE_MOTOR.getEncoder();

        SHOOT_DRIVE_MOTOR_1 = new TalonFX(Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_MOTOR_1_ID);
        SHOOT_DRIVE_MOTOR_2 = new TalonFX(Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_MOTOR_2_ID);
        SHOOT_DRIVE_MOTOR_1.getConfigurator().setPosition(0);
        SHOOT_DRIVE_MOTOR_2.getConfigurator().setPosition(0);

        config_shooter();
    }

    public void setShooterAngle(double POSITION) {
        SHOOT_ANGLE_MOTOR.getPIDController().setReference(POSITION, CANSparkBase.ControlType.kPosition);
    }

    public void setShooterVelocity(double RPM) {
        SHOOT_DRIVE_VELOCITY_VOLTAGE.Velocity = Conversions.RPM_to_FALCON_TICKS(RPM, Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_GEAR_RATIO);
        SHOOT_DRIVE_MOTOR_1.setControl(SHOOT_DRIVE_VELOCITY_VOLTAGE);
    }

    public void zeroShooterVelocity() {
        SHOOT_DRIVE_MOTOR_1.setControl(SHOOT_DRIVE_VELOCITY_VOLTAGE.withVelocity(0));
    }

    public void stopAllMotors() {
        SHOOT_ANGLE_MOTOR.stopMotor();
        zeroShooterVelocity();
    }

    public void velo() {
        setShooterVelocity(Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_RPM);
    }

    private void config_shooter() {
        SHOOT_ANGLE_MOTOR.restoreFactoryDefaults();

        SHOOT_ANGLE_MOTOR.getPIDController().setP(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_KP);
        SHOOT_ANGLE_MOTOR.getPIDController().setI(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_KI);
        SHOOT_ANGLE_MOTOR.getPIDController().setD(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_KD);
        SHOOT_ANGLE_MOTOR.getPIDController().setFF(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_KF);

        SHOOT_ANGLE_MOTOR.setIdleMode(CANSparkBase.IdleMode.kBrake);
        SHOOT_ANGLE_MOTOR.setSmartCurrentLimit(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_CURRENT_LIMIT);

        SHOOT_ANGLE_MOTOR.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_MAXIMUM_ANGLE);
        SHOOT_ANGLE_MOTOR.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_MINIMUM_ANGLE);

        SHOOT_ANGLE_MOTOR_ENCODER.setPositionConversionFactor(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_GEAR_RATIO);
        SHOOT_ANGLE_MOTOR_ENCODER.setVelocityConversionFactor(Mechanism_Constants.SHOOTER_ANGLE_CONSTANTS.SHOOT_ANGLE_GEAR_RATIO / 60);

        SHOOT_ANGLE_MOTOR.burnFlash();

        TalonFXConfiguration SHOOT_DRIVE_MOTOR_CONFIG = new TalonFXConfiguration();

        SHOOT_DRIVE_MOTOR_CONFIG.Slot0.kP = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_KP;
        SHOOT_DRIVE_MOTOR_CONFIG.Slot0.kI = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_KI;
        SHOOT_DRIVE_MOTOR_CONFIG.Slot0.kD = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_KD;

        SHOOT_DRIVE_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimit = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_CONTINOUS_CURRENT_LIMIT;
        SHOOT_DRIVE_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimitEnable = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_CURRENT_LIMIT_ENABLE;
        SHOOT_DRIVE_MOTOR_CONFIG.CurrentLimits.SupplyCurrentThreshold = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_PEAK_CURRENT_LIMIT;
        SHOOT_DRIVE_MOTOR_CONFIG.CurrentLimits.SupplyTimeThreshold = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_PEAK_CURRENT_DURATION;

        SHOOT_DRIVE_MOTOR_CONFIG.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        SHOOT_DRIVE_MOTOR_CONFIG.Feedback.SensorToMechanismRatio = Mechanism_Constants.SHOOTER_DRIVE_CONSTANTS.SHOOTER_DRIVE_GEAR_RATIO;

        SHOOT_DRIVE_MOTOR_1.getConfigurator().apply(SHOOT_DRIVE_MOTOR_CONFIG);
        SHOOT_DRIVE_MOTOR_2.getConfigurator().apply(SHOOT_DRIVE_MOTOR_CONFIG);

        SHOOT_DRIVE_MOTOR_2.setControl(new Follower(SHOOT_DRIVE_MOTOR_1.getDeviceID(), true));

    }
}

