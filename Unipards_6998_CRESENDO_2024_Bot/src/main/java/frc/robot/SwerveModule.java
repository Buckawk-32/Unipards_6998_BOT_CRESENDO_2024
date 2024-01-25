package frc.robot;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.All_Constants.Swerve.Swerve_Motion_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Type_Constants;

public class SwerveModule{

    public int module_num;
    private Swerve_Type_Constants swerveTypeConstants;

    private TalonFX DRIVE_MOTOR;
    private TalonFX ANGLE_MOTOR;
    private CANcoder CANCODER;
    private Rotation2d ANGLE_OFFSET;
    private Rotation2d LAST_ANGLE;

    private final DutyCycleOut DRIVE_DUTY_CYCLE = new DutyCycleOut(0);
    private final VelocityVoltage DRIVE_VELOCITY_VOLTAGE = new VelocityVoltage(0);

    private final PositionDutyCycle ANGLE_POSITION = new PositionDutyCycle(0);

    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KS, Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KV, Swerve_Motion_Constants.SWERVE_DRIVE_MOTOR_CONSTANTS.SWERVE_DRIVE_KA);

    public SwerveModule(int module_num, Swerve_Type_Constants swerveTypeConstants, int D_MOTOR_ID, int A_MOTOR_ID, int CANCODER_ID, Rotation2d ANGLE_OFFSET) {
        this.swerveTypeConstants = swerveTypeConstants;
        this.module_num = module_num;
        this.ANGLE_OFFSET = ANGLE_OFFSET;

        DRIVE_MOTOR = new TalonFX(D_MOTOR_ID);
        config_drive_motor();
        DRIVE_MOTOR.getConfigurator().setPosition(0.0);

        ANGLE_MOTOR = new TalonFX(A_MOTOR_ID);
        config_angle_motor();
        reset_to_Absolute_0();

        CANCODER = new CANcoder(CANCODER_ID);
        config_cancoder();


    }

    public void setDesiredState(SwerveModuleState DESIRED_STATE, boolean IS_OPENLOOP) {
        DESIRED_STATE = SwerveModuleState.optimize(DESIRED_STATE, getState().angle);
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
            DRIVE_VELOCITY_VOLTAGE.FeedForward = feedforward.calculate(DESIRED_STATE.speedMetersPerSecond);
            DRIVE_MOTOR.setControl(DRIVE_VELOCITY_VOLTAGE);
        }
    }

    private void setAngle(SwerveModuleState DESIRED_STATE) {
        ANGLE_MOTOR.setControl(ANGLE_POSITION.withPosition(DESIRED_STATE.angle.getDegrees()).withSlot(0));
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
                Conversions.FALCON_TICKS_to_METERS(DRIVE_MOTOR.getPosition().getValue(), swerveTypeConstants.WHEEL_CIRCUMFERENCE, swerveTypeConstants.DRIVE_GEAR_RATIO),
                Rotation2d.fromDegrees(ANGLE_MOTOR.getPosition().getValue())
        );
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
                Conversions.FALCON_TICKS_to_MPS(DRIVE_MOTOR.getPosition().getValue(), swerveTypeConstants.WHEEL_CIRCUMFERENCE, swerveTypeConstants.DRIVE_GEAR_RATIO),
                Rotation2d.fromDegrees(ANGLE_MOTOR.getPosition().getValue())
        );
    }


    public Rotation2d getCANcoder() {
        return Rotation2d.fromDegrees(CANCODER.getPosition().getValue());
    }

    public void reset_to_Absolute_0() {
        double ABSOLUTE_POSITION = (getCANcoder().getDegrees() - ANGLE_OFFSET.getDegrees());
        ANGLE_MOTOR.setPosition(ABSOLUTE_POSITION);
    }

    private void config_cancoder() {
        CANcoderConfiguration CAN_CODER_CONFIG = new CANcoderConfiguration();
        CAN_CODER_CONFIG.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;

        CANCODER.getConfigurator().apply(CAN_CODER_CONFIG);
    }

    private void config_angle_motor() {
        TalonFXConfiguration A_MOTOR_CONFIG = new TalonFXConfiguration();

        A_MOTOR_CONFIG.Slot0.kP = swerveTypeConstants.ANGLE_KP;
        A_MOTOR_CONFIG.Slot0.kI = swerveTypeConstants.ANGLE_KI;
        A_MOTOR_CONFIG.Slot0.kD = swerveTypeConstants.ANGLE_KD;

        A_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimitEnable = Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_ENABLE_CURRENT_LIMIT;
        A_MOTOR_CONFIG.CurrentLimits.SupplyCurrentLimit = Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_CONTINUOUS_CURRENT_LIMIT;
        A_MOTOR_CONFIG.CurrentLimits.SupplyCurrentThreshold = Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_CURRENT_THRESHOLD;
        A_MOTOR_CONFIG.CurrentLimits.SupplyTimeThreshold = Swerve_Motion_Constants.SWERVE_ANGLE_MOTOR_CONSTANTS.SWERVE_ANGLE_CURRENT_THRESHOLD_TIME;

        A_MOTOR_CONFIG.Feedback.SensorToMechanismRatio = (360 / swerveTypeConstants.ANGLE_GEAR_RATIO);
        A_MOTOR_CONFIG.ClosedLoopGeneral.ContinuousWrap = true;

        ANGLE_MOTOR.getConfigurator().apply(A_MOTOR_CONFIG);
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
