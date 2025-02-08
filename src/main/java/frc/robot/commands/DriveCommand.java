package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RobotDrive;

public class DriveCommand extends Command {
    
    private final RobotDrive robotDrive;
    private final DoubleSupplier forward;
    private final DoubleSupplier rotation;

    public DriveCommand(DoubleSupplier forward, DoubleSupplier rotation, RobotDrive robotDrive) {
        this.forward = forward;
        this.rotation = rotation;
        this.robotDrive = robotDrive;

        addRequirements(this.robotDrive);
    }
  
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        robotDrive.tankMode(forward.getAsDouble(), rotation.getAsDouble());
    }

    @Override
    public void end(boolean isInterrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
