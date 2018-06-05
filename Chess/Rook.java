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
	
	private boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isVertical(newPosition) || isHorizontal(newPosition);
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
