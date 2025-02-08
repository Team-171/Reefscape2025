package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.CoralConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralSubsystem extends SubsystemBase{

    private final SparkMax m_rollerTop = new SparkMax(CoralConstants.kCoralMotorCanID, MotorType.kBrushless);

    public CoralSubsystem() {

        SparkMaxConfig topMaxConfig = new SparkMaxConfig();
        
        topMaxConfig
            .smartCurrentLimit(20)
            .closedLoopRampRate(0.125);

        m_rollerTop.configure(topMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
 
    /**
     * Spins the motors to move the coral in
     * @param speed Speed at which to spin the motors
     */
    public void moveIn(double speed) {
        m_rollerTop.set(speed);
    }

    public void moveOut(double speed) {
        m_rollerTop.set(-speed);
    }

    public void stopMotion() {
        m_rollerTop.stopMotor();
    }
    
}
