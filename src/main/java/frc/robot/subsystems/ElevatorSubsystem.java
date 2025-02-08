package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.Encoder;


public class ElevatorSubsystem extends SubsystemBase{
    private final SparkMax m_leftLift = new SparkMax(ElevatorConstants.kLeftLiftCanID, MotorType.kBrushless);
    private final SparkMax m_rightLift = new SparkMax(ElevatorConstants.kRightLiftCanID, MotorType.kBrushless);

    public ElevatorSubsystem() {
        SparkMaxConfig leftLiftConfig = new SparkMaxConfig();
        SparkMaxConfig rightLiftConfig = new SparkMaxConfig();

        leftLiftConfig
            .smartCurrentLimit(ElevatorConstants.kElevatorMotorCurrentLimit)
            .closedLoopRampRate(0.125);
        rightLiftConfig
            .smartCurrentLimit(ElevatorConstants.kElevatorMotorCurrentLimit)
            .closedLoopRampRate(0.125)
            .inverted(true);

        m_leftLift.configure(leftLiftConfig, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

        m_rightLift.configure(rightLiftConfig, ResetMode.kResetSafeParameters, 
        PersistMode.kPersistParameters);
    }

    public void elevatorUp() {
        m_leftLift.set(ElevatorConstants.kElevatorSpeed);
        m_rightLift.set(ElevatorConstants.kElevatorSpeed);
    }

    public void elevatorDown() {
       m_leftLift.set(-ElevatorConstants.kElevatorSpeed);
       m_rightLift.set(-ElevatorConstants.kElevatorSpeed); 
    }

    public void stopElevator() {
        m_leftLift.stopMotor();
        m_rightLift.stopMotor();
    }
}
