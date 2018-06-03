package Chess;

import Chess.Side;

public class Pawn extends Piece {

	public Pawn(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanP.png");
		}
		else {
			super.loadImage("src/PurpleP.png");
		}
	}
}
