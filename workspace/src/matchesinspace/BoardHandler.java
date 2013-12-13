package matchesinspace;

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardHandler implements ActionListener{
    
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
    
    public BoardHandler(){
        BoardGenerator board = new BoardGenerator();
        pieces = board.getBoard();
    }
    
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
    
    public Piece getPieceAt(int row, int col){
        return pieces[row][col];
    }
    
    public void setPieceAt(int row, int col, Piece piece){
        piece.setAnimCol(col);                                                  
        piece.setAnimRow(row);                                                  
        pieces[row][col] = piece;
    }
    
    public void collectFallingPieces(ArrayList<Piece> collection){
        for(Piece piece[] : pieces){
            for(Piece each : piece){
                if(each.willDrop && !each.type.equals(Piece.pieceType.DELETED))
                    collection.add(each);
            }
        }
    }
    
    //***********ALGORITHMS***********
    
    public boolean isStable(){
        checkRows();
        checkColumns();
        return matches.isEmpty();
    }
    
    public void removeMatches(){
        markDeleted();
        calculateDrop();
        applyDrop();
        fillEmpty();
        endCascade();
    }
    
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
