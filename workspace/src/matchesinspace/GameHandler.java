
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
import static matchesinspace.ImageLibrary.DIRECTORY;


/**
 * Handles the information about the game, allows information to be passed from the pieces to game UI,lots of work stuff found here
 *
 */
public final class GameHandler extends JComponent {
    /**
     * @field gamePanel The GameUI being updated
     * @field gameBoard The BoardHandler for the GameHandler
     * @field focus The currently focused piece
     * @field started Tells if game is currently started
     * @field score Score of the game
     * @field level Current level of the game - meaningless
     * @field combo Current combo of matches
     * @field imageLibrary ImageLibrary which contains all information related to images being sourced
     * @field soundLibrary SoundLibrary which contains all information related to sounds being sourced
     * @field boardImg BufferedImage of the current board, containing board images and pieces
     * @field boardIcon ImageIcon of the board which is used
     * @field DIRECTORY Current directory to find information used in this class
     * 
     */
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
    static final String DIRECTORY   = "src/resources/images/";
    /**
     * Contructor, sets main information about the GameHandler and GameUI
     * @param gamePanel GameUI which is used and updated
     */
    public GameHandler(GameUI gamePanel){
        try{
            boardImg = ImageIO.read(new File(DIRECTORY+"board2.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "board2.png");
        } // catch
        this.gamePanel = gamePanel;
        System.out.println("GameHandler - Initializing Game");
        initializeGame();
        System.out.println("GameHandler - Initialized");
        
        this.boardIcon = new ImageIcon();
        this.boardIcon.setImage(this.boardImg);
        
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800,600)); 
        this.addMouseListener(new MouseListener(this));
    } // GameHandler : Constructor
    /**
     * Initializes many aspects of the game not found in the constructor
     */
    public void initializeGame(){     
        gameBoard = new BoardHandler(this);
        
        while(!gameBoard.isStable()) {
            gameBoard.removeMatches();
        } // while : !stable
        
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
    /**
     * Used so outside classes can get current object of GameHandler
     * @return returns current GameHandler object
     */
    public GameHandler getGameHandler(){
        return this;
    } // getGameHandler
    /**
     * Updates game based on animations and pieces being matched
     */
    public void updateGame(){
        if (!gameBoard.isStable()) {
            gameBoard.markDeleted();
            gameBoard.calculateDrop();
            gameBoard.setAnimationType(BoardHandler.animationType.CASCADE);
            gameBoard.animateCascade();
            //Game.soundLibrary.playAudio("fall");
        } // if : !stable
    } // updateGame
    /**
     * Cleans the boards, fills empty pieces, ends cascades
     */
    public void cleanBoard(){
        System.out.println("GameHandler - Cleaning Up");
        gameBoard.applyDrop();
        gameBoard.fillEmpty();
        gameBoard.endCascade();
        System.out.println("GameHandler - Finished Cleaning");
    } // cleanBoard
    /**
     * Adds score to the current score
     * @param points Adds points/score to the current score, increments level if over a certain amount
     */
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
    /**
     * Sets the combo to better understand points to receive
     * @param combo current combo which is used to set
     */
    public void setCombo(int combo){
        if(combo > this.combo){
            this.combo = combo;
            gamePanel.setCombo(combo);
        } // if : combo
    } // setCombo
    /**
     * When a click is performed, output the current position to the readout
     * @param col Current column of the click
     * @param row Current row of the click
     */
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
                    this.swapPieces(focus, clicked);
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
    /**
     * Swaps the pieces and calls the animation to happen
     * @param p1 First piece selected
     * @param p2 Second piece being swapped
     */
    private void swapPieces(Piece p1, Piece p2){
        gameBoard.setAnimationType(BoardHandler.animationType.SWAP);
        gameBoard.animateSwap(p1, p2);
    } // swapPieces
    /**
     * Paints the boardIcon
     */
    public void paintComponent(Graphics g){
        this.boardIcon.paintIcon(null, g, 0, 0);
        drawPieces(g);
    } // paintComponent
    /**
     * Draws the game pieces based on information found on the pieces objects
     * @param g Current grahpic being painted
     */
    private void drawPieces(Graphics g){
        for(int row = 0; row < BoardGenerator.HORIZONTAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.VERTICAL_PIECES; col++){
                Piece piece = gameBoard.getPieceAt(row, col);
                piece.draw(g);
            } // for : col
        } // for : row
    } // drawPieces    
} // GameHandler
