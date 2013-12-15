
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
	private JPanel mainui;
	private MainMenuUI homeui;
	private JPanel mypanel;
	private JButton mybutton1,mybutton2;
        
	/**
	 * Constructor, sets up information related to the UI
	 * @param game The current state of the given UI, to return 
	 */
	public PauseMenuUI(JPanel game){
            mainui = game;
            mypanel = new JPanel();
            setTitle("MATCHES..........IN SPACE!!!!");
            setSize(400,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
            setLayout(new GridLayout(2,1));
	
            mybutton1 = new JButton("Unpause Game");
            mybutton2 = new JButton("Return to main menu");
	
            mybutton1.addActionListener(this);
            mybutton2.addActionListener(this);
	
            add(mybutton1);
            add(mybutton2);
		
            setVisible(true);	
	} // PauseMenuUI : Constructor 
        
	@Override
      /**
	 * Handles all information related to button presses
	 * @param event the event that can perform an action
	 */
	public void actionPerformed(ActionEvent e){
            if(e.getSource() == mybutton1){
                setVisible(false);
                mainui.setVisible(true);
            } // if : 
            if(e.getSource() == mybutton2){
                setVisible(false);
		mainui.setVisible(false);
		homeui = new MainMenuUI();
            } // if :
	} // actionPerformed
 
} // PauseMenuUI
