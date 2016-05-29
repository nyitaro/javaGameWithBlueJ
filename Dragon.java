import java.io.Serializable;
/**
 * Dragon calls allows the creation of new dragons.
 * This class inherits from the Champions class which provides the name, level and
 * fee fields for creation of dragons.
 * Addional fields are methods are added in this class which are unique to dragons such as:
 * talk as some dragons have the ability to talk and some do not.
 *
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public class Dragon extends Champions implements Serializable
{
    private boolean talk;

       /**
     * Constructor for a Dragon
     * @param name dragon name
     * @param talk  dragon ability to talk
     * Dragons skill level and hire fee are not set up by the constructor because dragons have a
     * fixed level of skill (7) and a fixed hireFee (500)
     * This is set up using the super constructor to crete the new dragons with these default values
     */
    public Dragon(String name,  boolean talk)
    {
        super(name,7,500);
        this.talk = talk;
    }

    /**
     * Method that returns a boolean value either true or false wether a Dragon has the ability to talk
     */
    public boolean canTalk()
    {
        return this.talk;
    }

    /**
     * Method which check whether a Dragon is able to meet the challenge stated on the paramenters
     * This method has several conditions 
     * A dragon is always able to meet a FIGHT type challenge only when is ACTIVE
     * A dragon is also able to meet a MYSTERY type challenge only if the dragon is able to talk 
     * and it is in an ACTIVE state
     */
    public boolean canFight(Challenge chal)
    {
        if(chal != null){
            if((chal.getChallengeType() == ChallengeType.FIGHT || (chal.getChallengeType() == ChallengeType.MYSTERY && talk)) && getChampState() == ChampionState.ACTIVE){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    /**
     * To string method which shows all dragon information
     * most of the details are retrived using a toString() method from the Champion class
     * Additionaly the talk field has been added to show if the dragon is able to talk
     */
    public String toString()
    {
        return super.toString() +
        "\nCan talk : " + talk +
        "\n";
    }

}
