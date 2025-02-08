package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralDropper;

import java.util.function.DoubleSupplier;

public class DropperCommand extends Command {
  private final DoubleSupplier forward;
  private final DoubleSupplier reverse;
  private CoralDropper coralDropper;
  
    public DropperCommand(
        DoubleSupplier forward, DoubleSupplier reverse, CoralDropper coralDropper) {
      this.forward = forward;
      this.reverse = reverse;
      this.coralDropper = coralDropper;

    addRequirements(this.coralDropper);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    coralDropper.rundropper(forward.getAsDouble(), reverse.getAsDouble());
  }

  @Override
  public void end(boolean isInterrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}