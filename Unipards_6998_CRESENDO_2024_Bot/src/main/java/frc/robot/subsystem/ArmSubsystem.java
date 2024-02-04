package frc.robot.subsystem;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;

public class ArmSubsystem extends SubsystemBase {

    public CANSparkMax ARM_MOTOR_1;
    public RelativeEncoder ARM_MOTOR_1_ENCODER;
    public CANSparkMax ARM_MOTOR_2;
    public RelativeEncoder ARM_MOTOR_2_ENCODER;

    public ArmSubsystem() {

        ARM_MOTOR_1 = new CANSparkMax(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_1_ID, CANSparkLowLevel.MotorType.kBrushless);
        ARM_MOTOR_2 = new CANSparkMax(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_2_ID, CANSparkLowLevel.MotorType.kBrushless);

        ARM_MOTOR_1_ENCODER = ARM_MOTOR_1.getEncoder();
        ARM_MOTOR_2_ENCODER = ARM_MOTOR_2.getEncoder();
        config_arm();
    }

    private void config_arm() {

        ARM_MOTOR_1.restoreFactoryDefaults();

        ARM_MOTOR_1.getPIDController().setP(Mechanism_Constants.ARM_CONSTANTS.ARM_KP);
        ARM_MOTOR_1.getPIDController().setI(Mechanism_Constants.ARM_CONSTANTS.ARM_KI);
        ARM_MOTOR_1.getPIDController().setD(Mechanism_Constants.ARM_CONSTANTS.ARM_KD);
        ARM_MOTOR_1.getPIDController().setFF(Mechanism_Constants.ARM_CONSTANTS.ARM_KF);

        ARM_MOTOR_1.setSmartCurrentLimit(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_LIMIT);

        ARM_MOTOR_1.setIdleMode(CANSparkBase.IdleMode.kBrake);

        ARM_MOTOR_1_ENCODER.setPositionConversionFactor(360 / Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO);
        ARM_MOTOR_1_ENCODER.setVelocityConversionFactor(360 / (Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO / 60));

        ARM_MOTOR_2.restoreFactoryDefaults();

        ARM_MOTOR_2.getPIDController().setP(Mechanism_Constants.ARM_CONSTANTS.ARM_KP);
        ARM_MOTOR_2.getPIDController().setI(Mechanism_Constants.ARM_CONSTANTS.ARM_KI);
        ARM_MOTOR_2.getPIDController().setD(Mechanism_Constants.ARM_CONSTANTS.ARM_KD);
        ARM_MOTOR_2.getPIDController().setFF(Mechanism_Constants.ARM_CONSTANTS.ARM_KF);

        ARM_MOTOR_2.setSmartCurrentLimit(Mechanism_Constants.ARM_CONSTANTS.ARM_MOTOR_LIMIT);

        ARM_MOTOR_2.setIdleMode(CANSparkBase.IdleMode.kBrake);

        ARM_MOTOR_2_ENCODER.setPositionConversionFactor(360 / Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO);
        ARM_MOTOR_2_ENCODER.setVelocityConversionFactor(360 / (Mechanism_Constants.ARM_CONSTANTS.ARM_GEAR_RATIO / 60));

        ARM_MOTOR_2.follow(ARM_MOTOR_1);

        ARM_MOTOR_1.burnFlash();
        ARM_MOTOR_2.burnFlash();
    }

    public void setArm(double degree) {
        ARM_MOTOR_1.getPIDController().setReference(degree, CANSparkBase.ControlType.kSmartMotion);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm angle (degrees)", ARM_MOTOR_1_ENCODER.getPosition());
    }

}
