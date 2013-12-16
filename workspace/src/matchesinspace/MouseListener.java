
package matchesinspace;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
/**
 * 
 * Handles mouse input for the game
 *
 */
public class MouseListener extends MouseInputAdapter{
    private GameHandler gamePanel;
    /**
     * Sets up which panel to listen to
     * @param gamePanel The current GameHandler which needs mouse input
     */
    public MouseListener(GameHandler gamePanel){
        this.gamePanel = gamePanel;
    } // MouseListener : Constructor
    /**
     * If mouse is clicked, do focus/move etc.
     */
    public void mouseClicked (MouseEvent evt){
        int col = (evt.getX()-240)/65;
        int row = (evt.getY()-40)/65;
        
        if(col < 0){
            col = 0;
        } // if : col
        if(col > 7){
            col = 7;
        } // if : col
        if(row < 0){
            row = 0;
        } // if : row
        if(row > 7){
            row = 7;
        } // if : row
        
        gamePanel.clickPerformed(col, row);
        gamePanel.repaint();
    } // mouseClicked 
} // MouseListener
