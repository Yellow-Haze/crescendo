// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private CANSparkMax m_leftDrive;
  private CANSparkMax m_leftDriveFollower;
  private CANSparkMax m_rightDrive;
  private CANSparkMax m_rightDriveFollower;
  private CANSparkMax m_intake;
  private CANSparkMax m_shooter1;
  private CANSparkMax m_shooter2;
  // private PWMSparkMax m_climber1;
  // private PWMSparkMax m_climber2;
  private CANSparkMax m_shooterArm1;
  private CANSparkMax m_shooterArm2;
  private DifferentialDrive m_robotDrive;
  private XboxController m_controller;
  private Timer m_timer;
  private boolean shooterEnabled;
  private double startTime;

  public Robot() {
    //SendableRegistry.addChild(m_robotDrive, m_leftDrive);
    //SendableRegistry.addChild(m_robotDrive, m_rightDrive);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_leftDrive = new CANSparkMax(2, MotorType.kBrushed);
    m_leftDriveFollower = new CANSparkMax(3, MotorType.kBrushed);
    m_rightDrive = new CANSparkMax(4, MotorType.kBrushed);
    m_rightDriveFollower = new CANSparkMax(5, MotorType.kBrushed);
    m_intake = new CANSparkMax(10, MotorType.kBrushless);
    m_shooter1 = new CANSparkMax(8, MotorType.kBrushed);
    m_shooter2 = new CANSparkMax(9, MotorType.kBrushed);
    // m_climber1 = new PWMSparkMax(7);
    // m_climber2 = new PWMSparkMax(8);
    m_shooterArm1 = new CANSparkMax(6, MotorType.kBrushless);
    m_shooterArm2 = new CANSparkMax(7, MotorType.kBrushless);
    m_controller = new XboxController(0);
    m_timer = new Timer();
    shooterEnabled = false;

    m_leftDriveFollower.follow(m_leftDrive);
    m_rightDriveFollower.follow(m_rightDrive);

    m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    // startTime = Timer.getFPGATimestamp();
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // double time = Timer.getFPGATimestamp();
    // m_leftDrive.set(0.0);
    // m_rightDrive.set(0.0);
    // m_leftDriveFollower.follow(m_leftDrive);
    // m_rightDriveFollower.follow(m_rightDrive);
    // if(time - startTime < 1.0){
    //   m_leftDrive.set(-0.4);
    //   m_rightDrive.set(0.4);
    //   m_intake.set(-0.4);
    // } else if (time - startTime < 2.0){
    //   m_leftDrive.set(0.0);
    //   m_rightDrive.set(0.0);
    // } else if (time - startTime < 4.5){
    //   m_leftDrive.set(-0.37);
    //   m_rightDrive.set(-0.37);
    //   m_intake.set(0.0);
    // } else if (time - startTime < 4.65){
    //   m_leftDrive.set(0.0);
    //   m_rightDrive.set(0.0);
    // } else if (time - startTime < 6.0){
    //   m_shooter1.set(-0.5);
    //   m_shooter2.set(-0.5);
    // } else if (time - startTime < 7.0){
    //   m_intake.set(-0.5);
    // } else {
    //   m_leftDrive.set(0.0);
    //   m_rightDrive.set(0.0);
    //   m_intake.set(0.0);
    //   m_shooter1.set(0);
    //   m_shooter2.set(0);
    // }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_controller.getRightX(), -m_controller.getLeftY());

    // INTAKE
    if (m_controller.getLeftBumper()) {
      m_intake.set(-0.6);
      return;

    } else {
      m_intake.set(0.0);
    }
    if (m_controller.getXButton()) {
      m_intake.set(0.7);
      return;

    } else {
      m_intake.set(0.0);
    }

    // SHOOTER
    if (m_controller.getRightBumperPressed()) {
      shooterEnabled = !shooterEnabled;
    }
    if (shooterEnabled) {
      m_shooter1.set(-0.75);
      m_shooter2.set(-0.75);

    } else {
      m_shooter1.set(0.0);
      m_shooter2.set(0.0);
    }

    // SHOOTER ARM
    if (m_controller.getYButton()) {
      m_shooterArm1.set(0.2);
      m_shooterArm2.set(-0.2);
      return;

    } else {
      m_shooterArm1.set(0.0);
      m_shooterArm2.set(0.0);
    }
    if (m_controller.getAButton()) {
      m_shooterArm1.set(-0.1);
      m_shooterArm2.set(0.1);

    } else {
      m_shooterArm1.set(0.0);
      m_shooterArm2.set(0.0);
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
