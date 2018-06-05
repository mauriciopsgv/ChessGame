package Chess;

import Chess.Side;

public class Rook extends Piece {
	
	public Rook(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanR.png");
		}
		else {
			super.loadImage("src/PurpleR.png");
		}
	}
	
	@Override
	protected boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isVertical(newPosition) || isHorizontal(newPosition);
		}
		return false;
	}
}
