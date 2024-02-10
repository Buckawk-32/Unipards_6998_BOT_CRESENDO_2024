package frc.robot.commands.Drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.All_Constants.Swerve.Swerve_Motion_Constants;
import frc.robot.subsystem.SwerveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class SwerveDriveCommand extends Command{

    private SwerveSubsystem swerveSubsystem;
    private DoubleSupplier x_sup;
    private DoubleSupplier y_sup;
    private DoubleSupplier z_sup;
    private BooleanSupplier field_center_sup;

    public SwerveDriveCommand(SwerveSubsystem swerveSubsystem, DoubleSupplier x_sup, DoubleSupplier y_sup, DoubleSupplier z_sup, BooleanSupplier field_center_sup) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);

        this.x_sup = x_sup;
        this.y_sup = y_sup;
        this.z_sup = z_sup;
        this.field_center_sup = field_center_sup;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double X = MathUtil.applyDeadband(x_sup.getAsDouble(), Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_DEADBAND);
        double Y = MathUtil.applyDeadband(y_sup.getAsDouble(), Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_DEADBAND);
        double Z = MathUtil.applyDeadband(z_sup.getAsDouble(), Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_DEADBAND);

        swerveSubsystem.drive(
                new Translation2d(X, Y).times(Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED),
                Z * Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_ANGULAR_VELOCITY,
                !field_center_sup.getAsBoolean(),
                true
        );
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
