package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RobotDrive;

public class AutoCommand extends Command {
  RobotDrive robotDrive;
  private Timer timer;
  private double seconds = 1.0;

  public AutoCommand(RobotDrive robotDrive) {
    this.robotDrive = robotDrive;
    timer = new Timer();
    addRequirements(robotDrive);
  }

  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute() {
    robotDrive.driveTank(0.5, 0.0);
  }

  @Override
  public void end(boolean isInterrupted) {
    robotDrive.driveTank(0.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= seconds;
  }
}
