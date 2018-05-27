package Chess;

import java.awt.image.BufferedImage;

public interface Piece {
	enum Side { BLACK, WHITE, BLANK }
	Side side = Side.BLANK;
	BufferedImage image = new BufferedImage(0, 0, 0);
}
