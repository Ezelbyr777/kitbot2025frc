package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants.DropperConstants;

public class CoralDropper extends SubsystemBase {
  
  
  private final SparkMax dropperMotor = new SparkMax(DropperConstants.Dropper_MOTOR_ID, MotorType.kBrushed);
  Encoder dropperEncoder = new Encoder(5, 6, false);
  SparkMaxConfig dropperConfig = new SparkMaxConfig();
  PIDController pidController = new PIDController(0.1, 0.0, 0.0);

  public CoralDropper() {
    
    dropperMotor.setCANTimeout(250);

    dropperConfig.voltageCompensation(DropperConstants.Dropper_MOTOR_VOLTAGE_COMP);
    dropperConfig.smartCurrentLimit(DropperConstants.Dropper_MOTOR_CURRENT_LIMIT);
    dropperMotor.configure(dropperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setSpeed(double targetSpeed){

    pidController.setSetpoint(targetSpeed);
    double output = pidController.calculate(dropperEncoder.getRate());
    dropperMotor.set(output);
}

public void stop() {
    dropperMotor.set(0);
}
  @Override
  public void periodic() {}

  public void rundropper(double forward, double reverse) {
    dropperMotor.set(forward - reverse);
  }
}