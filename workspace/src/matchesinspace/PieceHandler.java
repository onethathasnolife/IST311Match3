
package matchesinspace;

import java.util.Random;

/**
 * 
 * Handles information related to a piece, sets up what piece relates to what color
 *
 */
public class PieceHandler {
    
    /**
     * Generates a random number, sets a piece to a certain type based on what is generated
     * @param row Given row of a piece
     * @param col Given column of a piece
     * @return Returns the current piece information
     */
    public static Piece generateRandom(int row, int col){
        Piece piece = null;
        String pieceType = null;
        
        Random rand = new Random();
        
        int num = rand.nextInt(7);
        
        if(num == 0){
            piece = new Piece(Piece.pieceType.BLUE_PLANET, row, col);
        } // if : 0
        if(num == 1){
            piece = new Piece(Piece.pieceType.GREEN_PLANET, row, col);
        } // if : 1
        if(num == 2){
            piece = new Piece(Piece.pieceType.GREY_PLANET, row, col);
        } // if : 2
        if(num == 3){    
            piece = new Piece(Piece.pieceType.ORANGE_PLANET, row, col);
        } // if : 3 
        if(num == 4){
            piece = new Piece(Piece.pieceType.PURPLE_PLANET, row, col);
        } // if : 4
        if(num == 5){
            piece = new Piece(Piece.pieceType.RED_PLANET, row, col);
        } // if : 5
        if(num == 6){
           piece = new Piece(Piece.pieceType.YELLOW_PLANET, row, col);
        } // if : 6
        
        return piece;
    } // generateRandom
    
    public static Piece generatePiece(int row, int col, String type){
        Piece piece = null;
        System.out.println("type g:"+type+".");
        if(type.equals(Piece.pieceType.BLUE_PLANET.toString())){
            piece = new Piece(Piece.pieceType.BLUE_PLANET, row, col);
        } // if : blue
        if(type.equals(Piece.pieceType.GREEN_PLANET.toString())){
            piece = new Piece(Piece.pieceType.GREEN_PLANET, row, col);
        } // if : green
        if(type.equals(Piece.pieceType.GREY_PLANET.toString())){
            piece = new Piece(Piece.pieceType.GREY_PLANET, row, col);
        } // if : grey
        if(type.equals(Piece.pieceType.ORANGE_PLANET.toString())){
            piece = new Piece(Piece.pieceType.ORANGE_PLANET, row, col);
        } // if : orange
        if(type.equals(Piece.pieceType.PURPLE_PLANET.toString())){
            piece = new Piece(Piece.pieceType.PURPLE_PLANET, row, col);
        } // if : purple
        if(type.equals(Piece.pieceType.RED_PLANET.toString())){
            System.out.println("Red Match");
            piece = new Piece(Piece.pieceType.RED_PLANET, row, col);
        } // if : red
        if(type.equals(Piece.pieceType.YELLOW_PLANET.toString())){
            piece = new Piece(Piece.pieceType.YELLOW_PLANET, row, col);
        } // if : yellow
        
        return piece;
    } // generatePiece
    
} // PieceHandler
