package frc.robot;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import com.revrobotics.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.All_Constants.Swerve.Swerve_Motion_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Type_Constants;

public class SwerveModule {

    public int module_num;
    private Swerve_Type_Constants swerveTypeConstants;

    private TalonFX DRIVE_MOTOR;
    private CANSparkMax ANGLE_MOTOR;
    private RelativeEncoder ANGLE_MOTOR_ENCODER;
    private CANcoder CANCODER;
    private Rotation2d ANGLE_OFFSET;
    private Rotation2d LAST_ANGLE;

    private final DutyCycleOut DRIVE_DUTY_CYCLE = new DutyCycleOut(0);
    private final VelocityVoltage DRIVE_VELOCITY_VOLTAGE = new VelocityVoltage(0);
    private final SimpleMotorFeedforward DRIVE_FEEDFORWARD = new SimpleMotorFeedforward(Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KS, Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KV, Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KA);

    public SwerveModule(int module_num, Swerve_Type_Constants swerveTypeConstants, int D_MOTOR_ID, int A_MOTOR_ID, int CANCODER_ID, Rotation2d ANGLE_OFFSET) {
        this.swerveTypeConstants = swerveTypeConstants;
        this.module_num = module_num;
        this.ANGLE_OFFSET = ANGLE_OFFSET;

        CANCODER = new CANcoder(CANCODER_ID);
        config_cancoder();

        DRIVE_MOTOR = new TalonFX(D_MOTOR_ID);
        config_drive_motor();
        DRIVE_MOTOR.getConfigurator().setPosition(0.0);

        ANGLE_MOTOR = new CANSparkMax(A_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        ANGLE_MOTOR_ENCODER = ANGLE_MOTOR.getEncoder();
        config_angle_motor();
        reset_to_Absolute_0();
    }

    public void setDesiredState(SwerveModuleState DESIRED_STATE, boolean IS_OPENLOOP) {
        DESIRED_STATE = AngleOptimize.optimize(DESIRED_STATE, getState().angle);
        setSpeed(DESIRED_STATE, IS_OPENLOOP);
        setAngle(DESIRED_STATE);
    }

    private void setSpeed(SwerveModuleState DESIRED_STATE, boolean IS_OPENLOOP) {
        if (IS_OPENLOOP) {
            DRIVE_DUTY_CYCLE.Output = DESIRED_STATE.speedMetersPerSecond / Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED;
            DRIVE_MOTOR.setControl(DRIVE_DUTY_CYCLE);
        }
        else {
            DRIVE_VELOCITY_VOLTAGE.Velocity = Conversions.MPS_to_FALCON_TICKS(DESIRED_STATE.speedMetersPerSecond, swerveTypeConstants.WHEEL_CIRCUMFERENCE, swerveTypeConstants.DRIVE_GEAR_RATIO);
            DRIVE_VELOCITY_VOLTAGE.FeedForward = DRIVE_FEEDFORWARD.calculate(DESIRED_STATE.speedMetersPerSecond);
            DRIVE_MOTOR.setControl(DRIVE_VELOCITY_VOLTAGE);
        }
    }

    private void setAngle(SwerveModuleState DESIRED_STATE) {
        Rotation2d ANGLE = Math.abs(DESIRED_STATE.speedMetersPerSecond) >= 0.01 * Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED ? LAST_ANGLE : DESIRED_STATE.angle;

        ANGLE_MOTOR.getPIDController().setReference(ANGLE.getDegrees(), CANSparkBase.ControlType.kPosition);
        LAST_ANGLE = ANGLE;
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
                Conversions.FALCON_TICKS_to_METERS(DRIVE_MOTOR.getPosition().getValue(), swerveTypeConstants.WHEEL_CIRCUMFERENCE, swerveTypeConstants.DRIVE_GEAR_RATIO),
                Rotation2d.fromDegrees(ANGLE_MOTOR_ENCODER.getPosition())
        );
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
                Conversions.FALCON_TICKS_to_MPS(DRIVE_MOTOR.getPosition().getValue(), swerveTypeConstants.WHEEL_CIRCUMFERENCE, swerveTypeConstants.DRIVE_GEAR_RATIO),
                Rotation2d.fromDegrees(ANGLE_MOTOR_ENCODER.getPosition())
        );
    }


    public Rotation2d getCANcoder() {
        return Rotation2d.fromDegrees(CANCODER.getAbsolutePosition().getValue());
    }

    public void reset_to_Absolute_0() {
        double ABSOLUTE_ANGLE = (getCANcoder().getDegrees() - ANGLE_OFFSET.getDegrees());
        ANGLE_MOTOR_ENCODER.setPosition(ABSOLUTE_ANGLE);
    }

    private void config_cancoder() {
        CANcoderConfiguration CAN_CODER_CONFIG = new CANcoderConfiguration();
        CAN_CODER_CONFIG.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        CAN_CODER_CONFIG.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;

        CANCODER.getConfigurator().apply(CAN_CODER_CONFIG);
    }

    private void config_angle_motor() {
        ANGLE_MOTOR.restoreFactoryDefaults();

        ANGLE_MOTOR.getPIDController().setP(swerveTypeConstants.ANGLE_KP);
        ANGLE_MOTOR.getPIDController().setI(swerveTypeConstants.ANGLE_KI);
        ANGLE_MOTOR.getPIDController().setD(swerveTypeConstants.ANGLE_KD);
        ANGLE_MOTOR.getPIDController().setFF(swerveTypeConstants.ANGLE_KF);

        ANGLE_MOTOR.setInverted(swerveTypeConstants.ANGLE_MOTOR_INVERT);
        ANGLE_MOTOR.setIdleMode(Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_NEUTRAL_MODE);

        ANGLE_MOTOR.setSmartCurrentLimit(Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_CONTINUOUS_CURRENT_LIMIT);
        ANGLE_MOTOR.enableVoltageCompensation(Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.MAX_VOLTAGE);

        ANGLE_MOTOR.getEncoder().setPositionConversionFactor(360 / swerveTypeConstants.ANGLE_GEAR_RATIO);

        ANGLE_MOTOR.burnFlash();
    }

    private void config_drive_motor() {
        TalonFXConfiguration D_MOTOR_CONFIG = new TalonFXConfiguration();

        D_MOTOR_CONFIG.Slot0.kP = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KP;
        D_MOTOR_CONFIG.Slot0.kI = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KI;
        D_MOTOR_CONFIG.Slot0.kD = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KD;
        D_MOTOR_CONFIG.Slot0.kV = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KV;
        D_MOTOR_CONFIG.Slot0.kA = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KA;
        D_MOTOR_CONFIG.Slot0.kS = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KS;

        D_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimitEnable = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_CURRENT_LIMIT_ENABLE;
        D_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimit = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_CONTINUOUS_CURRENT_LIMIT;
        D_MOTOR_CONFIG.CurrentLimits.SupplyCurrentThreshold = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_PEAK_CURRENT_LIMIT;
        D_MOTOR_CONFIG.CurrentLimits.SupplyTimeThreshold = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_PEAK_CURRENT_DURATION;

        D_MOTOR_CONFIG.Feedback.SensorToMechanismRatio = swerveTypeConstants.DRIVE_GEAR_RATIO;

        D_MOTOR_CONFIG.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_MOTOR_OPENLOOPRAMP;
        D_MOTOR_CONFIG.OpenLoopRamps.VoltageOpenLoopRampPeriod = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_MOTOR_OPENLOOPRAMP;

        D_MOTOR_CONFIG.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP;
        D_MOTOR_CONFIG.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP;

        D_MOTOR_CONFIG.MotorOutput.NeutralMode = Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.DRIVE_NEUTRAL_MODE;
        D_MOTOR_CONFIG.MotorOutput.Inverted = swerveTypeConstants.DRIVE_MOTOR_INVERT;

        DRIVE_MOTOR.getConfigurator().apply(D_MOTOR_CONFIG);
    }
}
