// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
//import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.CoralSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final CoralSubsystem m_coralSubsystem = new CoralSubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();

  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Configure default commands
    m_driveSubsystem.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_driveSubsystem.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(),
                    OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(),
                    OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(),
                    OIConstants.kDriveDeadband),
                true, true, m_driverController.getStartButton()),
            m_driveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // set lock formation of the drive
    new JoystickButton(m_driverController, XboxController.Button.kX.value)
        .whileTrue(new RunCommand(
            () -> m_driveSubsystem.setX(),
            m_driveSubsystem));

    // zero the heading
    new JoystickButton(m_driverController, XboxController.Button.kBack.value) // select button
        .onTrue(new RunCommand(() -> m_driveSubsystem.zeroHeading()));

    new JoystickButton(m_driverController, XboxController.Button.kA.value) // move coral in
        .whileTrue(new RunCommand(() -> m_coralSubsystem.moveIn(0.5)));

    new JoystickButton(m_driverController, XboxController.Button.kB.value) // move coral out
        .whileTrue(new RunCommand(() -> m_coralSubsystem.moveOut(0.5)));

    new JoystickButton(m_driverController, XboxController.Button.kA.value) // stop coral
        .whileFalse(new RunCommand(() -> m_coralSubsystem.stopMotion()));

    new JoystickButton(m_driverController, XboxController.Button.kB.value) // stop coral
        .whileFalse(new RunCommand(() -> m_coralSubsystem.stopMotion()));

    new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value) // move the elevator down manually
        .whileTrue(new RunCommand(() -> m_elevatorSubsystem.elevatorDown()));

    new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value)
        .whileFalse(new RunCommand(() -> m_elevatorSubsystem.stopElevator()));

    new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value) // move elevator up manually
        .whileTrue(new RunCommand(() -> m_elevatorSubsystem.elevatorUp()));

    new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value)
        .whileFalse(new RunCommand(() -> m_elevatorSubsystem.stopElevator()));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
