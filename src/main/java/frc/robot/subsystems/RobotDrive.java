package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;;

public class RobotDrive extends SubsystemBase {
  private final SparkMax leftMaster;
  private final SparkMax leftSlave;
  private final SparkMax rightMaster;
  private final SparkMax rightSlave;

  private final DifferentialDrive drive;

  private RelativeEncoder leftFrontEncoder;
  private RelativeEncoder rightFrontEncoder;
  public void driveTank(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }
  public RobotDrive() {
    SparkMaxConfig config = new SparkMaxConfig();

    leftMaster = new SparkMax(DriveConstants.LEFT_MASTER_ID, MotorType.kBrushed);
    leftSlave = new SparkMax(DriveConstants.LEFT_SLAVE_ID, MotorType.kBrushed);
    rightMaster = new SparkMax(DriveConstants.RIGHT_MASTER_ID, MotorType.kBrushed);
    rightSlave = new SparkMax(DriveConstants.RIGHT_SLAVE_ID, MotorType.kBrushed);

    RelativeEncoder leftFrontEncoder = leftMaster.getEncoder();
    RelativeEncoder rightFrontEncoder = rightMaster.getEncoder();

    drive = new DifferentialDrive(leftMaster, rightMaster);

    leftMaster.setCANTimeout(250);
    rightMaster.setCANTimeout(250);
    leftSlave.setCANTimeout(250);
    rightSlave.setCANTimeout(250);

    config.voltageCompensation(12);
    config.smartCurrentLimit(DriveConstants.DRIVE_MOTOR_CURRENT_LIMIT);

    config.follow(leftMaster);
    leftSlave.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    config.follow(rightMaster);
    rightSlave.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    config.disableFollowerMode();
    rightMaster.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    config.inverted(true);
    leftMaster.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  
    rightFrontEncoder.setPosition(0);
    leftFrontEncoder.setPosition(0);




}


  @Override
  public void periodic() {
            SmartDashboard.putNumber("Right encoder", leftFrontEncoderValue());
            SmartDashboard.putNumber("Left encoder", leftFrontEncoderValue());
}
public double leftFrontEncoderValue() {
    return leftFrontEncoder.getPosition();
}

public double rightFrontEncoderValue() {
    return rightFrontEncoder.getPosition();
}
            
            public void tankMode(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, -rightSpeed);
  }

  public void stop(){
    drive.stopMotor();
}


}
