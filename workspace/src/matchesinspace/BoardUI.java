
package matchesinspace;

import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardUI implements ActionListener{
    
    private int frame;
    private Timer timer;
    private Piece p1;
    private Piece p2;
    private ArrayList<Piece> fallingPieces;
    private Board gameBoard;
    private GameHandler gameHandler;
    private animationType animationType;
    public static enum animationType{SWAP, CASCADE};
    
    public BoardUI(Board gameBoard, GameHandler gameHandler, animationType type){
        this.gameBoard = gameBoard;
        this.gameHandler = gameHandler;
        this.animationType = type;
        p1 = null;
        p2 = null;
        fallingPieces = null;
        frame = 0;
        timer = new Timer(10, this);
    }
    
    //***********ANIMATIONS***********
    /**
     * Sets the animation type of the object
     * @param animationType Sets the current animation type of the object, given animation type
     */
    public void setAnimationType(animationType type){
        this.animationType = type;
    } // setAnimationType
    
    /**
     * Returns the Animation type 
     * @return returns the current animation type
     */
    public animationType getAnimationType(){
        return animationType;
    } // getAnimationType
    
    /**
     * Tells the current frame the animation is in
     * @return the current frame number in sequence
     */
    public int getCurrentFrame(){
        return frame;
    } // getCurrentFrame
    
    public void animateSwap(Piece p1, Piece p2){
        this.p1 = p1;
        this.p2 = p2;
        timer.start();
    } // animateSwap
    
    /**
     * Ends swap animation
     */
    public void endSwapAnimation(){
        timer.stop();
        frame = 0;
        gameBoard.swapPieces(p1, p2);
        gameHandler.repaint();
        gameHandler.updateGame();
    } // endSwapAnimation
    
    /**
     * Animates the cascade for a time
     */
    public void animateCascade(){
        fallingPieces = new ArrayList();
        gameBoard.collectFallingPieces(fallingPieces);
        timer.start();
    } // animateCascade
    
    /**
     * Tells when to end the Cascade Animation
     */
    public void endCascadeAnimation(){
        timer.stop();
        frame = 0;
        gameHandler.cleanBoard();
        gameHandler.repaint();
        gameHandler.updateGame();
    } // endCascadeAnimation
    
    public void actionPerformed(ActionEvent evt){
        if(animationType.equals(animationType.SWAP)){
            frame++;
            if(frame > 32){
                endSwapAnimation();
            } // if : frame
            else{
                int direction = 1;
                if(p1.col == p2.col){
                    if(p1.row < p2.row){
                        direction = 1;
                    } // if : row
                    else{
                        direction = -1;
                    } // else
                    p1.moveRow(2, direction);
                    p2.moveRow(2, -direction);
                } // if : col
                else{
                    if(p1.col < p2.col){
                        direction = 1;
                    } // if : col
                    else{
                        direction = -1;
                    } // else
                    p1.moveCol(2, direction);
                    p2.moveCol(2, -direction);
                } // else
                gameHandler.repaint();
            } // else
        } // if : animationType == SWAP
        else if(animationType.equals(animationType.CASCADE)){
            frame++;
            if(frame > 32){
                endCascadeAnimation();
            } // if : frame
            else{
                for(Piece piece : fallingPieces){
                    piece.moveRow(piece.dropDistance*2, 1);
                } // for : piece
                gameHandler.repaint();
            } // else
        } // else if : animationType == CASCADE
    } // actionPerformed
    
}
