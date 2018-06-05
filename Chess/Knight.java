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
	
	private boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isKnightMovement(newPosition);
		}
		return false;
	}
	
	@Override
	protected boolean movePiece(Position newPosition) {
		if (this.canMoveTo(newPosition)) {
			return super.movePiece(newPosition);
		}
		return false;
	}
}
