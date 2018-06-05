package Chess;

import Chess.Side;

public class Queen extends Piece {

	public Queen(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanQ.png");
		}
		else {
			super.loadImage("src/PurpleQ.png");
		}
	}
	
	private boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isVertical(newPosition) || isHorizontal(newPosition) || isDiagonal(newPosition);
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
