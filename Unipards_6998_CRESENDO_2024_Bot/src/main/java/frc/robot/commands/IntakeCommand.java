package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.complexArm.CollectSubSystem;

public class IntakeCommand extends CommandBase {

    private final CollectSubSystem collectSubSystem;
    private final XboxController drivController;
    private final XboxController operatorController;

    private IntakeCommand(CollectSubSystem collectSubSystem, XboxController drivController, XboxController operatorController) {
        this.collectSubSystem = collectSubSystem;
        this.drivController = drivController;
        this.operatorController = operatorController;
        addRequirements(collectSubSystem);
    }

    @Override
    public void initialize() {
        collectSubSystem.disableIntake();
    }

    @Override
    public void execute() {
       if (!DriverStation.isTeleopEnabled()) return;
       if (drivController.getAButton() || operatorController.getAButton()) {
           collectSubSystem.enableIntake(false);
       } 
       else if (drivController.getBButton() || operatorController.getBButton()) {
           collectSubSystem.enableIntake(true);
       } 
       else {
           collectSubSystem.disableIntake();
       }

    }

    
    
}
