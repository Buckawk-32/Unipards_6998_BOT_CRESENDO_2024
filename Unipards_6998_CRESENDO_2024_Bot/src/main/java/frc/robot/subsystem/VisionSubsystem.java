package frc.robot.subsystem;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;

public class VisionSubsystem extends SubsystemBase {

    private final static VisionSubsystem INSTANCE = new VisionSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static VisionSubsystem getInstance() {
        return INSTANCE;
    }

    private final SwerveSubsystem SWERVE_SUBSYSTEM = SwerveSubsystem.getInstance();

    private final PhotonCamera CAMERA;
    private final PhotonPoseEstimator PHOTON_POSE_ESTIMATOR;
    private double LAST_ESTIMATED_TIMESTAMP = 0.0;


    public VisionSubsystem() {
        CAMERA = new PhotonCamera("Camera 01");
    }
}

