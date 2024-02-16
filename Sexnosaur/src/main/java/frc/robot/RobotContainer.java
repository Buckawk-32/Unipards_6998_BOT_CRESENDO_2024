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
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


public class RobotContainer {
//  private final SwerveSubsystem SWERVE_SUBSYSTEM = new SwerveSubsystem();
//  private final CollectSubsystem COLLECT_SUBSYSTEM = new CollectSubsystem();
//
//  private final static XboxController DRIVE_CONTROLLER = new XboxController(0);
//  private final static XboxController OPERATOR_CONTROLLER = new XboxController(1);
//
//  public RobotContainer() {
//
////    SWERVE_SUBSYSTEM.setDefaultCommand(new SwerveDriveCommand(SWERVE_SUBSYSTEM,
////            DRIVE_CONTROLLER::getLeftY,
////            DRIVE_CONTROLLER::getLeftX,
////            DRIVE_CONTROLLER::getRightX,
////            DRIVE_CONTROLLER::getLeftBumper,
////            DRIVE_CONTROLLER::getRightBumper));
////
////    COLLECT_SUBSYSTEM.setDefaultCommand(new CollectNoteCommand(COLLECT_SUBSYSTEM,
////            OPERATOR_CONTROLLER::getRightBumper));
////
////    SHOOT_SUBSYSTEM.setDefaultCommand(new ShootNoteCommand(SHOOT_SUBSYSTEM,
////            OPERATOR_CONTROLLER::getXButton,
////            OPERATOR_CONTROLLER::getYButton,
////            OPERATOR_CONTROLLER::getAButton,
////            OPERATOR_CONTROLLER::getBButton));
//
//    configureBindings();
//  }
//
//  private void configureBindings() {
//      new JoystickButton(OPERATOR_CONTROLLER, XboxController.Button.kA.value)
//              .onTrue(new InstantCommand(COLLECT_SUBSYSTEM::setIntaketoRPM));
//  }
//
//  public Command getAutonomousCommand() {
//    return Commands.print("No autonomous command configured");
//  }
//

}
