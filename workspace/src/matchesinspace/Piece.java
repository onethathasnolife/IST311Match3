/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchesinspace;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 *
 * @author Bob
 */
public class Piece {
    
    protected static enum pieceType { BLUE_PLANET, GREEN_PLANET, GREY_PLANET, ORANGE_PLANET, PURPLE_PLANET, RED_PLANET, YELLOW_PLANET, FOCUSED, DELETED }
    protected pieceType type;
    protected ImageIcon pieceIcon;
    
    // animate
    protected int row;
    protected int col;
    protected int anim_row;
    protected int anim_col;
    protected boolean inFocus;
    protected boolean willDrop;
    protected int beforeDrop;
    protected int dropDistance;
    
    public Piece(pieceType type, int row, int col){
        this.type = type;
        this.row = row;
        this.col = col;
        
        // don't know what these do yet
        this.anim_row = row*65+40;
        this.anim_col = col*65+240;
        this.beforeDrop = 0;
        this.dropDistance = 0;
        this.willDrop = false;
        this.inFocus = false;
    
        if(this.type != pieceType.DELETED){
            this.pieceIcon = new ImageIcon();
            this.pieceIcon.setImage(BoardHandler.imageLibrary.getImage(type));
        }
    }
        
    public boolean isNextTo(Piece piece){
        if(Math.abs(row-piece.row) == 1 && Math.abs(col-piece.col) == 0){
            return true;
        }
        else if(Math.abs(row - piece.row) == 0 && Math.abs(col - piece.col) == 1){
            return true;
        }
        else{
            return false;
        }               
    }
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(!type.equals(Piece.pieceType.DELETED))
            pieceIcon.paintIcon(null, g, anim_col, anim_row);
        if(inFocus){
            ImageIcon focusIcon = new ImageIcon();
            focusIcon.setImage(BoardHandler.imageLibrary.getImage(Piece.pieceType.FOCUSED));
            focusIcon.paintIcon(null, g, anim_col, anim_row);
        }
    }
    
    public void moveRow(int step, int direction){
        anim_row += step*direction;
    }
    
    public void moveCol(int step, int direction){
        anim_col += step*direction;
    }
    
    public void setAnimRow(int row){
        this.col = col;
        this.anim_col = col*65+240;
    }
    
    public void setAnimCol(int col){
        this.col = col;
        this.anim_col = col*65+240;
    }
        
}