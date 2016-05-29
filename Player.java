import java.util.*;
import java.io.*;
/**
 * Player class allows the creation of new players
 * Player class also implements the Game interface
 * The player class contains all the methods for challenges and champions that are used by the player to play the CODE game
 * This class also contains save game and restore game methods that allow the player to save the state of the game 
 * by creating a save file.
 * This class also provides the player readChampions(filename) which allows the reading of textfile to read Champions objects.
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */

public class Player implements Game
{
    private String name;
    private int treasury;
    private HashMap<String,Champions> allChampions = new HashMap<String, Champions>();
    private HashMap<Integer,Challenge> allChallenges = new HashMap<Integer,Challenge>();

    private HashMap<String,Champions> army = new HashMap<String, Champions>();

    //**************** DiscEarth ************************** 
    /** Constructor requires the name of the player
     * @param player is the name of the player
     */  
    public Player(String player)
    {
        name = player;
        treasury = 1000;
        setupChampions();
        setupChallenges();
    }

    /** Constructor requires the name of the player and the
     * name of the file storing champions
     * @param player is the name of the player
     * @param filename name of file storing champions
     */  
    public Player(String player, String filename)
    {
        name = player;
        treasury = 1000;       
        readChampions(filename); // read from text file
        //comment for testing Task 5 
        //         setupChallenges();
        // uncomment for testing Task 5
        readChallenges();
    }

    /**Returns the name of the player 
     * @return is the name of the player 
     **/ 
    public String getName()
    {
        return name;
    }

    /**Returns a String representation of the state of the game,
     * including the name of the player, state of the treasury,whether 
     * player has lost or is still OK, and the champions in the army 
     * (or, "No champions" if army is empty)
     * @return a String representation of the state of the game,
     * including the name of the player, state of the treasury, whether 
     * player has lost or is still OK, and the champions in the army 
     * (or, "No champions" if army is empty)
     **/
    public String toString()
    {
        String s = "\nPlayer: " + name ;
        s = s + "\nTreasury: " + treasury;        
        if (hasLost())
        {
            s = s + "\nYou have lost \n" ;
        }
        else
        {
            s = s + "\nYou are OK \n" ;
        }
        // add the army to this String, or "no Champions in the army"
        return s + "\nPlayers army:\n" + getArmy();
    }

    /** returns the amount of money in the treasur
     * @returns the amount of money in the treasury
     */
    public int getMoney()
    {
        return treasury;
    }

    /** returns true if treasury <=0 and the army has no champions 
     * who can be dismissed. 
     * @returns true if treasury <=0 and the army has no champions 
     * who can be dismissed. 
     */
    public boolean hasLost()
    {

        if(treasury <= 0)
        {
            for (Champions champ : this.army.values())
            {
                if(champ.getChampState() == ChampionState.ACTIVE || champ.getChampState() == ChampionState.RESTING)
                {
                    return false;
                }

                else
                {
                    return true;
                }

            }

        }  
        return false;
    }

    // ***************** Army of Champions ************************  
    /**Returns a String representation of all champions available for hire
     * @return a String representation of all champions available for hire
     **/
    public String getAllChampionsForHire()
    {   
        Collection<Champions> champs = allChampions.values();
        String s = "";
        for (Champions temp : champs)
        {
            s = s+ temp.toString();
        }
        return s;
    }

    /** Returns details of a champion with the given name
     * @return details of a champion with the given name
     **/
    public String getChampion(String name)
    {
        String s = allChampions.get(name).toString();
        return s;
    }

    // ***************** Army Champions ************************   
    /** Allows a champion to be hired for the army, if there 
     * is enough money in the treasury for their hire fee.The champion's 
     * state is set to "active"
     * @param name is the name of the champion
     * @return name and either "- not found" if champion not found,or "- cannot 
     * be hired" if champion is not for hire,already hired/dead, "- hired and 
     * avialable" if champion hired, "- not enough money" if not enough money
     * in the treasury
     **/        
    public String hireChampion(String name)
    {   
        Champions champ = allChampions.get(name);

        String s = "";

        if(treasury >= champ.getHireFee() && champ.getChampState() == ChampionState.FORHIRE && isChampion(name)== true)
        {
            army.put(champ.getName(), champ);
            champ.changeChampionState(ChampionState.ACTIVE);
            treasury= treasury - champ.getHireFee();
            s = name + " " +  "\nHas been hired and is now ready for battle";
        }
        else if (isChampion(name)== false || treasury <= 0 || champ.getChampState() == ChampionState.DEAD || army.containsKey(name))
        {
            s = name + " " + "\nCan not be hired";
        }
        return s;
    }

