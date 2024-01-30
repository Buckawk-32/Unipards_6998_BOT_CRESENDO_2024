package frc.robot.subsystem.complexArm;
import com.revrobotics.CANSparkMax;

import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.All_Constants.*;
import static frc.robot.All_Constants.Constants.*;


public class CollectSubSystem extends SubsystemBase {
    public CANSparkMax mIntake;
    public RelativeEncoder mIntakeEncoder;

    private boolean intakeEnabled = false;
    // Neo 550 for Intake, NEO for shooter.
    public CollectSubSystem() {
        mIntake = new CANSparkMax(RobotMap.INTAKE_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        mIntakeEncoder = mIntake.getEncoder();

        configIntake();
        mIntake.burnFlash();
    }
    private void configIntake(){
        mIntake.restoreFactoryDefaults();
        mIntake.setInverted(RobotMap.INTAKE_INVERTED);
        mIntake.setSmartCurrentLimit(40);

        mIntake.getPIDController().setP(INTAKE_PID[0]);
        mIntake.getPIDController().setI(INTAKE_PID[1]);
        mIntake.getPIDController().setD(INTAKE_PID[2]);
        mIntake.getPIDController().setFF(INTAKE_PID[3]);

        mIntake.getEncoder().setVelocityConversionFactor(0.1);
        mIntake.getEncoder().setPositionConversionFactor(0.1);
    }
    public void disableIntake(){
        mIntake.set(0);
        intakeEnabled = false;
    }
    public void enableIntake(boolean reverse){
        double rpm = reverse ? 1000.0 : -1000.0; // TODO: Tune this
        mIntake.getPIDController().setReference(rpm, CANSparkMax.ControlType.kVelocity);
        mIntake.set(1);
        intakeEnabled = true;
    }
    public boolean isIntakeEnabled(){
        return intakeEnabled;
    }
    public void enableIntake(){
        enableIntake(false);
    }

    public void setIntakeSpeed(double speed){
        mIntake.set(speed);
    }

    
}
