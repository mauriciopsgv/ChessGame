package Chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Graphics.ScreenComponent;

public class Piece implements ImageObserver, ScreenComponent {
	
	protected Side side = Side.BLANK;
	
	protected BufferedImage image;
	
	protected int position[];
	
	protected Piece(Side side, int row, int column) {
		this.side = side;
		position = new int[2];
		position[0] = row;
		position[1] = column;
	}
	
	protected void loadImage(String imagePath) {
		try {                
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			System.out.println("Couldn't find image source file");
		}
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		// TODO Auto-generated method stub
		int horizontalPadding = (cellWidth - 64)/2;
		int verticalPadding = (cellHeight - 64)/2;
		g.drawImage(image, position[1] * cellHeight + verticalPadding, 
						   position[0] * cellWidth + horizontalPadding, this);
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
