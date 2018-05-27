package Chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Chess.Piece.Side;
import Graphics.ScreenComponent;

public class Knight implements Piece, ScreenComponent, ImageObserver{

	private Side side;
	private BufferedImage image;
	public Knight(Side side) {
		this.side = side;
		if (side == Side.WHITE) {
		    try {                
		          image = ImageIO.read(new File("src/CyanN.png"));
		       } catch (IOException ex) {
		            // tryfodase
		       }
			}
			else {
			    try {                
			          image = ImageIO.read(new File("src/PurpleN.png"));
			       } catch (IOException ex) {
			            // tryfodase
			       }
			}
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
