package frc.robot.subsystem.Swerve;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.All_Constants.Constants.NAVX_UPDATE_RATE;

public class SwerveSubsystem extends SubsystemBase {
    private final static SwerveSubsystem INSTANCE = new SwerveSubsystem();
    private final AHRS NAVX = new AHRS(I2C.Port.kMXP,NAVX_UPDATE_RATE);
    @SuppressWarnings("WeakerAccess")
    public static SwerveSubsystem getInstance() {
        return INSTANCE;
    }

    private SwerveSubsystem() {

    }
}

