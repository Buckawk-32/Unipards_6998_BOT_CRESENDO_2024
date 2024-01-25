package frc.robot.subsystem;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.RobotMap;

public class ShooterSubSystem extends SubsystemBase {
    private final CANSparkMax mShooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private final CANSparkMax mIntake = new CANSparkMax(RobotMap.INTAKE_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    // Neo 550 for Intake, NEO for shooter.

    private 

    
}
