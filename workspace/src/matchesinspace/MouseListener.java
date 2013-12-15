
package matchesinspace;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
/**
 * 
 * 
 *
 */
public class MouseListener extends MouseInputAdapter{
    private GameHandler gamePanel;
    
    public MouseListener(GameHandler gamePanel){
        this.gamePanel = gamePanel;
    } // MouseListener : Constructor
    
    public void mouseClicked (MouseEvent evt){
        int col = (evt.getX()-240)/65;
        int row = (evt.getY()-24)/65;
        
        if(col < 0){
            col = 0;
        } // if : col
        if(col > BoardGenerator.VERTICAL_PIECES-1){
            col = BoardGenerator.VERTICAL_PIECES-1;
        } // if : col
        if(row < 0){
            row = 0;
        } // if : row
        if(row > BoardGenerator.HORIZONTAL_PIECES-1){
            row = BoardGenerator.HORIZONTAL_PIECES-1;
        } // if : row
        
        gamePanel.clickPerformed(col, row);
        gamePanel.repaint();
    } // mouseClicked 
} // MouseListener
