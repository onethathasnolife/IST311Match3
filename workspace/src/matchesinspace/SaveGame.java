package matchesinspace;

import static matchesinspace.BoardGenerator.HORIZONTAL_PIECES;
import static matchesinspace.BoardGenerator.VERTICAL_PIECES;
import java.io.PrintWriter;
import java.io.File;

public class SaveGame {
    
    public static void Save(){
        System.out.println("Saving");
        Piece[][] pieces = Board.getPieces();
        
        try{
            File file = new File ("src/saves/save.dat");
            PrintWriter printWriter = new PrintWriter ("src/saves/save.dat");
            
            for(int row = 0; row < VERTICAL_PIECES; row++){
                for(int col = 0; col < HORIZONTAL_PIECES; col++){
                    printWriter.println (pieces[row][col].type.toString());      
                }
            }
            printWriter.println(GameUI.getScore());
            printWriter.println(GameUI.getLevel());
            printWriter.close ();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Saved");
    }

}
