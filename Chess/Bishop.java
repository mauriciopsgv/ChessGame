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
	
	private boolean canMoveTo(int newRow, int newColumn) {
		return Math.abs(newRow - this.getRow()) == Math.abs(newColumn - this.getColumn());
	}
	
	@Override
	protected boolean movePiece(int newRow, int newColumn) {
		if (this.canMoveTo(newRow, newColumn)) {
			return super.movePiece(newRow, newColumn);
		}
		return false;
	}
}
