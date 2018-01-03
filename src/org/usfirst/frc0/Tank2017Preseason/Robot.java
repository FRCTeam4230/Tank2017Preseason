// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc0.Tank2017Preseason;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc0.Tank2017Preseason.commands.*;
import org.usfirst.frc0.Tank2017Preseason.subsystems.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Preferences prefs;
    Command autonomousCommand;
    public static boolean arcade = true;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        prefs = Preferences.getInstance();
        arcade = prefs.getBoolean("Arcade?", false);
        
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
         
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        arcade = prefs.getBoolean("Arcade?", false);
        RobotMap.driveTrainL1.changeControlMode(TalonControlMode.Speed);
        RobotMap.driveTrainR1.changeControlMode(TalonControlMode.Speed);
       // RobotMap.driveTrainL1.setVoltageRampRate(Robot.prefs.getDouble("Ramp", 12.0));
        //RobotMap.driveTrainR1.setVoltageRampRate(Robot.prefs.getDouble("Ramp", 12.0));
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
       // tune(RobotMap.driveTrainR1, 600.0, prefs);
       
    }
    
    private void tune (CANTalon motor, double rpm, Preferences pref) {
    	double yStick = -oi.controller1.getRawAxis(1);
    	double targetSpeed = yStick * rpm;
    	double motorOutput = motor.getOutputVoltage() / motor.getBusVoltage();
    	if (oi.controller1.getRawButton(2)) {
    		targetSpeed = yStick * rpm;
    		motor.changeControlMode(TalonControlMode.Speed);
    		motor.set(targetSpeed);
    	} else {
    		motor.changeControlMode(TalonControlMode.PercentVbus);
    		motor.set(yStick);
    	}
    	
    	motor.setF(pref.getDouble("F", 0.0));
    	motor.setP(pref.getDouble("P", 0.0));
    	motor.setI(pref.getDouble("I", 0.0));
    	motor.setD(pref.getDouble("D", 0.0));
    	SmartDashboard.putNumber("Motor Output", motorOutput);
    	SmartDashboard.putNumber("Speed", motor.getSpeed());
    	SmartDashboard.putNumber("Target Speed", targetSpeed);
    	SmartDashboard.putNumber("Loop Error", motor.getClosedLoopError());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
