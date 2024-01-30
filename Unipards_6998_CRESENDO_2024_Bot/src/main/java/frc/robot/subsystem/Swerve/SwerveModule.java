package frc.robot.subsystem.Swerve;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.lib.math.Conversions;
import frc.lib.util.Swerve_Type_Constants;

import static frc.robot.All_Constants.Constants.*;

public class SwerveModule {
    public int ModuleID;
    private Swerve_Type_Constants swerveTypeConstants;
    private TalonFX mDriveFalcon;
    private TalonFX mAngleFalcon;
    private CANcoder angleCanCoder;
    private Rotation2d angleOffset;
    private Rotation2d lastAngle;


    SimpleMotorFeedforward angleFeedForward = new SimpleMotorFeedforward(
            SWERVE_DRIVE_KS,
            SWERVE_DRIVE_KV,
            SWERVE_DRIVE_KA
    );

    public SwerveModule(
            int ModuleID,
            Swerve_Type_Constants swerveTypeConstants,
            int driveMotorID, int angleMotorID, int angleCanCoderID,
            Rotation2d angleOffset) {
        this.swerveTypeConstants = swerveTypeConstants;
        this.ModuleID = ModuleID;
        this.angleOffset = angleOffset;
        mDriveFalcon = new TalonFX(driveMotorID);
        configDriveMotor();
        mDriveFalcon.getConfigurator().setPosition(0);

        mAngleFalcon = new TalonFX(angleMotorID);

        angleCanCoder = new CANcoder(angleCanCoderID);




        lastAngle = getState().angle;

    }

    private Rotation2d getAngle(){
        return Rotation2d.fromDegrees(angleMotorEncoder.getPosition());
    }
    private Rotation2d getCanCoder(){
        return Rotation2d.fromDegrees(angleCanCoder.getAbsolutePosition());

    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(Conversions.falconToMPS(
                mDriveFalcon.getPosition().getValue(),
                swerveTypeConstants.WHEEL_CIRCUMFERENCE,
                swerveTypeConstants.DRIVE_GEAR_RATIO),getAngle());
    }
    private void configDriveMotor(){
        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        driveConfig.Slot0.kP = CHASSIS_PID[0];
        driveConfig.Slot0.kI = CHASSIS_PID[1];
        driveConfig.Slot0.kD = CHASSIS_PID[2];
        driveConfig.Slot0.kS = SWERVE_DRIVE_KS;
        driveConfig.Slot0.kV = SWERVE_DRIVE_KV;

    }
    private void configAngleMotor(){
        TalonFXConfiguration angleConfig = new TalonFXConfiguration();
        angleConfig.Slot0.kP =
        angleConfig.Slot0.kI = CHASSIS_PID[1];
        angleConfig.Slot0.kD = CHASSIS_PID[2];
        angleConfig.Slot0.kS = SWERVE_DRIVE_KS;
        angleConfig.Slot0.kV = SWERVE_DRIVE_KV;
    }
}