    /**Returns a String representation of the champions in the player's army
     * with an appropriate header, or the message "No champions hired"
     * @return a String representation of the champions in the player's army
     **/
    public String getArmy()
    {   
        Collection<Champions> champs = this.army.values();
        String s = "";
        for (Champions temp : champs)
        {
            if(temp.getChampState() != ChampionState.FORHIRE ){
                s = s+ temp.toString();
            }
        }
        return s;
    }
    //return s;

    /** Returns true if the champion with the name is in the player's army, 
     * false otherwise.
     * @param name is the name of the champion
     * @return true if the champion with the name is in the player's army, 
     * false otherwise.
     **/
    public boolean isInArmy(String name)
    {   
        return army.containsKey(name);
    }

    /** Dismisses a champion from the army and add half of their hire fee 
     * to the treasury.Champion must be active or resting.Champion should
     * now be for hire.
     * pre-condition: isInArmy(name)and is not dead
     * @param name is the name of the champion
     * @return true if dismissed, else false
     **/
    public boolean dismissChampion(String name)
    {       
        Champions champ = allChampions.get(name);
        String s = "";
        if( army.containsKey(name) && champ.getChampState() != ChampionState.DEAD)
        {
            this.army.remove(champ);
            champ.changeChampionState(ChampionState.FORHIRE);
            treasury= treasury + champ.getHireFee()/2;
            s = name + " " +  "\nHas been dismissed and is now available for hiring";
            return true;
        }
        else
        {
            s = name + " " + "\nCannot be dimsmissed";
            return false;
        }
    }

    /**Restores a champion to the army by setting their state to ACTIVE 
     * @param the name of the champion to be restored
     * @return true if restored, else false
     */
    public boolean restoreChampion(String name)
    {       
        Champions champ = allChampions.get(name);
        String s = "";

        if( army.containsKey(name) && champ.getChampState() == ChampionState.RESTING) //changed from resting
        {
            champ.changeChampionState(ChampionState.ACTIVE);
            treasury= treasury + champ.getHireFee()/2;
            s = name + " " +  "\nHas been restored and now ready for battle";
            return true;
        }
        else
        {
            s = name + " " + "\nCannot be restored";
            return false;            
        }

    }

    //**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the reference number of the challenge
     * @returns true if the reference number represents a challenge
     **/
    public boolean isChallenge(int num)
    {
        return allChallenges.containsKey(num) ;
    }

    /** Meets the challenge represented by the challenge number (or returns 
     * " - no such challenge").Find a champion from the army who can meet the 
     * challenge and return a result which is one of the following: “Challenge 
     * won by...“ – add reward to treasury, set the champion to restingand add 
     * the name of champion, “Challenge lost as no champion available” – deduct 
     * reward from treasury,“Challenge lost on skill level”- deduct reward from 
     * treasury, the champion is killed, so add "<champion name> is dead" to the 
     * return String. If the challenge is lost and there is no money left, add 
     * "You have NO money in the treasury".
     * @param challNo is the reference number of the challenge
     * @return a String showing the result of meeting the challenge
     */
    public String meetChallenge(int challNo) {
        if(!this.hasLost()){
            if(this.isChallenge(challNo)){

                Challenge challenge = this.allChallenges.get(challNo);

                //iterate through looking for first champion avaliable
                for(Champions champion: this.army.values()){

                    if(this.isInArmy(champion.getName()) && champion.canFight(challenge)){

                        //if first champion met cannot win challenge is lost 
                        if(champion.getLevel() >= challenge.getLevel()){
                            this.treasury += challenge.getReward();
                            champion.changeChampionState(ChampionState.RESTING);
                            return "Challenge Won By " + champion.getName() + "!";
                        }
                        else{
                            this.treasury -= challenge.getReward();
                            champion.changeChampionState(ChampionState.DEAD);
                            String temp = "";
                            if(this.treasury < 0)
                                temp = "You have no money!";

                            return temp +
                            "\nChallenge Lost On Skill Level!" + 
                            champion.getName() + " Is Dead!";
                        }
                    }
                }

                this.treasury -= challenge.getReward();
                String temp = "";
                if(this.treasury < 0)
                    temp = "You have no money!";

                return temp +
                "\nChallenge Lost As No Champion Available!";
            }
            return "No Such Challenge!";
        }

        //return player has lost
        return this.toString();
    }

    /** Provides a String representation of a challenge given by challenge number
     * pre-condition isChallenge(num)
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {   
        if(isChallenge(num))
        {
            return (allChallenges.get(num)).toString();
        }
        return null;
    }

    /** Provides a String representation of all requests 
     * @return returns a String representation of of all requests
     **/
    public String getAllChallenges()
    {    
        String s = "";
        Collection<Challenge> chall = allChallenges.values();

        for (Challenge temp : chall)
        {
            s = s+ "\n" + temp.toString();
        }
        return s;
    }

