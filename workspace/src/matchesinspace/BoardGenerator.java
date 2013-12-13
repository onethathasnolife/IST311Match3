package matchesinspace;

public final class BoardGenerator {
    
    private Piece pieces[][];
    public static final int VERTICAL_PIECES = 8;
    public static final int HORIZONTAL_PIECES = 8;
    
    public BoardGenerator(){
        pieces = new Piece[VERTICAL_PIECES][HORIZONTAL_PIECES];
        initializeBoard();
    }      
    
    public void initializeBoard(){
        for(int row = 0; row < VERTICAL_PIECES; row++){
            for(int col = 0; col < HORIZONTAL_PIECES; col++){
                PieceHandler newPiece = null;
                pieces[row][col] = newPiece.generateRandom(row, col);
            }
        }
    }
    
    public Piece[][] getBoard(){
        return pieces;
    }
}
