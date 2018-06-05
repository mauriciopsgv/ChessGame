package Chess;

import Chess.Side;

public class King extends Piece {

	public King(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanK.png");
		}
		else {
			super.loadImage("src/PurpleK.png");
		}
	}
	
	private boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isNear(newPosition, 1);
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
