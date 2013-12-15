
package matchesinspace;

import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.image.BufferedImage;

/**
 * Loads information related to images found in the game
 */
public class ImageLibrary {
    /**
     * @field imageCollection list of all images
     * @field DIRECTORY string of directory to find images
     */
    private HashMap<Piece.pieceType, BufferedImage> imageCollection;
    static final String DIRECTORY = "/src/resources/images/";
    
    public ImageLibrary(){
        imageCollection = new HashMap(8);
        BufferedImage img;
        
        try{
            img = ImageIO.read(new File(DIRECTORY + "bluePlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "greenPlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "greyPlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "orangePlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "purplePlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "redPlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "yellowPlanet.png"));
        } // try
        catch(IOException e){
            System.out.println(e.getMessage());
        } // catch
        
    } // ImageLibrary
    
    BufferedImage getImage(Piece.pieceType type){
        return imageCollection.get(type);
    }
    
}
