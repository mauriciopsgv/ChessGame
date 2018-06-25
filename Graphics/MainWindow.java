package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import Chess.Game;
import Interface.Operation;

public class MainWindow extends JFrame {
	
	private Canvas canvas;
		
	private int mainWindowWidth = 800;
	private int mainWindowHeight = 800;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.newGame();
	}
	
	public MainWindow(Game game) {
        super("Chess Game");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(mainWindowWidth, mainWindowHeight);
        
        canvas = new Canvas();
		
        this.setContentPane(canvas);
        super.pack();
        super.setVisible(true);
        
        game.addObserver(new Observer() {
			public void update(Observable obj, Object arg) {
                Operation op = (Operation) arg;
            	if (op.operationName.equals("showPromotePawnMenu")) {
            		showPromotePawnMenu();
            	} else if (op.operationName.equals("triggerAlert")) {
            		triggerAlert((String) op.arg);
            	} else if (op.operationName.equals("dispose")) {
            		dispose();
            	}
            }
        });
        
        newGame(game);
	}
	
	public void newGame(Game game) {
		canvas.newGame(game);
	}
	
   public void showPromotePawnMenu(){
	  JFrame popup = new JFrame();
	  popup.setSize(100, 250);

	  JPopupMenu promotePawnMenu = new JPopupMenu("PromotePawn"); 
	
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
	  
	  PromotePawnOptionListener listener = new PromotePawnOptionListener(canvas.game, this);
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
	  promotePawnMenu.setVisible(true);

	  popup.add(promotePawnMenu);
	  popup.setLocation(mainWindowWidth/2, mainWindowHeight/2);
	  popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  popup.setVisible(true); 
   }
	
	public void triggerAlert(String alertMessage) {
		int alertWidth = 300;
		int alertHeight = 100;
		JFrame alertPanel = new JFrame();

		JLabel alertLabel = new JLabel(alertMessage);
		alertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		alertLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		alertPanel.add(alertLabel);

		alertPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertPanel.setSize(alertWidth, alertHeight);
		alertPanel.setLocation((mainWindowWidth - alertWidth)/2, (mainWindowHeight - alertHeight)/2);
		alertPanel.setVisible(true);
	}
	
	class PromotePawnOptionListener implements ActionListener {
		private Game game;
		private MainWindow window;
		
		PromotePawnOptionListener (Game game, MainWindow window) {
			this.game = game;
			this.window = window;
		}
		public void actionPerformed(ActionEvent e) {            
	         game.promotePawn(e.getActionCommand());
	         window.repaint();
		}
	}
}
