/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchesinspace;

import java.util.ArrayList;

/**
 *
 * Handles information related to the Board, calls classes needed and acts as an inbetween the UI and Game
 */
public class Board {
    
    private Piece pieces[][];
    
    public Board(GameHandler game){
        BoardGenerator board = new BoardGenerator();
        pieces = board.getBoard();
    }
    
    /**
     * Swaps pieces based on what piece is selected and direction of second swap
     * @param piece1 first piece used in animation, selected piece
     * @param piece2 second piece used in animation, what is being swapped with
     */
    public void swapPieces(Piece piece1, Piece piece2){
        int column1 = piece1.col;
        int column2 = piece2.col;
        int row1 = piece1.row;
        int row2 = piece2.row;
        piece1.setAnimCol(column2);
        piece1.setAnimRow(row2);
        piece2.setAnimCol(column1);
        piece2.setAnimRow(row1);
        pieces[row1][column1] = piece2;
        pieces[row2][column2] = piece1;
    } // swapPieces
    
    /**
     * Gets the piece at a certain row and col
     * @param row row of piece selected
     * @param col column of piece selected
     * @return returns piece at row, col
     */
    public Piece getPieceAt(int row, int col){
        return pieces[row][col];
    } // getPieceAt
    
    /**
     * Sets a piece at a certain row and column, with a certain piece
     * @param row Row of piece selected
     * @param col Column of piece selected
     * @param piece piece used to set at certain spot
     */
    public void setPieceAt(int row, int col, Piece piece){
        piece.setAnimCol(col);                                                  
        piece.setAnimRow(row);                                                  
        pieces[row][col] = piece;
    } // setPieceAt
    
    /**
     * Gets the amount of falling pieces from a match
     * @param collection array of all pieces that are falling
     */
    public void collectFallingPieces(ArrayList<Piece> collection){
        for(Piece piece[] : pieces){
            for(Piece each : piece){
                if(each.willDrop && !each.type.equals(Piece.pieceType.DELETED)){
                    System.out.println("BoardHandler - Pieces Collected");
                    collection.add(each);
                }
            } // for : each
        } // for : piece[]
    } // collectFallingPieces
    
}
