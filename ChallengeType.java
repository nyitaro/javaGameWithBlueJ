import java.io.*;
/**
 * ChallengeType class is an enum type of class based on the type of Challenge
 * It allows the represantion of a fixed set of constant fields:
 * MAGIC, FIGHT and MYSTERY
 * The ChallengeType classs aids in the creation of new Challenge object
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public enum ChallengeType implements Serializable
{
    MAGIC(" Magic"), FIGHT("Fight"), MYSTERY ("Mystery");
    private String type;
    
    /**
     * Constructor for ChallengeType
     * @param type String ty as a parameter
     */
    private ChallengeType(String ty)
    {
        type = ty;
    }
    
    /**
     * Method which return a string represantion of type.
     * MAGIC(" Magic"), FIGHT("Fight"), MYSTERY ("Mystery");
     */
    public String toString()
    {
        return type;
    }
}
