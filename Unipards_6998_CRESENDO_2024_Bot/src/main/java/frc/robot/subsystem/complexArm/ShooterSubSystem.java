package frc.robot.subsystem;

import static frc.robot.All_Constants.Constants.SHOOTER_PID;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.RobotMap;

public class ShooterSubSystem extends SubsystemBase {
    private final CANSparkMax mShooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private boolean shooterEnabled = false;
    // Neo 550 for Intake, NEO for shooter.
    public ShooterSubSystem() {
       
        mShooter.restoreFactoryDefaults();
        mShooter.setInverted(RobotMap.SHOOTER_INVERTED);
        mShooter.setSmartCurrentLimit(40);

        mShooter.getPIDController().setP(SHOOTER_PID[0]);
        mShooter.getPIDController().setI(SHOOTER_PID[1]);
        mShooter.getPIDController().setD(SHOOTER_PID[2]);
        mShooter.getPIDController().setFF(SHOOTER_PID[3]);

        mShooter.getEncoder().setVelocityConversionFactor(0.1);
        mShooter.getEncoder().setPositionConversionFactor(0.1);

   
        mShooter.burnFlash();
    }
}
