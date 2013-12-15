
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
    private animationType animationType;
    private GameHandler gamePanel;
    
    /**
     * Constructor sets up information, gets pieces/board array.
     */
    public BoardHandler(GameHandler gamePanel){
        System.out.println("BoardHandler - Constructor");
        this.gamePanel = gamePanel;
        this.matches = new ArrayList();
        this.fallingPieces = null;
        this.timer = new Timer(10, this);
        this.frame = 0;
        this.p1 = null;
        this.p2 = null;
        this.animationType = null;
        BoardGenerator board = new BoardGenerator();
        pieces = board.getBoard();    
    } // BoardHandler : Constructor
    
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
                if(each.willDrop && !each.type.equals(Piece.pieceType.DELETED))
                    System.out.println("BoardHandler - Pieces Collected");
                    collection.add(each);
            } // for : each
        } // for : piece[]
    } // collectFallingPieces
    
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
        gamePanel.addScore(score);
        gamePanel.setCombo(combo);
        System.out.println("BoardHandler - Marked");
    } // markDeleted
    /**
     * Calculates the drop from a resulting match
     */
    public void calculateDrop(){
        for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
            for(int row = BoardGenerator.VERTICAL_PIECES-1; row >=0; row--){
                Piece bottomPiece = this.getPieceAt(row, col);
                bottomPiece.beforeDrop = row;
                
                if(bottomPiece.type.equals(Piece.pieceType.DELETED)){
                    for(int temp = row-1; temp >= 0; temp--){
                        Piece topPiece = this.getPieceAt(temp, col);
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
        for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
            for(int row = BoardGenerator.VERTICAL_PIECES-1; row >= 0; --row){
                Piece piece = this.getPieceAt(row, col);
                
                if(piece.willDrop && !piece.type.equals(Piece.pieceType.DELETED)){
                    this.setPieceAt(row+piece.dropDistance, col, piece);
                    piece.setAnimCol(col);
                    this.setPieceAt(row, col, new Piece(Piece.pieceType.DELETED, row, col));
                } // if : willDrop && !DELETED
            } // for : row
        } // for : col
    } // applyDrop
    /**
     * Generates new pieces for pieces that are missing
     */
    public void fillEmpty(){
        System.out.println("BoardHandler - Filling Empty Spaces");
        for(int row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
                Piece piece = this.getPieceAt(row, col);
                if(piece.type.equals(Piece.pieceType.DELETED)){
                    System.out.println("DELETED FOUND");
                    Piece random = PieceHandler.generateRandom(row, col);
                    this.setPieceAt(row, col, random);
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
        for(int row = 0; row < BoardGenerator.VERTICAL_PIECES; row++){
            for(int col = 0; col < BoardGenerator.HORIZONTAL_PIECES; col++){
                Piece piece = this.getPieceAt(row, col);
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
    
    //***********ANIMATIONS***********
    /**
     * Sets the animation type of the object
     * @param animationType Sets the current animation type of the object, given animation type
     */
    public void setAnimationType(animationType animationType){
        this.animationType = animationType;
    } // setAnimationType
    /**
     * Returns the Animation type 
     * @return returns the current animation type
     */
    public animationType getAnimationType(){
        return animationType;
    } // getAnimationType
    /**
     * Tells the current frame the animation is in
     * @return the current frame number in sequence
     */
    public int getCurrentFrame(){
        return frame;
    } // getCurrentFrame
    
    public void animateSwap(Piece p1, Piece p2){
        this.p1 = p1;
        this.p2 = p2;
        timer.start();
    } // animateSwap
    /**
     * Ends swap animation
     */
    public void endSwapAnimation(){
        timer.stop();
        frame = 0;
        this.swapPieces(p1, p2);
        gamePanel.repaint();
        gamePanel.updateGame();
    } // endSwapAnimation
    /**
     * Animates the cascade for a time
     */
    public void animateCascade(){
        fallingPieces = new ArrayList();
        this.collectFallingPieces(fallingPieces);
        timer.start();
    } // animateCascade
    /**
     * Tells when to end the Cascade Animation
     */
    public void endCascadeAnimation(){
        timer.stop();
        frame = 0; 
        
        gamePanel.cleanBoard();
        gamePanel.repaint();
        gamePanel.updateGame();
    } // endCascadeAnimation
    
    public void actionPerformed(ActionEvent evt){
        if(animationType.equals(animationType.SWAP)){
            frame++;
            if(frame > 32){
                endSwapAnimation();
            } // if : frame
            else{
                int direction = 1;
                if(p1.col == p2.col){
                    if(p1.row < p2.row){
                        direction = 1;
                    } // if : row
                    else{
                        direction = -1;
                    } // else
                    p1.moveRow(2, direction);
                    p2.moveRow(2, -direction);
                } // if : col
                else{
                    if(p1.col < p2.col){
                        direction = 1;
                    } // if : col
                    else{
                        direction = -1;
                    } // else
                    p1.moveCol(2, direction);
                    p2.moveCol(2, -direction);
                } // else
                gamePanel.repaint();
            } // else
        } // if : animationType == SWAP
        else if(animationType.equals(animationType.CASCADE)){
            frame++;
            if(frame > 32){
                endCascadeAnimation();
            } // if : frame
            else{
                for(Piece piece : fallingPieces){
                    piece.moveRow(piece.dropDistance*2, 1);
                } // for : piece
                gamePanel.repaint();
            } // else
        } // else if : animationType == CASCADE
    } // actionPerformed   
} // BoardHandler
