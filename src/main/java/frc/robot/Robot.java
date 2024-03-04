// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftDrive = new PWMSparkMax(2);
  private final PWMSparkMax m_rightDrive = new PWMSparkMax(0);
  private final PWMSparkMax m_leftDriveFollower = new PWMSparkMax(3);
  private final PWMSparkMax m_rightDriveFollower = new PWMSparkMax(1);
  private final PWMSparkMax m_intake = new PWMSparkMax(4);
  private final PWMSparkMax m_shooter1 = new PWMSparkMax(5);
  private final PWMSparkMax m_shooter2 = new PWMSparkMax(6);
  private final PWMSparkMax m_chainArm1 = new PWMSparkMax(7);
  private final PWMSparkMax m_chainArm2 = new PWMSparkMax(8);
  private final PWMSparkMax m_shooterArm1 = new PWMSparkMax(9);
  private final PWMSparkMax m_shooterArm2 = new PWMSparkMax(10);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftDrive::set, m_rightDrive::set);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();

  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftDrive);
    SendableRegistry.addChild(m_robotDrive, m_rightDrive);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_leftDrive.addFollower(m_leftDriveFollower);
    m_rightDrive.addFollower(m_rightDriveFollower);
    m_leftDrive.setInverted(true);
    m_rightDrive.setInverted(false);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX());

    if (m_controller.getXButton()) {
      m_intake.set(-0.25);

    } else {
      m_intake.set(0.0);
    }

    if (m_controller.getBButton()) {
      m_shooter1.set(0.5);
      m_shooter2.set(-0.5);

    } else {
      m_shooter1.set(0.0);
      m_shooter2.set(0.0);
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
