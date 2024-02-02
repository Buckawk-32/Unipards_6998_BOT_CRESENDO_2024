package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;

public class PhotonVisionHelpers{

    private final PhotonCamera CAMERA;
    private final NetworkTableInstance NETWORK_TABLE;

    public PhotonVisionHelpers() {

        NETWORK_TABLE = NetworkTableInstance.

        CAMERA = new PhotonCamera();
    }


}
