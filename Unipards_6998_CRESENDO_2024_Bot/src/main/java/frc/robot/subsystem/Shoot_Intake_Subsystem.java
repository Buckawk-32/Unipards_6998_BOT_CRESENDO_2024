package frc.robot.subsystem;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;


public class Shoot_Intake_Subsystem extends SubsystemBase {


    public CANSparkMax INTAKE_MOTOR;
    public CANSparkMax SHOOTER_MOTOR;

    public RelativeEncoder INTAKE_MOTOR_ENCODER;
    public RelativeEncoder SHOOTER_MOTOR_ENCODER;


    public Shoot_Intake_Subsystem() {
        INTAKE_MOTOR = new CANSparkMax(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        SHOOTER_MOTOR = new CANSparkMax(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

        INTAKE_MOTOR_ENCODER = INTAKE_MOTOR.getEncoder();
        config_intake();
        SHOOTER_MOTOR_ENCODER = SHOOTER_MOTOR.getEncoder();
        config_shooter();
    }

    private void config_intake() {

        INTAKE_MOTOR.restoreFactoryDefaults();

        INTAKE_MOTOR.getPIDController().setP(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KP, 0);
        INTAKE_MOTOR.getPIDController().setI(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KI, 0);
        INTAKE_MOTOR.getPIDController().setD(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KD, 0);
        INTAKE_MOTOR.getPIDController().setFF(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_KF, 0);

        INTAKE_MOTOR.setIdleMode(CANSparkBase.IdleMode.kCoast);
        INTAKE_MOTOR.setSmartCurrentLimit(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_CURRENT_LIMIT);

        INTAKE_MOTOR_ENCODER.setPositionConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO);
        INTAKE_MOTOR_ENCODER.setVelocityConversionFactor(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_GEAR_RATIO / 60);

        INTAKE_MOTOR.burnFlash();
    }

    private void config_shooter() {

        SHOOTER_MOTOR.restoreFactoryDefaults();

        SHOOTER_MOTOR.getPIDController().setP(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_KP, 0);
        SHOOTER_MOTOR.getPIDController().setI(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_KI, 0);
        SHOOTER_MOTOR.getPIDController().setD(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_KD, 0);
        SHOOTER_MOTOR.getPIDController().setFF(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_KF, 0);

        SHOOTER_MOTOR.setIdleMode(CANSparkBase.IdleMode.kCoast);
        SHOOTER_MOTOR.setSmartCurrentLimit(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_CURRENT_LIMIT);

        SHOOTER_MOTOR_ENCODER.setPositionConversionFactor(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_GEAR_RATIO);
        SHOOTER_MOTOR_ENCODER.setVelocityConversionFactor(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_GEAR_RATIO / 60);

        SHOOTER_MOTOR.burnFlash();
    }

    public void setIntake() {
        INTAKE_MOTOR.getPIDController().setReference(Mechanism_Constants.INTAKE_CONSTANTS.INTAKE_RPM, CANSparkBase.ControlType.kVelocity);
    }

    public void zeroIntake() {
        INTAKE_MOTOR.getPIDController().setReference(0, CANSparkBase.ControlType.kVelocity);
    }

    public void setShooter() {
        SHOOTER_MOTOR.getPIDController().setReference(Mechanism_Constants.SHOOTER_CONSTANTS.SHOOTER_RPM, CANSparkBase.ControlType.kVelocity);
    }

    public void zeroShooter() {
        SHOOTER_MOTOR.getPIDController().setReference(0, CANSparkBase.ControlType.kVelocity);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Intake RPM", INTAKE_MOTOR_ENCODER.getVelocity());
        SmartDashboard.putNumber("Shooter RPM", SHOOTER_MOTOR_ENCODER.getVelocity());

    }

}
