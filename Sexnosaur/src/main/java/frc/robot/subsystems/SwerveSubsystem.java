package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase {

    private final static SwerveSubsystem INSTANCE = new SwerveSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static SwerveSubsystem getInstance() {
        return INSTANCE;
    }

    public SwerveSubsystem() {

    }
}
