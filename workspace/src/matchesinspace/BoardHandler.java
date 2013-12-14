package matchesinspace;

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * Handles most information relating to the board, directly relating it to the UI
 *
 */
public class BoardHandler implements ActionListener{
    /**
     * @field pieces[][] 'Board' of pieces, 2 dimensional array of all pieces.
     * @field p1 first selected piece
     * @field p2  second selected piece
     * @field matches arraylist of all pieces that are being matched
     * @field fallingPieces  arraylist of all piece currently falling
     * @field MULTIPLIER constant score multiplier
     * @field frame amount of frames used in animation
     * @field timer timer for the game
     * @field animationType type of animation used, contains swap and cascade
     * @field type current animation type
     * @field gamePanel contains information related to gameUI
     * 
     */
    private Piece pieces[][];
    private Piece p1;
    private Piece p2;
    private ArrayList<ArrayList<Piece>> matches;
    private ArrayList<Piece> fallingPieces;
    private static final int MULTIPLIER = 10;
    private int frame;
    private Timer timer;
    public static enum animationType{SWAP, CASCADE};
    private animationType type;
    private GameUI gamePanel;
    /**
     * Constructor sets up information, gets pieces/board array.
     */
    public BoardHandler(){
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
    }
    /**
     * Gets the piece at a certain row and col
     * @param row row of piece selected
     * @param col column of piece selected
     * @return returns piece at row, col
     */
    public Piece getPieceAt(int row, int col){
        return pieces[row][col];
    }
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
    }
    /**
     * Gets the amount of falling pieces from a match
     * @param collection array of all pieces that are falling
     */
    public void collectFallingPieces(ArrayList<Piece> collection){
        for(Piece piece[] : pieces){
            for(Piece each : piece){
                if(each.willDrop && !each.type.equals(Piece.pieceType.DELETED))
                    collection.add(each);
            }
        }
    }
    
    //***********ALGORITHMS***********
    /**
     * Checks if there is any current matches, if none found, returns no matches found.
     * @return Returns the array list stating that there are not current matches moving or in progress
     */
    public boolean isStable(){
        checkRows();
        checkColumns();
        return matches.isEmpty();
    }
    /**
     * Removes a match when called, handles information regarding it.
     */
    public void removeMatches(){
        markDeleted();
        calculateDrop();
        applyDrop();
        fillEmpty();
        endCascade();
    }
    /**
     * Matches a match as deleted, adds to a combo and adds to the score
     */
    public void markDeleted(){
        int combo = 0;
        int score = 0;
        
        for(ArrayList<Piece> match : matches){
            combo += match.size();
            score += match.size()*MULTIPLIER;
            for(Piece piece : match){
                piece.type = Piece.pieceType.DELETED;
            }
        }
        matches.clear();
        addScore(score);
        setCombo(combo);
    }
    /**
     * Calculates the drop from a resulting match
     */
    public void calculateDrop(){
        for(int col = 0; col < BoardGenerator.VERTICAL_PIECES; col++){
            for(int row = BoardGenerator.HORIZONTAL_PIECES-1; row >=0; row--){
                Piece bottomPiece = this.getPieceAt(row, col);
                bottomPiece.beforeDrop = row;
                
                if(bottomPiece.type.equals(Piece.pieceType.DELETED)){
                    for(int temp = row-1; temp >= 0; temp--){
                        Piece topPiece = this.getPieceAt(temp, col);
                        topPiece.willDrop = true;
                        topPiece.dropDistance++;
                    }
                }
            }
        }
    }
    /**
     * Applies animations and other information related to dropping, as well as moving pieces.
     */
    public void applyDrop(){
        for(int col = 0; col < BoardGenerator.VERTICAL_PIECES; col++){
            for(int row = BoardGenerator.HORIZONTAL_PIECES-1; row >= 0; row--){
                Piece piece = this.getPieceAt(row, col);
                
                if(piece.willDrop && !piece.type.equals(Piece.pieceType.DELETED)){
                    this.setPieceAt(row+piece.dropDistance, col, piece);
                    piece.setAnimCol(col);
                    this.setPieceAt(row, col, new Piece(Piece.pieceType.DELETED, row, col));
                }
            }
        }
    }
    /**
     * Generates new pieces for pieces that are missing
     */
    public void fillEmpty(){
        for(int row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
                Piece piece = this.getPieceAt(row, col);
                if(piece.type.equals(Piece.pieceType.DELETED)){
                    Piece random = PieceHandler.generateRandom(row, col);
                    this.setPieceAt(row, col, random);
                }
            }
        }
    }
    /**
     * Ends the cascade of multiple piece matches happening at the same time or in a combo
     */
    public void endCascade(){
        for(int row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
                Piece piece = this.getPieceAt(row, col);
                piece.beforeDrop = col;
                piece.dropDistance = 0;
                piece.willDrop = false;
            }
        }
    }
    /**
     * Checks rows if there is matches.
     */
    private void checkRows(){
        int temp;
        for(int row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES-2; col++){
                Piece start = this.getPieceAt(row, col);
                ArrayList match = new ArrayList(5);
                match.add(start);
                
                for(temp = (col+1); temp < BoardGenerator.HORIZONTAL_PIECES; temp++){
                    Piece next = this.getPieceAt(row, temp);
                    if(next.type.equals(start.type)){
                        match.add(next);
                    }
                    else{
                        break;
                    }
                    
                }
                if(match.size() > 2){
                    this.matches.add(match);
                }
                col = temp - 1;
            }
        }
    }
    /**
     * Checks columns if there is any matches
     */
    private void checkColumns(){
        int temp;
        for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
            for(int row = 0; row < BoardGenerator.VERTICAL_PIECES-2; row++){
                Piece start = this.getPieceAt(row, col);
                ArrayList match = new ArrayList(3);
                match.add(start);
                
                for(temp = (row+1); temp < BoardGenerator.VERTICAL_PIECES; temp++){
                    Piece next = this.getPieceAt(temp, col);
                    if(next.type.equals(start.type)){
                        match.add(next);
                    }
                    else{
                        break;
                    }
                }
                if(match.size() > 2){
                    this.matches.add(match);
                }
                row = temp - 1;
            }
        }
    }
    
    //***********ANIMATIONS***********
    
}
