// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the manifest
 * file in the resource
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
  private CANSparkMax m_climber1;
  private CANSparkMax m_climber2;
  private CANSparkMax m_shooterArm1;
  private CANSparkMax m_shooterArm2;
  private XboxController m_controller;
  private DifferentialDrive m_robotDrive;
  private boolean shooterEnabled;
  private long startTime;

  public Robot() {
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any
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
    m_climber1 = new CANSparkMax(11, MotorType.kBrushed);
    m_climber2 = new CANSparkMax(12, MotorType.kBrushed);
    m_shooterArm1 = new CANSparkMax(6, MotorType.kBrushless);
    m_shooterArm2 = new CANSparkMax(7, MotorType.kBrushless);
    m_controller = new XboxController(0);
    shooterEnabled = false;
    m_leftDriveFollower.follow(m_leftDrive);
    m_rightDriveFollower.follow(m_rightDrive);
    m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
    CameraServer.startAutomaticCapture();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    startTime = System.currentTimeMillis();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    long timeSinceStart = System.currentTimeMillis() - startTime;
    // 2-NOTE CENTER AUTO
    // if (timeSinceStart < 500) {
    // m_shooterArm1.set(-0.2);
    // m_shooterArm2.set(0.2);

    // } else if (timeSinceStart > 500 && timeSinceStart < 1000) {
    // m_shooterArm1.set(0.0);
    // m_shooterArm2.set(0.0);
    // m_shooter1.set(-0.85);
    // m_shooter2.set(-0.85);

    // } else if (timeSinceStart > 1000 && timeSinceStart < 2000) {
    // m_intake.set(-0.85);

    // } else if (timeSinceStart > 2000 && timeSinceStart < 4500) {
    // m_shooter1.set(0.0);
    // m_shooter2.set(0.0);
    // m_robotDrive.arcadeDrive(0.0, 0.5);

    // } else if (timeSinceStart > 4500 && timeSinceStart < 4700) {
    // m_robotDrive.stopMotor();

    // } else if (timeSinceStart > 4700 && timeSinceStart < 7200) {
    // m_intake.set(0.0);
    // m_robotDrive.arcadeDrive(0.0, -0.5);

    // } else if (timeSinceStart > 7200 && timeSinceStart < 7500) {
    // m_robotDrive.stopMotor();
    // m_shooter1.set(0.5);
    // m_shooter2.set(0.5);
    // m_intake.set(0.5);

    // } else if (timeSinceStart > 7500 && timeSinceStart < 8000) {
    // m_shooter1.set(-0.85);
    // m_shooter2.set(-0.85);
    // m_intake.set(0.0);

    // } else if (timeSinceStart > 8000 && timeSinceStart < 9000) {
    // m_intake.set(-0.85);

    // } else if (timeSinceStart > 9000 && timeSinceStart < 11500) {
    // m_shooter1.set(0.0);
    // m_shooter2.set(0.0);
    // m_intake.set(0.0);
    // m_robotDrive.arcadeDrive(0.0, 0.5);

    // } else if (timeSinceStart > 11500) {
    // m_robotDrive.stopMotor();
    // }
    // ONE NOTE FAR SIDE
    if (timeSinceStart < 500) {
      m_shooterArm1.set(-0.2);
      m_shooterArm2.set(0.2);

    } else if (timeSinceStart > 500 && timeSinceStart < 1000) {
      m_shooterArm1.set(0.0);
      m_shooterArm2.set(0.0);
      m_shooter1.set(-0.85);
      m_shooter2.set(-0.85);

    } else if (timeSinceStart > 1000 && timeSinceStart < 2000) {
      m_intake.set(-0.85);

    } else if (timeSinceStart > 2000 && timeSinceStart < 9000) {
      m_shooter1.set(0.0);
      m_shooter2.set(0.0);
      m_robotDrive.arcadeDrive(0.0, 0.5);

    } else if (timeSinceStart > 9000) {
      m_robotDrive.stopMotor();
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_controller.getRightX(), -m_controller.getLeftY());

    // INTAKE
    if (m_controller.getLeftBumper()) {
      m_intake.set(-0.7);
    }
    if (m_controller.getXButton()) {
      m_intake.set(0.8);
    }
    if (!m_controller.getLeftBumper() && !m_controller.getXButton()) {
      m_intake.set(0.0);
    }

    // SHOOTER
    if (m_controller.getRightBumperPressed()) {
      shooterEnabled = !shooterEnabled;
    }
    if (shooterEnabled) {
      m_shooter1.set(-0.85);
      m_shooter2.set(-0.85);

    } else {
      m_shooter1.set(0.0);
      m_shooter2.set(0.0);
    }

    // SHOOTER ARM
    if (m_controller.getYButton()) {
      m_shooterArm1.set(0.35);
      m_shooterArm2.set(-0.35);
    }
    if (m_controller.getAButton()) {
      m_shooterArm1.set(-0.2);
      m_shooterArm2.set(0.2);
    }
    if (!m_controller.getYButton() && !m_controller.getAButton()) {
      m_shooterArm1.set(0.0);
      m_shooterArm2.set(0.0);
    }

    // CLIMBERS
    if (m_controller.getRightTriggerAxis() > 0.1) {
      m_climber1.set(1.0);
    }
    if (m_controller.getLeftTriggerAxis() > 0.1) {
      m_climber1.set(-1.0);
    }
    if (m_controller.getRightTriggerAxis() <= 0.1 && m_controller.getLeftTriggerAxis() <= 0.1) {
      m_climber1.set(0.0);
    }
    if (m_controller.getPOV() == 180) {
      m_climber2.set(1.0);
    }
    if (m_controller.getPOV() == 0) {
      m_climber2.set(-1.0);
    }
    if (m_controller.getPOV() != 180 && m_controller.getPOV() != 0) {
      m_climber2.set(0.0);
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
