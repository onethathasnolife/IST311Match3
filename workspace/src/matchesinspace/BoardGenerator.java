
package matchesinspace;

/**
 * 
 * Handles board Generation, Holds pieces and the 'board'. 
 *
 */

public final class BoardGenerator {
    /**
     * @field pieces[][] 2 dimension array of all individual pieces
     * @field VERTICAL_PIECES Defines the amount of pieces vertically on the board
     * @field HORIZONTAL_PIECES Defines amount of pieces horizontally on the board
     */
    private Piece pieces[][];
    public static final int VERTICAL_PIECES = 8;
    public static final int HORIZONTAL_PIECES = 8;
    
    /**
     * Defines pieces array
     */
    public BoardGenerator(){
        pieces = new Piece[VERTICAL_PIECES][HORIZONTAL_PIECES];
        
        System.out.println("BoardGenerator - Generating Board");
        initializeBoard();
        System.out.println("BoardGenerator - Generated");
    } // BoardGenerator : Constructor      
    
    /**
     * Populates board with pieces
     */
    public void initializeBoard(){
        for(int row = 0; row < VERTICAL_PIECES; row++){
            for(int col = 0; col < HORIZONTAL_PIECES; col++){
                PieceHandler newPiece = null;
                pieces[row][col] = newPiece.generateRandom(row, col);
            } // for : col
        } // for : row
    } // initilializeBoard
    
    /**
     * Allows outside classes to get the 'board' or array of pieces
     * @return pieces returns the array of pieces, effectively making the board
     */
    public Piece[][] getBoard(){
        return pieces;
    } // getBoard
    
} // BoardGenerator
