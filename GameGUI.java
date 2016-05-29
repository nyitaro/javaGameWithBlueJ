import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GameGUI class is the graphical user interface for CODE
 * It creates the frames, menu bars and buttons with action performers that enable the user
 * to play the CODE game
 * 
 * @author Eduardo Herrera (14092594), Nyima Sherpa (14079534)
 * @version (V.6 04/03/2014)
 */
public class GameGUI
{
    private Game test = new Player("Hero");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();

    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel southPanel = new JPanel(new BorderLayout());
    private JPanel westPanel = new JPanel(new BorderLayout());
    private JPanel eastPanel = new JPanel(new BorderLayout());

    private JButton meetChallBtn = new JButton("Meet Challenge");
    private JButton viewStateBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");

    private JTextArea listing = new JTextArea("", 20, 27);
    private JScrollPane sp;

    /**
     * Constructor for objects of class GameGUI
     */
    public GameGUI()
    {
        makeMenuBar(myFrame);
        //         myFrame.pack();
        makeFrame();
        //         myFrame.setVisible(true);
    }

    /**
     * Menu bar set up using Jframe
     * This is where the players menu option is created
     */
    public void makeMenuBar(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);

        JMenu championsBar = new JMenu("Champions");
        menuBar.add(championsBar);

        JMenuItem listAllChampions = new JMenuItem("List all champions ");
        listAllChampions.addActionListener(new listAllChampions()); 
        championsBar.add(listAllChampions);

        JMenuItem listMyArmy = new JMenuItem("List my army");
        listMyArmy.addActionListener(new listMyArmy()); 
        championsBar.add(listMyArmy);

        JMenuItem hireChampion = new JMenuItem("Hire champion");
        hireChampion.addActionListener(new hireChampion()); 
        championsBar.add(hireChampion);

        JMenuItem dismissChampion = new JMenuItem("Dismiss champion");
        dismissChampion.addActionListener(new dismissChampion());
        championsBar.add(dismissChampion);

        JMenuItem restorChampion = new JMenuItem("Restore champion");
        restorChampion.addActionListener(new restoreChampion());
        championsBar.add(restorChampion);

        JMenuItem viewChampion = new JMenuItem("View champion");
        viewChampion.addActionListener(new viewChampion());
        championsBar.add(viewChampion);
    }

    public void makeFrame()
    {
        contentPane.setLayout(new BorderLayout());

        listing.setVisible(false);//text area listing is always there but set to false unless set true in methods

        sp = new JScrollPane(listing);
        //         JPanel westPanel = new JPanel(new GridLayout(4,0,0,0));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(sp, BorderLayout.CENTER);

        /**
         * Clear and Quit buttons on the sounth end of the content pane
         */
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(0,2));
        southPanel.add(clearBtn);
        southPanel.add(quitBtn);
        clearBtn.setVisible(false);
        quitBtn.setVisible(true);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        /**
         * Challenge button, view state button, meet challenge button and quit button 
         */
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(0,2));
        northPanel.add(meetChallBtn);
        northPanel.add(viewStateBtn);
        meetChallBtn.setVisible(true);

        viewStateBtn.setVisible(true);
        contentPane.add(northPanel, BorderLayout.NORTH);

        clearBtn.addActionListener(new clearButton());
        viewStateBtn.addActionListener(new viewStateButton());
        meetChallBtn.addActionListener(new meetChallengeButton());
        quitBtn.addActionListener(new quitButton());

        myFrame.pack();
        myFrame.setVisible(true);

    }

    /**
     * Listens to the action listener and when true implements the getAllChampionsForHire() method from 
     * an instance of an player class called Test and then puts the string value of that method to 
     * the Java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     * 
     *
     */
    private class listAllChampions implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String s = test.getAllChampionsForHire();
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true implements the getArmy() method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     * 
     *
     */
    private class listMyArmy implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String s = test.getArmy();
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true implements the hireChampion(champName) method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     * 
     */
    private class hireChampion implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String champName = JOptionPane.showInputDialog("Champion's name to be hired :  ");
            String s = test.hireChampion(champName);
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true creates a string variable called champName where it is set java dialog to ask the user for the name 
     * of the champion to be dismissed - then it implements the dismissChampion(champName) method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     */
    private class dismissChampion implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String champName = JOptionPane.showInputDialog("Champion's name to be dismiss :  ");
            String s = "";
            if(test.dismissChampion(champName) == true)
            {
                 s = "Champion has been dismissed";
            }
            else
            {
                 s = "Champion has not been dismissed";
            }
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);

        }
    }

    /**
     * Listens to the action listener and when true creates a string variable called champName where it is set java dialog to ask the user for the name 
     * of the champion to be restored after meeting the challenges - then it implements the restoreChampion(champName) method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     */
    private class restoreChampion implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String champName = JOptionPane.showInputDialog("Champion's name to be hired :  ");
            String s = "";
            if(test.restoreChampion(champName) == true)
            {
                 s = "Champion has been restored";
            }
            else
            {
                 s = "Champion has not been restored";
            }
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true creates a string variable called champName where it is set java dialog to ask the user for the name 
     * of the champion to be restored after meeting the challenges - then it implements the getChampion(champName) method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     */
    private class viewChampion implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String champName = JOptionPane.showInputDialog("Champion's name to be viewed :  ");
            String s = "" + test.getChampion(champName);
            listing.setText(s);
            listing.setVisible(true);
            clearBtn.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true creates a int variable called challengeNo where it is set java dialog to ask the user for the challenge ID 
     * of the challenged to be met against  - then it implements the meetChallenge(challengeNoe) method from an instance of a player 
     * class called test and sets the string into a java text area called listing and sets the visibility of that area to 
     * true and enables the clear button visible to true
     */
    private class meetChallengeButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String challengeNo = JOptionPane.showInputDialog("Challenge's ID no to be met :  ");
            int num = Integer.parseInt(challengeNo);
            String s = test.meetChallenge(num);
            listing.setText(s);
            listing.setVisible(true);
        }
    }

    /**
     * Listens to the action listener and when true - sets the visiblity of that particular java text are to false and makes itself false along side as well
     */
    private class clearButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(false);
            clearBtn.setVisible(false);
        }
    }

    /**
     * Listens to the action listener and when true creates a int variable called answer where it is set java dialog to ask the user to confirm whether
     * they want to quit the game or not and then if statement that implememnts what to do if the input is true or false based on the answer.
     * then it sets the text/button are visibilty to false.
     */
    private class quitButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int answer = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to tquit?" , "Finish" , 
                    JOptionPane.YES_NO_OPTION);

            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            listing.setVisible(false);
            clearBtn.setVisible(false);
        }
    }

    /**
     * Listens to the action listener and when true creates a String variable s where then it is set to toString() and put then put into a java text area called listing and 
     * set true
     * */
    private class viewStateButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s = test.toString();
            listing.setText(s);
            listing.setVisible(true);
        }

    }

}

