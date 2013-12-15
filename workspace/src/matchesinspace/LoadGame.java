package matchesinspace;

import java.io.File;
import java.io.*;
import static matchesinspace.BoardGenerator.HORIZONTAL_PIECES;
import static matchesinspace.BoardGenerator.VERTICAL_PIECES;

public class LoadGame {
    
    public static Piece[][] pieces = new Piece[8][8];
    public static int score = 0;
    public static int level = 0;
    
    public static Piece[][] LoadPieces(){
        System.out.println("Loading");
        File file = new File("src/saves/save.dat");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            System.out.println("Save Found");
            for(int row = 0; row < VERTICAL_PIECES; row++){
                for(int col = 0; col < HORIZONTAL_PIECES; col++){
                    String type = reader.readLine();
                    System.out.println("Piece Read: "+type+".");
                    pieces[row][col] = PieceHandler.generatePiece(row, col, type);
                } // for : col
            } // for : row
            score = Integer.parseInt(reader.readLine());
            level = Integer.parseInt(reader.readLine());
            reader.close();
        } // try
        catch(Exception e){
            System.out.println(e.getMessage());
        } // catch
        
        System.out.println("Loaded");
        return pieces;
    } // 
    
    public static int getScore(){
        return score;
    } // getScore
    
    public static int getLevel(){
        return level;
    } // getLevel
    
} // LoadGame
