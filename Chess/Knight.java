package Chess;

import Chess.Side;

public class Knight extends Piece {

	public Knight(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanN.png");
		}
		else {
			super.loadImage("src/PurpleN.png");
		}
	}
}
