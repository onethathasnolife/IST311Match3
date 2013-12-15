
package matchesinspace;

import java.util.ArrayList;

/**
 * 
 * Handles most information relating to the board, directly relating it to the UI
 *
 */

public class BoardHandler{
    /**
     * @field pieces[][] 'Board' of pieces, 2 dimensional array of all pieces.
     * @field p1 first selected piece
     * @field p2  second selected piece
     * @field matches arraylist of all pieces that are being matched
     * @field MULTIPLIER constant score multiplier
     * @field gameHandler contains information related to gameUI
     * 
     */
    
    private ArrayList<ArrayList<Piece>> matches;
    private static final int MULTIPLIER = 10;
    private GameHandler gameHandler;
    private Board gameBoard;
    
    /**
     * Constructor sets up information, gets pieces/board array.
     */
    public BoardHandler(Board gameBoard, GameHandler gameHandler){
        System.out.println("BoardHandler - Constructor");
        this.gameHandler = gameHandler;
        this.gameBoard = gameBoard;
        this.matches = new ArrayList();      
    } // BoardHandler : Constructor
     
    //***********ALGORITHMS***********
    /**
     * Checks if there is any current matches, if none found, returns no matches found.
     * @return Returns the array list stating that there are not current matches moving or in progress
     */
    public boolean isStable(){
        System.out.println("BoardHandler - Checking Stability");
        checkRows();
        checkColumns();
        System.out.println("BoardHandler - Stability Status: "+matches.isEmpty());
        return matches.isEmpty();
    } // isStable
    
    /**
     * Removes a match when called, handles information regarding it.
     */
    public void removeMatches(){
        System.out.println("BoardHandler - Removing Matches");
        markDeleted();
        calculateDrop();
        applyDrop();
        fillEmpty();
        endCascade();
        System.out.println("BoardHandler - Matches Removed");
    } // removeMatches
    
    /**
     * Matches a match as deleted, adds to a combo and adds to the score
     */
    public void markDeleted(){
        System.out.println("BoardHandler - Marking Deleted");
        int combo = 0;
        int score = 0;
        
        for(ArrayList<Piece> match : matches){
            combo += match.size();
            score += match.size()*MULTIPLIER;
            for(Piece piece : match){
                System.out.println("BoardHandler + markDeleted + Piece Type: "+piece.type);
                piece.type = Piece.pieceType.DELETED;
                System.out.println("BoardHandler - markDeleted - Piece Type: "+piece.type);             
            } // for : piece
        } // for : match
        matches.clear();
        gameHandler.addScore(score);
        gameHandler.setCombo(combo);
        System.out.println("BoardHandler - Marked");
    } // markDeleted
    
    /**
     * Calculates the drop from a resulting match
     */
    public void calculateDrop(){
        int col, row, temp;
        for(col = 0; col < 8; col++){
            for(row = 7; row >=0; row--){
                Piece bottomPiece = gameBoard.getPieceAt(row, col);
                bottomPiece.beforeDrop = row;
                
                if(bottomPiece.type.equals(Piece.pieceType.DELETED)){
                    for(temp = row-1; temp >= 0; temp--){
                        Piece topPiece = gameBoard.getPieceAt(temp, col);
                        topPiece.willDrop = true;
                        topPiece.dropDistance++;
                    } // for : row
                } // if : pieceType == DELETED
            } // for : row
        } // for : col
    } // calculateDrop
    
    /**
     * Applies animations and other information related to dropping, as well as moving pieces.
     */
    public void applyDrop(){
        int col, row;
        for(col = 0; col < 8; ++col){
            for(row = 7; row >= 0; --row){
                Piece piece = gameBoard.getPieceAt(row, col);
                
                if(piece.willDrop && !piece.type.equals(Piece.pieceType.DELETED)){
                    gameBoard.setPieceAt(row+piece.dropDistance, col, piece);
                    piece.setAnimCol(col);
                    gameBoard.setPieceAt(row, col, new Piece(Piece.pieceType.DELETED, row, col));
                } // if : willDrop && !DELETED
            } // for : row
        } // for : col
    } // applyDrop
    
    /**
     * Generates new pieces for pieces that are missing
     */
    public void fillEmpty(){
        System.out.println("BoardHandler - Filling Empty Spaces");
        int row, col;
        for(row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
                Piece piece = gameBoard.getPieceAt(row, col);
                if(piece.type.equals(Piece.pieceType.DELETED)){
                    System.out.println("DELETED FOUND");
                    Piece random = PieceHandler.generateRandom(row, col);
                    gameBoard.setPieceAt(row, col, random);
                } // if : pieceType == DELETED
            } // for : col
        } // for : row
        System.out.println("BoardHandler - Empty Spaces Filled");
    } // fillEmpty
    
    /**
     * Ends the cascade of multiple piece matches happening at the same time or in a combo
     */
    public void endCascade(){
        System.out.println("BoardHandler - Ending Cascade");
        int row, col;
        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                Piece piece = gameBoard.getPieceAt(row, col);
                piece.beforeDrop = col;
                piece.dropDistance = 0;
                piece.willDrop = false;
            } // for : col
        } // for : row
        System.out.println("BoardHandler - Cascade Ended");
    } // endCascade
    
    /**
     * Checks rows if there is matches.
     */
    private void checkRows(){
        System.out.println("BoardHandler - Checking Rows");
        int row, col, temp;
        for(row = 0; row < 8; row++){
            for(col = 0; col < 6; col++){
                Piece start = gameBoard.getPieceAt(row, col);
                ArrayList match = new ArrayList(5);
                match.add(start);
                
                for(temp = (col+1); temp < 8; temp++){
                    Piece next = gameBoard.getPieceAt(row, temp);
                    if(next.type.equals(start.type)){
                        match.add(next);
                    } // if : match
                    else{
                        break;
                    } // else                   
                } // for : temp
                if(match.size() > 2){
                    this.matches.add(match);
                } // if : match.size
                col = temp - 1;
            } // for : col
        } // for : row
        System.out.println("BoardHandler - Rows Checked");
    } // checkRows
    
    /**
     * Checks columns if there is any matches
     */
    private void checkColumns(){
        System.out.println("BoardHandler - Checking Columns");
        int col, row, temp;
        for(col = 0; col < 8; col++){
            for(row = 0; row < 6; row++){
                Piece start = gameBoard.getPieceAt(row, col);
                ArrayList match = new ArrayList(3);
                match.add(start);
                
                for(temp = (row+1); temp < 8; temp++){
                    Piece next = gameBoard.getPieceAt(temp, col);
                    if(next.type.equals(start.type)){
                        match.add(next);
                    } // if : match
                    else{
                        break;
                    } // else
                } // for : temp
                if(match.size() > 2){
                    this.matches.add(match);
                } // if : match.size
                row = temp - 1;
            } // for : row
        } // for : col
        System.out.println("BoardHandler - Columns Checked");
    } // checkColumns
          
} // BoardHandler
