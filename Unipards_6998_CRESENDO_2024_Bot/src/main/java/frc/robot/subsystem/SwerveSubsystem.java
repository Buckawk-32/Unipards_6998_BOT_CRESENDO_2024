package frc.robot.subsystem;


import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase {
    private final static SwerveSubsystem INSTANCE = new SwerveSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static SwerveSubsystem getInstance() {
        return INSTANCE;
    }

    private SwerveSubsystem() {
        SwerveModuleState.optimize()
    }
}

