
package matchesinspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 * 
 * Contains all information related to handling the pause menu UI
 *
 */

public class PauseMenuUI extends JFrame implements ActionListener {
	private JPanel pauseGamePanel;
	private MainMenuUI mainMenuPanel;
	private JPanel pauseMenuPanel;
	private JButton resumeBTN,savequitBTN;
        
	/**
	 * Constructor, sets up information related to the UI
	 * @param game The current state of the given UI, to return 
	 */
	public PauseMenuUI(JPanel game){
            pauseGamePanel = game;
            pauseMenuPanel = new JPanel();
            setTitle("MATCHES..........IN SPACE!!!!");
            setSize(400,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(2,1));
            setLocationRelativeTo(null);
            
            resumeBTN = new JButton("Resume");
            savequitBTN = new JButton("Save & Quit");
	
            resumeBTN.addActionListener(this);
            savequitBTN.addActionListener(this);
	
            add(resumeBTN);
            add(savequitBTN);
            
            setVisible(true);	
	} // PauseMenuUI : Constructor 
        
	@Override
      /**
	 * Handles all information related to button presses
	 * @param event the event that can perform an action
	 */
	public void actionPerformed(ActionEvent e){
            if(e.getSource() == resumeBTN){
                setVisible(false);
                pauseGamePanel.setVisible(true);
            } // if : 
            if(e.getSource() == savequitBTN){
                SaveGame.Save();
                System.exit(0);
            } // if :
	} // actionPerformed
 
} // PauseMenuUI
