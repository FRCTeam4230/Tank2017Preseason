// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc0.Tank2017Preseason.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc0.Tank2017Preseason.Robot;
import org.usfirst.frc0.Tank2017Preseason.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class AutonomousCommand extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonomousCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.motionProfileL.reset();
    	RobotMap.motionProfileR.reset();
    	RobotMap.driveTrainL1.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.driveTrainR1.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.motionProfileL.startMotionProfile();
    	RobotMap.motionProfileR.startMotionProfile();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.motionProfileL.control();
    	RobotMap.motionProfileR.control();
    	
    	CANTalon.SetValueMotionProfile setOutputL = RobotMap.motionProfileL.getSetValue();
    	CANTalon.SetValueMotionProfile setOutputR = RobotMap.motionProfileR.getSetValue();
    	
    	RobotMap.driveTrainL1.set(setOutputL.value);
    	RobotMap.driveTrainR1.set(setOutputR.value);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
