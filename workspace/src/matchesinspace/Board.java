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
public final class Board {
    
    private static Piece pieces[][];
    BoardGenerator board;
    
    public Board(GameHandler game){
        board = new BoardGenerator();    
    } // Board : Constructor
    
    public Board(GameHandler game, Boolean loaded){
        
    } // do nothing
    
    public void initialize(){
        pieces = board.getBoard();
    } // initialize
    
    public void initializeLoaded(){
        pieces = LoadGame.LoadPieces();
    } // initializeLoaded
    
    /**
     * Swaps pieces based on what piece is selected and direction of second swap
     * @param p1 first piece used in animation, selected piece
     * @param p2 second piece used in animation, what is being swapped with
     */
    public void swapPieces(Piece p1, Piece p2){
        int c1 = p1.col;
        int c2 = p2.col;
        int r1 = p1.row;
        int r2 = p2.row;
        p1.setAnimCol(c2);
        p1.setAnimRow(r2);
        p2.setAnimCol(c1);
        p2.setAnimRow(r1);
        pieces[r1][c1] = p2;
        pieces[r2][c2] = p1;
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
    
    public static Piece[][] getPieces(){
        return pieces;
    } //getPieces
    
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
                } // if : willDrop && type != DELETED
            } // for : each
        } // for : piece[]
    } // collectFallingPieces
    
} // Board
