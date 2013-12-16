
package matchesinspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 
 * Shows main game information board
 *
 */

public class GameUI extends JFrame implements ActionListener, KeyListener{
    
    private JPanel gamePanel;
    private JPanel statsPanel;
    private PauseMenuUI pause;

    private JLabel textScore;
    public static JLabel score;
    private JLabel textLevel;
    public static JLabel level;
    private JLabel textCombo;
    private JLabel combo;
    private JLabel textRow;
    private JLabel row;
    private JLabel textCol;
    private JLabel col;
    private JButton glitched;
    GameHandler game;
    Clip clip;
    
    /**
    * Sets up basic information on the game board
    */
    public GameUI(){
        System.out.println("GameUI - Building UI");
        
        initializeComponents();      
	this.setVisible(true);
	addKeyListener(this);
        initializeGame();
        
        System.out.println("GameUI - UI Built");
    } // GameUI : Constructor
    
    /**
    * Used if game is loaded from text file instead
    */
    public GameUI(Boolean loaded){
        System.out.println("GameUI - Loaded - Building UI");
        
        initializeComponents();      
	this.setVisible(true);
	addKeyListener(this);
        initializeGame(loaded);
        
        System.out.println("GameUI - Loaded - UI Built");
    } // GameUI : Constructor - loaded
    /**
     * Analyzes the main information for the game
     */
    public void initializeComponents(){
        windowSetup();
        
        gamePanel   = new JPanel(new BorderLayout());
        statsPanel  = new JPanel();
        
        textScore   = new JLabel("Score");
        score       = new JLabel("0",JLabel.CENTER);
        textLevel   = new JLabel("Level");
        level       = new JLabel("1",JLabel.CENTER);
        textCombo   = new JLabel("Max Combo");
        combo       = new JLabel("0",JLabel.CENTER);
        textRow     = new JLabel("row",JLabel.CENTER);
        row         = new JLabel("-1",JLabel.CENTER);
        textCol     = new JLabel("column", JLabel.CENTER);
        col         = new JLabel("-1",JLabel.CENTER);
        
        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        statsPanel.setBorder(lineBorder);
        statsPanel.setLayout(new GridLayout(1,6));
        statsPanel.add(textScore);
        statsPanel.add(score);
        statsPanel.add(textLevel);
        statsPanel.add(level);
        statsPanel.add(textCombo);
        statsPanel.add(combo);
        
        /* Debugging Info
        statsPanel.add(textRow);
        statsPanel.add(row);
        statsPanel.add(textCol);
        statsPanel.add(col);
        */
        
        gamePanel.add(statsPanel, BorderLayout.NORTH);
        gamePanel.setBackground(null);
        
        System.out.println("GameUI - Building Game");
        game = new GameHandler(this); 
        gamePanel.add(game.getGameHandler(), BorderLayout.CENTER);
        System.out.println("GameUI - Game Running");
        try{clip = AudioSystem.getClip();}catch(Exception e){}
       
        this.add(gamePanel);
    } // initializeComponents
    /**
     * Sets up the game window size and other small information
     */  
    private void initializeGame(){
        game.initializeGame();
    } // initializeGame
    /**
     * Sets up the game window size and other small information
     */  
    private void initializeGame(Boolean loaded){
        if(loaded){
            game.initializeGame(loaded);
        } // if : loaded
    } // initializeGame - loaded
    /**
     * Sets up information based on the window of the GameUI
     */
    private void windowSetup(){
        //Toolkit kit = Toolkit.getDefaultToolkit();
        //Dimension screenSize = kit.getScreenSize();
        //double screenHeight = screenSize.height*.75;
        //double screenWidth  = screenSize.width*.75;
        int screenHeight = 647;
        int screenWidth = 800;
        setSize(screenWidth,screenHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("MATCHES..........IN SPACE!!!!");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Leaving Space",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                }
            });
    } // windowSetup
    /**
     * Sets the score
     * @param score Current score given.
     */
    public void setScore(int score){
        this.score.setText(Integer.toString(score));
    } // setScore
    /**
     * Sets the level
     * @param level Current level given.
     */
    public void setLevel(int level){
        this.level.setText(Integer.toString(level));
    } // setLevel
    /**
     * Sets the combo
     * @param combo Current combo given.
     */
    public void setCombo(int combo){
        this.combo.setText(Integer.toString(combo));
    } // setCombo
    /**
     * Sets the current row
     * @param row Current row given
     */
    public void setRow(int row){
        this.row.setText(Integer.toString(row));
    } // setRow
    /**
     * Sets the current Column
     * @param col Current column given
     */
    public void setColumn(int col){
        this.col.setText(Integer.toString(col));
    } // setColumn
    /**
     * Gets the current score
     * @return Returns the score currently
     */
    public static int getScore(){
        return Integer.parseInt(score.getText());
    } // getScore
    /**
     * Gets the current level
     * @return Returns the current level
     */
    public static int getLevel(){
        return Integer.parseInt(level.getText());
    } // getLevel
    /**
     * Handles actions performed for this class
     */
    public void actionPerformed(ActionEvent evt){
	Object obj = evt.getSource();
        /*
        if(obj == glitched){
            System.out.println("Glitched");
            game = new GameHandler(this);        
        }*/
    } // actionPerformed
    
    /**
     * Handles key presses
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyChar()=='p'){
            System.out.println("Paused");
            pause = new PauseMenuUI(gamePanel);
	} // if : key == 'p'
        if(e.getKeyChar()=='q'){
            System.exit(0);
	} // if : key == 'p'
        if(e.getKeyChar()=='s'){
            try{
                File song = new File("src/resources/sounds/song.wav");
                
                if(!clip.isRunning()){
                    clip.open(AudioSystem.getAudioInputStream(song));
                    clip.start();
                } // if : clip
            } // try
            catch(Exception ex){
                ex.printStackTrace(System.out);
            } // catch
        }
    } // keyPressed
    
    public void keyReleased(KeyEvent e){
        
    } // keyReleased
    
    public void keyTyped(KeyEvent e){
        
    } // keyTyped
} // GameUI

