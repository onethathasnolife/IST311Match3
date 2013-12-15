
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
	private JButton unpauseBTN,returnBTN;
        
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
            
            unpauseBTN = new JButton("Unpause Game");
            returnBTN = new JButton("Return to main menu");
	
            unpauseBTN.addActionListener(this);
            returnBTN.addActionListener(this);
	
            add(unpauseBTN);
            add(returnBTN);
		
            setVisible(true);	
	} // PauseMenuUI : Constructor 
        
	@Override
      /**
	 * Handles all information related to button presses
	 * @param event the event that can perform an action
	 */
	public void actionPerformed(ActionEvent e){
            if(e.getSource() == unpauseBTN){
                setVisible(false);
                pauseGamePanel.setVisible(true);
            } // if : 
            if(e.getSource() == returnBTN){
                setVisible(false);
		pauseGamePanel.setVisible(false);
                this.dispose();
		mainMenuPanel = new MainMenuUI();
            } // if :
	} // actionPerformed
 
} // PauseMenuUI
