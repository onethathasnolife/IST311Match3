
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchesinspace;

import java.awt.Color;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JComponent;


/**
 *
 * @author Bob
 */
public final class GameHandler extends JComponent {
    
    private GameUI gamePanel;
    private BoardHandler gameBoard;
    private Piece focus;
    private boolean started;
    private int score;
    private int level;
    private int combo;
    public static ImageLibrary imageLibrary = new ImageLibrary();
    public static SoundLibrary soundLibrary = new SoundLibrary();
    public BufferedImage boardImg;
    public ImageIcon boardIcon;
    static final String DIRECTORY   = "src/images/";
    
    public GameHandler(GameUI gamePanel){
        try{
            boardImg = ImageIO.read(new File(DIRECTORY+"board.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        
        this.boardIcon = new ImageIcon();
        this.boardIcon.setImage(this.boardImg);
        started = false;
        this.gamePanel = gamePanel;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800,600)); 
        this.addMouseListener(new MouseListener(this));
    } // GameHandler : Constructor
    
    public void initializeGame(){
        gameBoard = new BoardHandler();
        
        while(!gameBoard.isStable()) {
            gameBoard.removeMatches();
        } // while : !stable
        
        started = true;
        focus = null;
        score = 0;
        combo = 0;
        level = 1;
        gamePanel.setScore(score);
        gamePanel.setCombo(combo);
        gamePanel.setLevel(level);
        gamePanel.setRow(-1);
        gamePanel.setColumn(-1);
        repaint();
        
        //Game.soundLibrary.playAudio("fall");
    } // intitializeGame
    
    public void updateGame(){
        if (!gameBoard.isStable()) {
            gameBoard.markDeleted();
            gameBoard.calculateDrop();
            gameBoard.setAnimationType(BoardHandler.animationType.CASCADE);
            gameBoard.animateCascade();
            
            //Game.soundLibrary.playAudio("fall");
        } // if : !stable
    } // updateGame
    
    public void cleanBoard(){
        gameBoard.applyDrop();
        gameBoard.fillEmpty();
        gameBoard.endCascade();
    } // cleanBoard
    
    public void addScore(int points){
        if((this.score + points) > 1000){
            this.level++;
            this.score = 0;
            gamePanel.setLevel(this.level);
            gamePanel.setScore(this.score);
        } // if : score
        else{
            this.score += points;
            gamePanel.setScore(score);
        } // else
    } // addScore
    
    public void setCombo(int combo){
        if(combo > this.combo){
            this.combo = combo;
            gamePanel.setCombo(combo);
        } // if : combo
    } // setCombo
    
    public void clickPerformed(int col, int row){
        gamePanel.setColumn(col);
        gamePanel.setRow(row);
        Piece clicked = gameBoard.getPieceAt(row, col);
        if(focus == null){
            focus = clicked;
            clicked.inFocus = true;
            
            //Game.soundLibrary.playAudio("select");
        } // if : focus == null
        else{
            if(focus.equals(clicked)){
                clicked.inFocus = false;
                focus = null;
            } // if : focus
            else{
                if(focus.isNextTo(clicked)){
                    focus.inFocus = false;
                    gameBoard.swapPieces(focus, clicked);
                    focus = null;
                } // if : isNextTo
                else{
                    focus.inFocus = false;
                    focus = clicked;
                    clicked.inFocus = true;
                } // else
            } // else
        } // else
    } // clickPerformed
    
    public void paintComponent(Graphics g){
        this.boardIcon.paintIcon(null, g, 0, 0);
        if(started){
            drawPieces(g);
        } // if : started
    } // paintComponent
    
    private void drawPieces(Graphics g){
        for(int row = 0; row < BoardGenerator.HORIZONTAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.VERTICAL_PIECES; col++){
                Piece piece = gameBoard.getPieceAt(row, col);
                piece.draw(g);
            } // for : col
        } // for : row
    } // drawPieces    
} // GameHandler
