import java.io.*;
import java.util.*;
/**
 * ChallengeSetup class allows the addition of new challenge objects using a terminal window.
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public class ChallengeSetup implements Serializable
{
    public static void main(String[] args) throws IOException
    {
        Challenge c = null;
        ChallengeType type = null;
        FileOutputStream fis = new FileOutputStream("challenge.txt");
        ObjectOutputStream ois = new ObjectOutputStream(fis);
        Scanner scan = new Scanner(System.in);

        String input = "quit";
        boolean continueC = true;
        while (continueC)
        {
           
            System.out.println("Please choose a challenge type : MAGIC / FIGHT / MYSTERY"); 
            type = ChallengeType.valueOf(scan.next().toUpperCase());

            System.out.println("Please enter the Challenger name "); 
            String name = scan.next();

            System.out.println("Please enter the Challenge level "); 
            int level = scan.nextInt();

            System.out.println("Please enter the reward for the challenge "); 
            double reward = scan.nextInt();
            scan.nextLine();

            c = new Challenge(type,name,level,reward);
            ois.writeObject(c);

            System.out.println("\n");
            System.out.println("Challenge added sucessfully!");
            System.out.println("\n");
            System.out.println("Press any key and enter to continue adding Challenges");
            System.out.println("Type q to quit");
            if(scan.nextLine().equals("q")) continueC = false;

        }

        ois.writeObject(null);
        System.exit(0);

    }
}
