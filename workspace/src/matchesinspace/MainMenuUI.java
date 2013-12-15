
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
     
    private OptionMenuUI options;
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
        
        System.out.println("MainMenuUI - Running");
    } // MainMenuUI : Constructor
    
    public void initializeComponents(){
        windowSetup();
        
        newBTN      = new JButton("New Game");
	loadBTN     = new JButton("Load Game");
	settingsBTN = new JButton("Settings");
	
        mainMenuPanel = new JPanel(new BorderLayout());
        mainMenuPanel.setSize(300, 300);
        mainMenuPanel.add(newBTN, BorderLayout.NORTH);
	mainMenuPanel.add(loadBTN, BorderLayout.CENTER);
	mainMenuPanel.add(settingsBTN, BorderLayout.SOUTH);
        
	newBTN.addActionListener(this);
	loadBTN.addActionListener(this);
	settingsBTN.addActionListener(this);
        
        this.add(mainMenuPanel);
    } // initializeComponents
    
    private void windowSetup(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth  = screenSize.width;
        setSize(screenWidth,screenHeight);
        setLocationByPlatform(true);
        setTitle("MATCHES..........IN SPACE!!!!");
        setLayout(new BorderLayout());
        setDefaultCloseOperation (EXIT_ON_CLOSE);   
    } // windowSetup
    
    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
    
        if(obj == newBTN){
            gameUI = new GameUI();
            this.setVisible(false);
        } // if : newBTN
        if(obj == loadBTN){
        
        } // if : loadBTN
        if(obj == settingsBTN){
            options = new OptionMenuUI();
            this.setVisible(false);
        } // if : settingsBTN
    } // actionPerformed

} // MainMenuUI
