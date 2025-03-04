// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An ClimberUpCommand that uses a climb subsystem. */
public class ClimberUpCommand extends Command {
  private final ClimberSubsystem m_climber;
  boolean havecage;

  // havecage different than HaveCage
  /**
   * Runs the climber up, note that this can change
   * based on how the winch is wound.
   *
   * @param climber The subsystem used by this command.
   */
  public ClimberUpCommand(ClimberSubsystem climber) {
    m_climber = climber;
    havecage = false;
    // No cage in the first place
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    havecage = m_climber.IsClimberFinished();
    // havecage is now connected to IsClimberFinished
    // reach into climber and get the numbers
    m_climber.runClimber(ClimberConstants.CLIMBER_SPEED_UP);
  }

  // Called once the command ends or is interrupted.. Here we ensure the climber
  // is not
  // running once we let go of the button
  @Override
  public void end(boolean interrupted) {
    m_climber.runClimber(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (havecage) {
      return true;
      // if it is returned true, goes to isfinished, and runs the command
    } else {
      return false;
    }
  }
}