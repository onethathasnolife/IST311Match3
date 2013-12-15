
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
    static final String DIRECTORY = "src/resources/images/";
    
    public ImageLibrary(){
        imageCollection = new HashMap(8);
        BufferedImage img;
        System.out.println("ImageLibrary - Building");
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemBlue.png"));
            imageCollection.put(Piece.pieceType.BLUE_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "bluePlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemGreen.png"));
            imageCollection.put(Piece.pieceType.GREEN_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "greenPlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemWhite.png"));
            imageCollection.put(Piece.pieceType.GREY_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "greyPlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemOrange.png"));
            imageCollection.put(Piece.pieceType.ORANGE_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "orangePlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemPurple.png"));
            imageCollection.put(Piece.pieceType.PURPLE_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "purplePlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemRed.png"));
            imageCollection.put(Piece.pieceType.RED_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "redPlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "gemYellow.png"));
            imageCollection.put(Piece.pieceType.YELLOW_PLANET, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "yellowPlanet.png");
        } // catch
        try{
            img = ImageIO.read(new File(DIRECTORY + "focus.png"));
            imageCollection.put(Piece.pieceType.FOCUSED, img);
        } // try
        catch(IOException e){
            System.out.println(e.getMessage()+DIRECTORY + "focus.png");
        }
        System.out.println("ImageLibrary - Built");
    } // ImageLibrary
    
    BufferedImage getImage(Piece.pieceType type){
        return imageCollection.get(type);
    } // getImage
    
} // ImageLibrary
