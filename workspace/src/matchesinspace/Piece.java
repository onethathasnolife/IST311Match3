/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchesinspace;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 * Handles and controls all information related to an individual piece
 * 
 */
public class Piece {
    /**
     * 
     *@field pieceType relates information to what piece type the piece is
     *@field type current type of piece the piece is
     *@field pieceIcon the icon/image for the type of piece the piece is
     *@field row Row of the piece
     *@field col Column of the piece
     *@field anim_row Row of the Animation
     *@field anim_col Column of the Animation
     *@field inFocus The state if the piece is currently in focus
     *@field willDrop The state if the piece will drop after a match
     *@field beforeDrop  The column of the piece before dropping
     *@field dropDistance The distance of the drop in rows
     *
     */
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
    /**
     * Sets information about the piece
     * @param type Type of the piece, relates to icon
     * @param row Current row of the piece
     * @param col Current column of the piece
     */
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
    /**
     * Shows if the piece is currently next to a piece of the same type    
     * @param piece the current piece being selected
     * @return true if next to a certain piece, false if it isnt
     */
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
    /**
     * Draws the grahpic for the piece
     * @param g Given grahpics field/board.
     */
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
    /**
     * Moves a piece by row
     * @param step Amount of steps the piece is moved
     * @param direction Direction of movement, usually one or negative one
     */
    public void moveRow(int step, int direction){
        anim_row += step*direction;
    }
    /**
     * Moves a piece by column
     * @param step Amount of steps the piece is moved
     * @param direction Direction of movement, usually positive one or negative one
     */
    public void moveCol(int step, int direction){
        anim_col += step*direction;
    }
    /**
     * Sets animation of a row of a piece
     * @param row Given row of a piece
     */
    public void setAnimRow(int row){
        this.col = col;
        this.anim_col = col*65+240;
    }
    /**
     * Sets animation of a column of a piece
     * @param col Given column of a piece
     */
    public void setAnimCol(int col){
        this.col = col;
        this.anim_col = col*65+240;
    }
        
}