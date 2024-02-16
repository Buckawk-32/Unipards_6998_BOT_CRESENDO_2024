package frc.robot.commands.Drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Module_Constants;
import frc.robot.All_Constants.Swerve.Swerve_Motion_Constants;
import frc.robot.LimeLightHelpers;
import frc.robot.subsystems.SwerveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class SwerveDriveCommand extends Command {

    private SwerveSubsystem SWERVE_SUBSYSTEM;
    private DoubleSupplier x_sup;
    private DoubleSupplier y_sup;
    private DoubleSupplier z_sup;
    private BooleanSupplier field_center_sup;

    private BooleanSupplier VISION_AIM_SUP;
    private final PIDController VISION_AIM_PID = new PIDController(Swerve_Motion_Constants.SWERVE_VISION_CONSTANTS.VISION_AIM_KP, Swerve_Motion_Constants.SWERVE_VISION_CONSTANTS.VISION_AIM_KI, Swerve_Motion_Constants.SWERVE_VISION_CONSTANTS.VISION_AIM_KD);

    public SwerveDriveCommand(SwerveSubsystem swerveSubsystem, DoubleSupplier x_sup, DoubleSupplier y_sup, DoubleSupplier z_sup, BooleanSupplier field_center_sup, BooleanSupplier VISION_AIM_SUP) {
        this.SWERVE_SUBSYSTEM = swerveSubsystem;
        addRequirements(swerveSubsystem);

        this.x_sup = x_sup;
        this.y_sup = y_sup;
        this.z_sup = z_sup;
        this.VISION_AIM_SUP = VISION_AIM_SUP;
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

        double visionAimNum = LimeLightHelpers.getTX(Swerve_Module_Constants.LIMELIGHT_CONSTANTS.LIMELIGHT_NAME);
        if (VISION_AIM_SUP.getAsBoolean()&&visionAimNum!=0.0) {
            double vision_rotation = VISION_AIM_PID.calculate(visionAimNum, 0);
            /* Vision Drive */
            SWERVE_SUBSYSTEM.drive(
                    new Translation2d(X, Y).times(Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED),
                    vision_rotation * Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_ANGULAR_VELOCITY,
                    !field_center_sup.getAsBoolean(),
                    true
            );
        }else {
            /* Drive */
            SWERVE_SUBSYSTEM.drive(
                    new Translation2d(X, Y).times(Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_SPEED),
                    Z * Swerve_Motion_Constants.SWERVE_CHASSIS_CONSTANTS.SWERVE_MAX_ANGULAR_VELOCITY,
                    !field_center_sup.getAsBoolean(),
                    true
            );}
    }
}
