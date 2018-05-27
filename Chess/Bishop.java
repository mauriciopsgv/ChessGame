package Chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Graphics.ScreenComponent;

public class Bishop implements Piece, ScreenComponent, ImageObserver{
	
	private Side side;
	private BufferedImage image;
	public Bishop(Side side) {
		this.side = side;
		
		if (side == Side.WHITE) {
	    try {                
	          image = ImageIO.read(new File("src/CyanB.png"));
	       } catch (IOException ex) {
	            // tryfodase
	       }
		}
		else {
		    try {                
		          image = ImageIO.read(new File("src/PurpleB.png"));
		       } catch (IOException ex) {
		            // tryfodase
		       }
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
