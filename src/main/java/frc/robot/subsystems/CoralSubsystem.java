package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralSubsystem extends SubsystemBase{

    private final SparkMax m_rollerTop = new SparkMax(31, MotorType.kBrushless);
    private final SparkMax m_rollerBottom = new SparkMax(32, MotorType.kBrushless);

    public CoralSubsystem() {

        SparkMaxConfig topMaxConfig = new SparkMaxConfig();
        SparkMaxConfig bottomMaxConfig = new SparkMaxConfig();
        
        topMaxConfig
            .smartCurrentLimit(20)
            .closedLoopRampRate(0.125);

        bottomMaxConfig
            .smartCurrentLimit(20)
            .closedLoopRampRate(0.125)
            .inverted(true);

        m_rollerTop.configure(topMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rollerBottom.configure(bottomMaxConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
 
    /**
     * Spins the motors to move the coral in
     * @param speed Speed at which to spin the motors
     */
    public void moveIn(double speed) {
        m_rollerTop.set(speed);
        m_rollerBottom.set(speed);
    }

    public void moveOut(double speed) {
        m_rollerTop.set(-speed);
        m_rollerBottom.set(-speed);
    }

    public void stopMotion() {
        m_rollerTop.stopMotor();
        m_rollerBottom.stopMotor();
    }
    
}
