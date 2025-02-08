package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.DropperConstants;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DropperCommand;
import frc.robot.subsystems.CoralDropper;
import frc.robot.subsystems.RobotDrive;

public class RobotContainer {

  private final RobotDrive robotDrive = new RobotDrive();
  private final CoralDropper coralDropper = new CoralDropper();

  private final CommandXboxController driverController = new CommandXboxController(
      OperatorConstants.DRIVER_CONTROLLER_PORT);

  private final CommandXboxController operatorController = new CommandXboxController(
      OperatorConstants.OPERATOR_CONTROLLER_PORT);

  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();

    autoChooser.setDefaultOption("Autonomous", new AutoCommand(robotDrive));
  }

  private void configureBindings() {

    operatorController.a()
        .whileTrue(new DropperCommand(() -> DropperConstants.Dropper_EJECT_VALUE, () -> 0, coralDropper));

    robotDrive.setDefaultCommand(new DriveCommand(
        () -> -driverController.getLeftY() * (driverController.getHID().getRightBumper() ? 1 : 0.5),
        () -> -driverController.getRightX(),
        robotDrive));
    coralDropper.setDefaultCommand(new DropperCommand(
        () -> operatorController.getRightTriggerAxis(),
        () -> operatorController.getLeftTriggerAxis(), coralDropper));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
