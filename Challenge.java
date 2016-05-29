import java.io.*;
import java.util.*;
/**
 * Challenge class allows the creation of new challenges.
 * This class provides the fields challengeID, challengerName, level, reward and type
 * This 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public class Challenge implements Serializable
{
    //Instance variable 
    private static int challengeID =1;
    private int challNo;
    private String challengerName;
    private int level;
    private double reward;
    private ChallengeType challengeType;

    /**
     * Constructor for objects of the class Challenge 
     * @param challID champion name
     * @param ChallengeType challengeType 
     * @param challengerNam challenger name
     * @param lvl challenge level
     * @param rwd challenge reward
     */
    public Challenge(ChallengeType challengeType , String challengerNam, int lvl, double rwd)
    {
        challNo =    challengeID++;
        this.challengeType = challengeType;
        challengerName = challengerNam;
        level = lvl;
        reward = rwd;
    }
    
    /**
     * Returns the value of challenge ID no in the form of int
     */
     public int getChallengeID()
    {
        return challNo;
    }
    
     /**
     * Returns the value of challenge name in the form of String
     */
    public String challengerName()
    {
        return challengerName;
    }
    
     /**
     * Returns the value of challenge level no in the form of int
     */
    public int getLevel()
    {
        return level;
    }
    
     /**
     * Returns the value of reward for winning the challenge in the form of double 
     * as the fee assigned for Warrior is up to the user end ( they may input a value of double and if we 
     * used int - it will not work properly). Therefore using the double instead of integer is more efficient
     */
    public double getReward()
    {
        return reward;
    }
    
    /**
     * Returns the challenge type of the challenge from the class Challenge Type (enumaration of the challenge type)
     */
    public ChallengeType getChallengeType()
    {
        return challengeType;
    }
    
    /**
     * Prints out the fields that makes up the challenge constructor in the string form
     * so that it can be used in a more efficient manner by other classes.
     */
      public String toString()
    {

        return  "\nChallenge ID: " + " " + challNo
        + "\nChallenge type: " + " " + challengeType
        + "\nChallenger name: " + " " + challengerName
        + "\nChallenger level: " + " " + level 
        + "\nReward: " + " " + reward;
    }
}