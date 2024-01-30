package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.ArmSubSystem;

public class ArmCommands extends CommandBase {
    private final ArmSubSystem armSubSystem;
    private final XboxController operatorController;


    public ArmCommands(ArmSubSystem armSubSystem, XboxController operatorController) {
        this.armSubSystem = armSubSystem;
        this.operatorController = operatorController;
        addRequirements(armSubSystem);
    }
    @Override
    public void initialize() {
        armSubSystem.disableArm();
    }
    @Override
    public void execute() {
        armSubSystem.setArm(operatorController.getLeftY());
    }
}
