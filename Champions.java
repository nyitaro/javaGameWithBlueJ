import java.io.*;
/**
 * Champions class allows the creation of new champions.
 * This is an abstract class which provides the name, level and
 * fee fields for creation of champions
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public abstract class Champions implements Serializable
{
    // instance variables - replace the example below with your own
    private String name;
    public int level;
    private int hireFee;
    private ChampionState championState;

    /**
     * Constructor for objects of the class Champion
     * @param name champion name
     * @param lvl champions skill level
     * @param fee champions hire fee
     */
    public Champions(String name,int lvl, int fee)
    {
        this.name = name;
        level = lvl;
        hireFee = fee;
        championState = ChampionState.FORHIRE;
    }

    /**
     * Method which return the name of the Champion as a String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method which return the level of the Champion
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Method which return the hireFee of the Champion
     */
    public int getHireFee()
    {
        return hireFee;
    }

    /**
     * Method which return the Champion state form the ChampionState class
     * using the method getChamState() to get the current state of the champion
     */
    public ChampionState getChampState()
    {
        return championState;
    }

    /**
     * Method which allows the change of state of a champion
     * It uses the ChampionState class and a state parameter to change the state of the champion 
     */
    public void changeChampionState (ChampionState state)
    {
        this.championState = state;
    }

    /**
     * Abstract method which is overwrote from the subclasses of the challenge
     */
    public  abstract boolean canFight(Challenge chal);

    /**
     * To string method which shows all Champions information
     * It returns the name of the champion
     * the HireFee of the champion
     * And the state of the champion
     */
    public String toString()
    {

        return  "\nName : " + " " + name 
        + "\nLevel : " + " " + level 
        + "\nHire Fee : " + " " + hireFee
        + "\nStatus : "  + championState;
    }

}
