package Chess;

import Chess.Side;

public class Bishop extends Piece {

	public Bishop(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanB.png");
		}
		else {
			super.loadImage("src/PurpleB.png");
		}
	}
}
