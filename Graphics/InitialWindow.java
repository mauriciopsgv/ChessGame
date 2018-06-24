package Graphics;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import Chess.Bishop;
import Chess.King;
import Chess.Knight;
import Chess.Pawn;
import Chess.Piece;
import Chess.Queen;
import Chess.Rook;
import Chess.Side;
import Chess.TurnManager;
 
@SuppressWarnings("serial")
public class InitialWindow extends JFrame
                             implements ActionListener {

	private int width = 300;
	private int height = 110;
	JPanel panel;
    JButton initGame, loadGame;
    JFileChooser fc;
    ArrayList<Piece> pieces;
    
    public InitialWindow() {
 
    	super("Chess Game");
    	
    	pieces = new ArrayList<Piece>();
    	this.setSize(width, height);
        fc = new JFileChooser();
        
        initGame = new JButton("Iniciar Partida");
        initGame.addActionListener(this);
 
        loadGame = new JButton("Carregar Partida Salva");
        loadGame.addActionListener(this);
        
        panel = new JPanel();
        panel.add(initGame);
        panel.add(loadGame);

        add(panel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
 
    public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == initGame) {
        	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == loadGame) {
            int returnVal = fc.showOpenDialog(InitialWindow.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                pieces = readFile(file);
            }         
        }
    }
    
    public ArrayList<Piece> getLoadedPieces()
    {
    	return pieces;
    }
    
    private ArrayList<Piece> readFile(File file)
    {
    	ArrayList<Piece> initialPieces = new ArrayList<Piece>();
    	
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            try {
				for(String line; (line = br.readLine()) != null; ) {
				    // process the line.
					String[] parts = line.split(" ");
					
					if(parts[0].equals("Side:"))
					{
						Side side;
						if(parts[1].equals("White")) {
							side = Side.WHITE;
						}
						else {
							side = Side.BLACK;
						}
						TurnManager.setCurrentSide(side);
						continue;
					}
					
					Side side;
					int row, column;
					if(parts[0].equals("White")) side = Side.WHITE;
					else side = Side.BLACK;
					row = Integer.parseInt(parts[2]);
					column = Integer.parseInt(parts[3]);
					if(parts[1].equals("Pawn")) {
						Pawn p = new Pawn(side, row, column);
						initialPieces.add(p);
					}
					else if(parts[1].equals("Rook")) {
						Rook r = new Rook(side, row, column);
						initialPieces.add(r);
					}
					else if(parts[1].equals("Bishop")) {
						Bishop r = new Bishop(side, row, column);
						initialPieces.add(r);
					}
					else if(parts[1].equals("Knight")) {
						Knight r = new Knight(side, row, column);
						initialPieces.add(r);
					}
					else if(parts[1].equals("Queen")) {
						Queen r = new Queen(side, row, column);
						initialPieces.add(r);
					}
					else if(parts[1].equals("King")){
						King r = new King(side, row, column);
						initialPieces.add(r);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // line is not visible here.
        } catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        return initialPieces;
    }
}