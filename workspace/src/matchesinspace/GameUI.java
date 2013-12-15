
package matchesinspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

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
    private JLabel score;
    private JLabel textLevel;
    private JLabel level;
    private JLabel textCombo;
    private JLabel combo;
    private JLabel textRow;
    private JLabel row;
    private JLabel textCol;
    private JLabel col;
    
    /**
    * Sets up basic information on the game board
    */
    public GameUI(){
        System.out.println("GameUI - Building UI");
        
        initializeComponents();      
	this.setVisible(true);
	addKeyListener(this);
              
        System.out.println("GameUI - UI Built");
    } // GameUI : Constructor
    
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
        statsPanel.setLayout(new GridLayout(6,2));
        statsPanel.add(textScore);
        statsPanel.add(score);
        statsPanel.add(textLevel);
        statsPanel.add(level);
        statsPanel.add(textCombo);
        statsPanel.add(combo);
        statsPanel.add(textRow);
        statsPanel.add(row);
        statsPanel.add(textCol);
        statsPanel.add(col);
        gamePanel.add(statsPanel, BorderLayout.WEST);
        
        System.out.println("GameUI - Building Game");
        GameHandler game = new GameHandler(this);
        gamePanel.add(game.getGameHandler(), BorderLayout.CENTER);
        System.out.println("GameUI - Game Running");
        
        this.add(gamePanel);
    } // initializeComponents
    
    private void windowSetup(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        //double screenHeight = screenSize.height*.75;
        //double screenWidth  = screenSize.width*.75;
        int screenHeight = 625;
        int screenWidth = 940;
        setSize((int)screenWidth,(int)screenHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("MATCHES..........IN SPACE!!!!");
        setLayout(new BorderLayout());
        setDefaultCloseOperation (EXIT_ON_CLOSE);   
    } // windowSetup
    
    public void setScore(int score){
        this.score.setText(Integer.toString(score));
    } // setScore
    
    public void setLevel(int level){
        this.level.setText(Integer.toString(level));
    } // setLevel
    
    public void setCombo(int combo){
        this.combo.setText(Integer.toString(combo));
    } // setCombo
    
    public void setRow(int row){
        this.row.setText(Integer.toString(row));
    } // setRow
    
    public void setColumn(int col){
        this.col.setText(Integer.toString(col));
    } // setColumn

    public void actionPerformed(ActionEvent event){
	
    } // actionPerformed
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyChar()=='p'){
            System.out.println("Paused");
            pause = new PauseMenuUI(gamePanel);
	} // if : key == 'p'
        if(e.getKeyChar()=='q'){
            System.exit(0);
	} // if : key == 'p'
    } // keyPressed
    
    public void keyReleased(KeyEvent e){
        
    } // keyReleased
    
    public void keyTyped(KeyEvent e){
        
    } // keyTyped
} // GameUI