    //*********************** Task 1 ***********************************************
    /** Setting up Champion objects manually for testing on other classes
     **/
    private void setupChampions()
    {   
        // depends on your collections
        allChampions.put("Ganfrank", new Wizard("Ganfrank",7,true,"transmutation" ));
        allChampions.put("Rudolf", new Wizard("Rudolf", 6,true,"invisibility"));
        allChampions.put("Elblond", new Warrior("Elblond", 200, "sword"));
        allChampions.put("Flimsi", new Warrior("Flimsi", 300, "bow"));
        allChampions.put("Drabina", new Dragon("Drabina",false));
        allChampions.put("Golum", new Dragon("Golum",true));
        allChampions.put("Argon", new Warrior("Argon", 900, "mace"));
        allChampions.put("Neon", new Wizard("Neon", 2,false,"translocation"));
        allChampions.put("Xenon", new Dragon("Xenon",true));
    }

    /** Setting up Challenges objects manually for testing on other classes
     **/
    private void setupChallenges()
    {
        // depends on your collections
        allChallenges.put(1, new Challenge(ChallengeType.MAGIC,"Borg",3,100));
        allChallenges.put(2, new Challenge(ChallengeType.FIGHT,"Huns",3,120));
        allChallenges.put(3, new Challenge(ChallengeType.MYSTERY,"Ferengi",3,150));
        allChallenges.put(4, new Challenge(ChallengeType.MAGIC,"Vandals",9,200));
        allChallenges.put(5, new Challenge(ChallengeType.MYSTERY,"Borg",7,90));
        allChallenges.put(6, new Challenge(ChallengeType.FIGHT,"Goths",8,45));
        allChallenges.put(7, new Challenge(ChallengeType.MAGIC,"Franks",10,200));
        allChallenges.put(8, new Challenge(ChallengeType.FIGHT,"Sith",10,150)); 
    }
    //*******************************************************************************
    /************************ Task 5 ************************************************/
    /** reads data about champions from a text file and stores in collection of 
     * champions.Data in the file is  "comma separated" and so editable
     * @param fileName name of the file to be read
     */   
    private void readChampions(String fileName)
    { 
        try{
            BufferedReader in = new BufferedReader(new FileReader("champions.txt"));
            String champ = in.readLine();
            Champions temp = null;

            while(champ !=null)
            {
                String inp[] = champ.split(",");

                if(inp[0].equals("dragon"))
                {
                    temp = new Dragon(inp[1],Boolean.valueOf(inp[2]));
                }
                else if(inp[0].equals("warrior"))
                {
                    temp = new Warrior(inp[1],Integer.parseInt(inp[2]),inp[3]);
                }
                else if(inp[0].equals("wizard"))
                {
                    temp = new Wizard(inp[1],Integer.parseInt(inp[2]),Boolean.valueOf(inp[3]),inp[4]);
                }

                if(temp !=null)
                {
                    allChampions.put(temp.getName(), temp);
                }else{
                    break;
                }

                champ = in.readLine();

            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }   

    //************************ Task 6 **********************************
    /** reads data about challenges from an object file and stores in collection of 
     * champions.Data in the file is not editable
     * @param fileName name of the file to be read
     */
    private void readChallenges()
    {
        try{
            FileInputStream fis = new FileInputStream("challenge.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Challenge temp = (Challenge)ois.readObject();

            while(temp != null){

                allChallenges.put(temp.getChallengeID(),temp);
                temp = (Challenge)ois.readObject();
            }

            ois.close();
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // ********************   Task 6 object write/read  *********************
    /**
     * Saves the state of the game to the file with the given name 
     * @param filename the name of the file 
     */ 
    public void saveGame(String fname)
    {   // uses object serialisation 
        try
        {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream save = new ObjectOutputStream(fos);

            save.writeObject(this);

            save.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /** 
     * Restores the game from the file with the given name
     * @param filename the name of the file 
     */
    public Game restoreGame(String fname)
    {   // uses object serialisation         
        try{
            ObjectInputStream load = new ObjectInputStream(new FileInputStream(fname));

            Player player = (Player)load.readObject();
            this.allChampions = player.allChampions;
            this.allChallenges = player.allChallenges;
            this.army = player.army;
            this.treasury = player.treasury;

            return player;
        }
        catch(Exception exc){
            return null;
        }
    }

    /** 
     * Checks whether a champion is present on allChampions using the given name
     * @param name the name of the champion
     */
    private boolean isChampion(String name)
    {
        return allChampions.containsKey(name);
    }

}

