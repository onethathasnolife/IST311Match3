package matchesinspace;

import static matchesinspace.BoardGenerator.HORIZONTAL_PIECES;
import static matchesinspace.BoardGenerator.VERTICAL_PIECES;
import java.io.PrintWriter;
import java.io.File;
/**
 * 
 * Handles information to saving the game
 *
 */
public class SaveGame {
    /**
     * Saves the game, takes the array of pieces and creates a save file to be loaded.
     */
    public static void Save(){
        System.out.println("Saving");
        Piece[][] pieces = Board.getPieces();
        
        try{
            File file = new File ("src/saves/save.dat");
            PrintWriter printWriter = new PrintWriter ("src/saves/save.dat");
            
            for(int row = 0; row < VERTICAL_PIECES; row++){
                for(int col = 0; col < HORIZONTAL_PIECES; col++){
                    printWriter.println (pieces[row][col].type.toString());      
                }//for:col < HORIZONTAL_PIECES
            }//for:row < VERTICAL_PIECES
            printWriter.println(GameUI.getScore());
            printWriter.println(GameUI.getLevel());
            printWriter.close ();
        }//try
        catch(Exception e){
            System.out.println(e.getMessage());
        }//catch
        System.out.println("Saved");
    }//Save

}//SaveGame
