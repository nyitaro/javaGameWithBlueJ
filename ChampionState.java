import java.io.*;
/**
 * ChampionState class is an enum type of class based on the state of Champions
 * It allows the represantion of a fixed set of constant fields:
 * FORHIRE, ACTIVE,RESTING and DEAD 
 * The ChampionState classs aids in the creation of new Champion object
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public enum ChampionState implements Serializable
{
    FORHIRE(" For hire"), ACTIVE(" Active"),RESTING(" Resting"),DEAD (" Dead");
    private String state;
    
    /**
     * Constructor for ChampionSate
     * @param state String st as a parameter
     */
    private ChampionState(String st)
    {
        state = st;
    }
    
    /**
     * Method which return a string represantion of state.
     * FORHIRE(" For hire"), ACTIVE(" Active"),RESTING(" Resting"),DEAD (" Dead"); 
     */
    public String toString()
    {
        return state;
    }
}
