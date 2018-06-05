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
	
	private boolean isKnightMovement(Position newPosition) {
		return (Math.abs(newPosition.column - getColumn()) == 2) && (Math.abs(newPosition.row - getRow()) == 1) ||
				(Math.abs(newPosition.column - getColumn()) == 1) && (Math.abs(newPosition.row - getRow()) == 2);
	}
	
	@Override
	protected boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isKnightMovement(newPosition);
		}
		return false;
	}
}
