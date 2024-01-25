package frc.robot.subsystem;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.*;
import static frc.robot.All_Constants.Constants.*;


public class InShootSubSystem extends SubsystemBase {
    private final CANSparkMax mShooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private final CANSparkMax mIntake = new CANSparkMax(RobotMap.INTAKE_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private boolean intakeEnabled = false;
    private boolean shooterEnabled = false;
    // Neo 550 for Intake, NEO for shooter.
    public InShootSubSystem() {
        mShooter.restoreFactoryDefaults();
        mIntake.restoreFactoryDefaults();
        mIntake.setInverted(RobotMap.INTAKE_INVERTED);
        mShooter.setInverted(RobotMap.SHOOTER_INVERTED);
        mIntake.setSmartCurrentLimit(40);
        mShooter.setSmartCurrentLimit(40);

        mIntake.getPIDController().setP(INTAKE_PID[0]);
        mIntake.getPIDController().setI(INTAKE_PID[1]);
        mIntake.getPIDController().setD(INTAKE_PID[2]);
        mIntake.getPIDController().setFF(INTAKE_PID[3]);

        mShooter.getPIDController().setP(SHOOTER_PID[0]);
        mShooter.getPIDController().setI(SHOOTER_PID[1]);
        mShooter.getPIDController().setD(SHOOTER_PID[2]);
        mShooter.getPIDController().setFF(SHOOTER_PID[3]);
        
        mIntake.burnFlash();
        mShooter.burnFlash();
    }
    public void disableIntake(){
        mIntake.set(0);
        intakeEnabled = false;
    }
    public void enableIntake(boolean reverse){
        double rpm = reverse ? 1000.0 : -1000.0;
        
        mIntake.set(1);
        intakeEnabled = true;
    }
    
}
