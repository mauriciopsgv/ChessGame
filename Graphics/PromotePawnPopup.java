package Graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Chess.Game;

public class PromotePawnPopup {

	public PromotePawnPopup(Game game) {
		
        JFrame frame = new JFrame("PromotePawnMenu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PromotePawnMenu(game));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	class PromotePawnMenu extends JPanel {
		private JPopupMenu promotePawnMenu;

		public PromotePawnMenu(Game game) {
			promotePawnMenu = new JPopupMenu("PromotePawn"); 
			
			JMenuItem bishopOption = new JMenuItem("Bishop");
			bishopOption.setActionCommand("Bishop");
			
			JMenuItem knightOption = new JMenuItem("Knight");
			knightOption.setActionCommand("Knight");
			
			JMenuItem queenOption = new JMenuItem("Queen");
			queenOption.setActionCommand("Queen");
			
			JMenuItem pawnOption = new JMenuItem("Pawn");
			pawnOption.setActionCommand("Pawn");
			
			JMenuItem rookOption = new JMenuItem("Rook");
			rookOption.setActionCommand("Rook");
			
			MenuItemListenerGambiarra listener = new MenuItemListenerGambiarra(game);
			bishopOption.addActionListener(listener);
			knightOption.addActionListener(listener);
			queenOption.addActionListener(listener);
			pawnOption.addActionListener(listener);
			rookOption.addActionListener(listener);
			
			promotePawnMenu.add(bishopOption);
			promotePawnMenu.add(knightOption);
			promotePawnMenu.add(queenOption);
			promotePawnMenu.add(pawnOption);
			promotePawnMenu.add(rookOption);
			
			setComponentPopupMenu(promotePawnMenu);
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(100,250);
		}
	}
	
	class MenuItemListenerGambiarra implements ActionListener {
		private Game game;
		
		MenuItemListenerGambiarra (Game game) {
			this.game = game;
		}
		public void actionPerformed(ActionEvent e) {            
	         game.promotePawn(e.getActionCommand());
		}
	}
}
