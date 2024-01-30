package frc.robot.subsystem;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.All_Constants.Constants.*;
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


        armMotorEncoder.setPositionConversionFactor(360/armGearRatio);
        armMotorEncoder.setVelocityConversionFactor(360/(armGearRatio/60));

        mArmMotor.burnFlash();
    }
    public void setArm(double degree){
        mArmMotor.getPIDController().setReference(degree, CANSparkBase.ControlType.kSmartMotion);
    }
    public void disableArm(){
        mArmMotor.set(0);
        armEnabled = false;
    }




    
}
