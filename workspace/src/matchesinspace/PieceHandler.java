package matchesinspace;

import java.util.Random;
/**
 * 
 * Handles information related to a piece, sets up what piece relates to what color
 *
 */
public class PieceHandler {
    
    public PieceHandler(){
        
    }
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
        
        if(num == 0)
            piece = new Piece(Piece.pieceType.BLUE_PLANET, row, col);
        if(num == 1)
            piece = new Piece(Piece.pieceType.GREEN_PLANET, row, col);
        if(num == 2)
            piece = new Piece(Piece.pieceType.GREY_PLANET, row, col);
        if(num == 3)    
            piece = new Piece(Piece.pieceType.ORANGE_PLANET, row, col);
        if(num == 4)
            piece = new Piece(Piece.pieceType.PURPLE_PLANET, row, col);
        if(num == 5)
            piece = new Piece(Piece.pieceType.RED_PLANET, row, col);
        if(num == 6)
           piece = new Piece(Piece.pieceType.YELLOW_PLANET, row, col);
        
        return piece;
    }
    
}
