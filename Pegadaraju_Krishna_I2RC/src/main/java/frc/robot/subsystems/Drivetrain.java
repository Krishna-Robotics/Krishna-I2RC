// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.util.Units;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Drivetrain extends SubsystemBase {
  private final WPI_TalonSRX leftDriveTalon; 
  private final WPI_TalonSRX rightDriveTalon; 
  private AHRS navx = new AHRS(SPI.Port.kMXP);

  private ShuffleboardTab DTTab = Shuffleboard.getTab("Dirvetrain");
  private GenericEntry LeftVoltage = DTTab.add("Left Volatage",0.0).getEntry();
private GenericEntry RightVoltage = DTTab.add("Right Volatage",0.0).getEntry();

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    rightDriveTalon = new WPI_TalonSRX(Constants.DrivetrainPort.rightDriveTalon);
    leftDriveTalon = new WPI_TalonSRX(Constants.DrivetrainPort.leftDriveTalon);
    rightDriveTalon.setNeutralMode(NeutralMode.Coast);
    leftDriveTalon.setNeutralMode(NeutralMode.Coast);

    rightDriveTalon.setInverted(true);
    leftDriveTalon.setSensorPhase(true);
        rightDriveTalon.setSensorPhase(true);
        leftDriveTalon.configFactoryDefault();
        leftDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        rightDriveTalon.configFactoryDefault();
        rightDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    
  }
  public void tankDrive(double leftspeed, double rightspeed){
    leftDriveTalon.set(leftspeed);
    rightDriveTalon.set(rightspeed);
  }



    public double getAngle(){
      return navx.getAngle();
      
    }

    public void reset(){
      navx.reset();

  }

  public double getTicks(){
    return(leftDriveTalon.getSelectedSensorPosition(0) + rightDriveTalon.getSelectedSensorPosition(0))/2.0;
  } 
public double getMeters(){
  return(Units.inchesToMeters(6)*Math.PI)/(4096*getTicks());

}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler fiarun
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
