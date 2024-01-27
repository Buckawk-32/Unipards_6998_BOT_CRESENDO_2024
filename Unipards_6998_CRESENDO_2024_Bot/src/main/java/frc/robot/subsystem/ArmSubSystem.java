package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.All_Constants.Constants.ARM_CURRENT_LIMIT;
import static frc.robot.All_Constants.Constants.ARM_PID;
import static frc.robot.All_Constants.RobotMap.ARM_MOTOR_ID;

public class ArmSubSystem extends SubsystemBase{
    private final CANSparkMax armMotor = new CANSparkMax(ARM_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private boolean armEnabled = false;

    public ArmSubSystem(){
        armMotor.restoreFactoryDefaults();
        armMotor.setInverted(true);
        armMotor.setSmartCurrentLimit(ARM_CURRENT_LIMIT);
        armMotor.getPIDController().setP(ARM_PID[0]);
        armMotor.getPIDController().setI(ARM_PID[1]);
        armMotor.getPIDController().setD(ARM_PID[2]);
        armMotor.getPIDController().setFF(ARM_PID[3]);

        armMotor.getEncoder().setVelocityConversionFactor(0.1);
        armMotor.getEncoder().setPositionConversionFactor(0.1);

        armMotor.burnFlash();
    }




    
}
