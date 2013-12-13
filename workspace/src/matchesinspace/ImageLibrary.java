/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchesinspace;

import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.image.BufferedImage;

/**
 *
 * @author Bob
 */
public class ImageLibrary {
    
    private HashMap<Piece.pieceType, BufferedImage> imageCollection;
    static final String DIRECTORY = "/src/resources/images/";
    
    public ImageLibrary(){
        imageCollection = new HashMap(8);
        BufferedImage img;
        
        try{
            img = ImageIO.read(new File(DIRECTORY + "bluePlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "greenPlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "greyPlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "orangePlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "purplePlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "redPlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try{
            img = ImageIO.read(new File(DIRECTORY + "yellowPlanet.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    BufferedImage getImage(Piece.pieceType type){
        return imageCollection.get(type);
    }
    
}
