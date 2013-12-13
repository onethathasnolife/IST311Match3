package matchesinspace;

import java.util.ArrayList;

public class BoardHandler {
    
    private Piece pieces[][];
    
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
}
