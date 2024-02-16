package frc.robot.commands.Shoot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootSubsystem;

import java.util.function.BooleanSupplier;


public class ShootNoteCommand extends Command {

    private final ShootSubsystem SHOOT_SUBSYSTEM;
    private final BooleanSupplier VISION_AIM_SUP;
    private final XboxController OPERATOR_CONTROLLER;
    private final NetworkTable LIME_LIGHT = NetworkTableInstance.getDefault().getTable("limelight");

    private boolean lastHasTarget = false;
    private double targetDistance = 0;
    public ShootNoteCommand(ShootSubsystem SHOOT_SUBSYSTEM, BooleanSupplier VISION_AIM_SUP, XboxController OPERATOR_CONTROLLER) {
        this.SHOOT_SUBSYSTEM = SHOOT_SUBSYSTEM;
        this.OPERATOR_CONTROLLER = OPERATOR_CONTROLLER;
        this.VISION_AIM_SUP = VISION_AIM_SUP;

        addRequirements(SHOOT_SUBSYSTEM);
    }

    @Override
    public void initialize() {
        SHOOT_SUBSYSTEM.stopAllMotors();
        lastHasTarget = false;
    }

    @Override
    public void execute() {
        if (VISION_AIM_SUP.getAsBoolean()) {

        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        SHOOT_SUBSYSTEM.stopAllMotors();
    }

    public void updateCommand() {
        SHOOT_SUBSYSTEM.setDefaultCommand(this);
    }
}
