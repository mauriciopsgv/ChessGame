package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.ScreenComponent;

public class Board implements ScreenComponent {
	
	public Board() {
		
	}
	
	private boolean toggleBool(boolean bool) {
		return !bool;
	}
	
	public void draw(Graphics g) {
		boolean isBlackSquare = true;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (isBlackSquare) {
					g.setColor(new Color(0,0,0));
				} else {
					g.setColor(new Color(255,255,255));
				}
				g.fillRect(100*col, 100*row, 100, 100);
				isBlackSquare = toggleBool(isBlackSquare);
			}
			isBlackSquare = toggleBool(isBlackSquare);
		}
	}
}
