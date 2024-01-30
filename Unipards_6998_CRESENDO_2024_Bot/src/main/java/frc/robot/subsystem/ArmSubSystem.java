package frc.robot.subsystem;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.All_Constants.Constants.ARM_CURRENT_LIMIT;
import static frc.robot.All_Constants.Constants.ARM_PID;
import static frc.robot.All_Constants.RobotMap.ARM_MOTOR_ID;

public class ArmSubSystem extends SubsystemBase{
    private CANSparkMax mArmMotor;
    private RelativeEncoder armMotorEncoder;
    private boolean armEnabled = false;


    public ArmSubSystem(){
        mArmMotor = new CANSparkMax(ARM_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        armMotorEncoder = mArmMotor.getEncoder();
        configArm();
    }
    public void configArm(){
        mArmMotor.restoreFactoryDefaults();
        mArmMotor.setInverted(true);
        mArmMotor.setSmartCurrentLimit(ARM_CURRENT_LIMIT);
        mArmMotor.getPIDController().setP(ARM_PID[0]);
        mArmMotor.getPIDController().setI(ARM_PID[1]);
        mArmMotor.getPIDController().setD(ARM_PID[2]);
        mArmMotor.getPIDController().setFF(ARM_PID[3]);

        mArmMotor.setIdleMode(CANSparkBase.IdleMode.kBrake);


        mArmMotor.getEncoder().setVelocityConversionFactor(0.1);
        mArmMotor.getEncoder().setPositionConversionFactor(0.1);

        mArmMotor.burnFlash();
    }




    
}
