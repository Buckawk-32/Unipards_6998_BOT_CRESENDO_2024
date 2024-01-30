// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystem.ArmSubSystem;
import frc.robot.subsystem.Swerve.SwerveSubsystem;
import frc.robot.subsystem.complexArm.CollectSubSystem;
import frc.robot.subsystem.complexArm.ShooterSubSystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotContainer {
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final ArmSubSystem armSubSystem = new ArmSubSystem();
  private final CollectSubSystem collectSubSystem = new CollectSubSystem();
  private final ShooterSubSystem shooterSubSystem = new ShooterSubSystem();

  private final XboxController drivController = new XboxController(0);
  private final XboxController operatorController = new XboxController(1);
  private SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
