
package matchesinspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 * 
 * Contains all information related to the main menu UI
 *
 */
public class MainMenuUI extends JFrame implements ActionListener{
     
    private GameUI gameUI;
    private JPanel mainMenuPanel;
    private JButton newBTN;
    private JButton loadBTN;
    private JButton settingsBTN;
    private JLabel mylabel;
    
    /**
    * Constructor, sets up main information of the UI
    */
    public MainMenuUI(){
	System.out.println("MainMenuUI - Building");
        
        this.initializeComponents();
	this.setVisible(true);
        this.setSize(450,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        System.out.println("MainMenuUI - Running");
    } // MainMenuUI : Constructor
    /**
     * Initializes the individual components of the Main Menu
     */
    public void initializeComponents(){
        setTitle("MATCHES..........IN SPACE!!!!");
        setLocationRelativeTo(null);
        newBTN      = new JButton("New Game");
	loadBTN     = new JButton("Load Game");
	settingsBTN = new JButton("Instructions");
	
        mainMenuPanel = new JPanel(new GridLayout(3,1));
        mainMenuPanel.add(newBTN, BorderLayout.NORTH);
	mainMenuPanel.add(loadBTN, BorderLayout.CENTER);
	mainMenuPanel.add(settingsBTN, BorderLayout.SOUTH);
        
	newBTN.addActionListener(this);
	loadBTN.addActionListener(this);
	settingsBTN.addActionListener(this);
        
        this.add(mainMenuPanel);
    } // initializeComponents
    /**
     * Handles information to button presses on this UI
     * @param event set of event that can have an action
     */
    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
    
        if(obj == newBTN){
            gameUI = new GameUI();
            this.setVisible(false);
            this.dispose();
        } // if : newBTN
        if(obj == loadBTN){
            LoadGame.LoadPieces();
            if(LoadGame.loaded){
                gameUI = new GameUI(true);
                this.setVisible(false);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(mainMenuPanel, "No Saves Found");
            }
        } // if : loadBTN
        if(obj == settingsBTN){
            JOptionPane.showMessageDialog(mainMenuPanel, "Move the pieces on the board by clicking on two adjacent pieces.\n\nControls:\np - Pause Game\nq - Force Quit");
        } // if : settingsBTN
    } // actionPerformed

} // MainMenuUI
