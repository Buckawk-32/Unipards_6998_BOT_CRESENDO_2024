package frc.robot.subsystem;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;

public class ArmSubsystem extends SubsystemBase {

    public CANSparkMax ARM_MOTOR;
    public RelativeEncoder ARM_MOTOR_ENCODER;

    public ArmSubsystem() {

        ARM_MOTOR = new CANSparkMax(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

        ARM_MOTOR_ENCODER = ARM_MOTOR.getEncoder();
        config_arm();
    }

    private void config_arm() {

        ARM_MOTOR.restoreFactoryDefaults();

        ARM_MOTOR.getPIDController().setP(Mechanism_Constants.ARM_CONSTANTS.ARM_KP);
        ARM_MOTOR.getPIDController().setI(Mechanism_Constants.ARM_CONSTANTS.ARM_KI);
        ARM_MOTOR.getPIDController().setD(Mechanism_Constants.ARM_CONSTANTS.ARM_KD);
        ARM_MOTOR.getPIDController().setFF(Mechanism_Constants.ARM_CONSTANTS.ARM_KF);

        ARM_MOTOR.setSmartCurrentLimit(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_LIMIT);

        ARM_MOTOR.setIdleMode(CANSparkBase.IdleMode.kBrake);

        ARM_MOTOR_ENCODER.setPositionConversionFactor(Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO);
        ARM_MOTOR_ENCODER.setVelocityConversionFactor(Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO / 60);
    }

    public void Set_Arm(double RPM) {
        ARM_MOTOR.getPIDController().setReference(RPM, CANSparkBase.ControlType.kVelocity);
    }

    public void Zero_Arm() {
        ARM_MOTOR.getPIDController().setReference(0, CANSparkBase.ControlType.kVelocity);
    }

}
