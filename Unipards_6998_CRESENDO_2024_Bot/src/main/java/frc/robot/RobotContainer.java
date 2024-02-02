// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Drive.SwerveDriveCommand;
import frc.robot.subsystem.ArmSubsystem;
import frc.robot.subsystem.Shoot_Intake_Subsystem;
import frc.robot.subsystem.SwerveSubsystem;

public class RobotContainer {

  private final SwerveSubsystem SWERVE_SUBSYSTEM = new SwerveSubsystem();
  private final Shoot_Intake_Subsystem SHOOT_INTAKE_SUBSYSTEM = new Shoot_Intake_Subsystem();
  private final ArmSubsystem ARM_SUBSYSTEM = new ArmSubsystem();
  private final static XboxController DRIVE_CONTROLLER = new XboxController(0);
  private final static XboxController OPERATOR_CONTROLLER = new XboxController(1);

  public RobotContainer() {

    SWERVE_SUBSYSTEM.setDefaultCommand(new SwerveDriveCommand(SWERVE_SUBSYSTEM,
            DRIVE_CONTROLLER::getLeftY,
            DRIVE_CONTROLLER::getLeftX,
            DRIVE_CONTROLLER::getRightX,
            DRIVE_CONTROLLER::getLeftBumper));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(DRIVE_CONTROLLER, XboxController.Button.kLeftBumper.value)
            .onTrue(new InstantCommand(SWERVE_SUBSYSTEM::zeroGyro));

    new JoystickButton(OPERATOR_CONTROLLER, XboxController.Button.kA.value)
            .onTrue(new InstantCommand(SHOOT_INTAKE_SUBSYSTEM::setIntake));

    new JoystickButton(OPERATOR_CONTROLLER, XboxController.Button.kY.value)
            .onTrue(new InstantCommand(SHOOT_INTAKE_SUBSYSTEM::setShooter));

    new JoystickButton(OPERATOR_CONTROLLER, XboxController.Button.kB.value)
            .onTrue(new InstantCommand(SHOOT_INTAKE_SUBSYSTEM::zeroIntake));

    new JoystickButton(OPERATOR_CONTROLLER, XboxController.Button.kX.value)
            .onTrue(new InstantCommand(SHOOT_INTAKE_SUBSYSTEM::zeroShooter));


  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
