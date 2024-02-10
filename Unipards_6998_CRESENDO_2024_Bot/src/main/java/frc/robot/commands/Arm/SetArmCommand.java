package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.All_Constants.Mechanism.Mechanism_Constants;
import frc.robot.subsystem.ArmSubsystem;

import java.util.function.IntSupplier;


public class SetArmCommand extends Command {

    private final ArmSubsystem ARM_SUBSYSTEM;
    private final IntSupplier POV_SUP;
    private double ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.DEFAULT_ARM_ANGLE;

    public SetArmCommand(ArmSubsystem ARM_SUBSYSTEM, IntSupplier POV_SUP) {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        this.ARM_SUBSYSTEM = ARM_SUBSYSTEM;
        this.POV_SUP = POV_SUP;

        addRequirements(ARM_SUBSYSTEM);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        switch (POV_SUP.getAsInt()) {
            case 270:
                if (ARM_DEGREE > Mechanism_Constants.ARM_CONSTANTS.MINIMUM_ARM_ANGLE && ARM_DEGREE <= Mechanism_Constants.ARM_CONSTANTS.MAXIMUM_ARM_ANGLE) {
                    ARM_SUBSYSTEM.setArm(Mechanism_Constants.ARM_CONSTANTS.MINIMUM_ARM_ANGLE);
                    ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.MINIMUM_ARM_ANGLE;
                }
                break;
            case 0:
                if (ARM_DEGREE != Mechanism_Constants.ARM_CONSTANTS.DEFAULT_ARM_ANGLE) {
                    ARM_SUBSYSTEM.setArm(Mechanism_Constants.ARM_CONSTANTS.DEFAULT_ARM_ANGLE);
                    ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.DEFAULT_ARM_ANGLE;
                }
                break;
            case 90:
                if (ARM_DEGREE < Mechanism_Constants.ARM_CONSTANTS.MAXIMUM_ARM_ANGLE && ARM_DEGREE >= Mechanism_Constants.ARM_CONSTANTS.MINIMUM_ARM_ANGLE) {
                    ARM_SUBSYSTEM.setArm(Mechanism_Constants.ARM_CONSTANTS.MAXIMUM_ARM_ANGLE);
                    ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.MINIMUM_ARM_ANGLE;
                }
                break;
            case 180:
                if (ARM_DEGREE != Mechanism_Constants.ARM_CONSTANTS.AMP_ARM_ANGLE) {
                    ARM_SUBSYSTEM.setArm(Mechanism_Constants.ARM_CONSTANTS.AMP_ARM_ANGLE);
                    ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.AMP_ARM_ANGLE;
                }
                break;
            default:
                ARM_SUBSYSTEM.setArm(ARM_DEGREE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        ARM_DEGREE = Mechanism_Constants.ARM_CONSTANTS.DEFAULT_ARM_ANGLE;
    }
}
