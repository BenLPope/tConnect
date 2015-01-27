
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class tConnect extends JApplet implements ActionListener, MouseListener{
	
	Canvas  canvas;
	JLabel infoLabel;
	JButton b1;
	//JTextField scoreTextField;
	private final int boardSize = 700;
	boolean gameWon = false;
	int player = 1;
	int gameScore;


	int[][] Peices;
	
	public void mouseEntered(MouseEvent evt){}
	public void mouseExited(MouseEvent evt){}
	public void mouseClicked(MouseEvent evt){}
	public void mouseMoved(MouseEvent evt){}
	public void mouseDragged(MouseEvent evt){}
	public void mouseReleased(MouseEvent evt){}
	
	private class Canvas extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, boardSize, boardSize-100);
			int xloc, yloc;
				
			for(int i=1; i <8; i++){
				for(int j=1; j<7; j++){
					g.setColor(Color.black);
					g.drawRect((i-1)*100, (j-1)*100, 100,100);
					if (Peices[i][j] == 0)
						g.setColor(Color.gray);
					else if (Peices[i][j] == 1 )
						g.setColor(Color.yellow);
					else if (Peices[i][j] == 2 )
						g.setColor(Color.red);
				
				g.fillOval(((i-1)*100)+1, ((j-1)*100)+1, 99, 99);	
					
				}
			}

		}         

	}

	public void actionPerformed( ActionEvent acEvent) {
		String aC = acEvent.getActionCommand();
		if (aC == "New Game"){
			resetBoard();
			infoLabel.setText("Player 1's Turn");
			b1.setEnabled(false);
			gameWon = false;
			repaint();}
	}

	public void mousePressed(MouseEvent evt){
		int x = evt.getX();
		int col = (x/100)+1;
		
		

		if (b1.isEnabled() && gameWon == false)
			infoLabel.setText("Press the New Game button");
		else if(!b1.isEnabled())
		{
		for (int i = 6; i >= 0; i--)
		{	if (Peices[col][i] == 0)
				{
			
				Peices[col][i] = player;
				if (player ==1){
					if(isWin()== player){
						infoLabel.setText("Player 1 has won!");
						b1.setEnabled(true);
						repaint();
						gameWon = true;
						return;}
					
					player = 2;
					infoLabel.setText("Player 2's Turn");}
				
				else if (player ==2){
					if(isWin()== player){
						infoLabel.setText("Player 2 has won!");
						b1.setEnabled(true);
						repaint();
						gameWon = true;
						return;}
					
					player = 1;
				    infoLabel.setText("Player 1's Turn");
				    }
				break;}
		
		else if(Peices[col][i]==-1)
				infoLabel.setText("Cannot Place Peiece");
		}
		
		if(isWin() == 3){
					infoLabel.setText("Tie Game! Everyone loses!");
					b1.setEnabled(true);
					repaint();
					gameWon = true;
					return;}
		repaint();}
		
	}
	 

	public void init() {
		createComponents();

		Peices = new int[9][8];
		resetBoard();

	}

	private void resetBoard(){
		for (int i = 0; i < 9; i++)
			for(int j = 0; j <8; j++)
				Peices[i][j]= 0;
		
		for (int i = 0; i < 8; i++)
			{Peices[0][i] = -1;
			Peices[8][i]= -1;}
		for (int i = 0; i < 9; i++)
			{Peices [i][0] = -1;
		    Peices [i][7] = -1;}
		infoLabel.setText("");
		
	}

	public int isWin(){
		int topRowCount = 0;
		
		
	for (int i = 1; i < 8; i++){
		for(int j = 1; j<7; j++)
			if (Peices[i][j] == player){
				if(Peices[i+1][j]== -1)
					break;
				else if(Peices[i+1][j]== player)
					if(Peices[i+2][j] == -1)
						break;
					else if(Peices[i+2][j]== player)
						if(Peices[i+1][j+1] == player || Peices[i+1][j-1] == player )
							return player;
				if(Peices[i][j+1]==-1)
					break;
				else if(Peices[i][j+1]== player)
					if(Peices[i][j+2] == -1)
						break;
					else if(Peices[i][j+2]== player)
						if(Peices[i+1][j+1] == player || Peices[i-1][j+1] == player)
							return player;
			}}				
		for(int i = 1; i<8; i++)
			{if(Peices[i][1]!=0)
				topRowCount++;
			if(topRowCount == 7)
				return 3;}
		return -1;
			
	}
	

	private void createComponents() {
		JPanel content = new JPanel();
		content.setLayout( new BorderLayout());

		JPanel uip = new JPanel();
		uip.setLayout( new FlowLayout());


		infoLabel = new JLabel("");
		uip.add(infoLabel);

		b1 = new JButton("New Game");
		uip.add(b1);
		b1.addActionListener(this);
		b1.setEnabled(true);
		

		canvas = new Canvas();
		

		canvas.addMouseListener(this);
		setSize(boardSize+1, boardSize-68);

		content.add(canvas, BorderLayout.CENTER);
		content.add(uip, BorderLayout.NORTH);
		setContentPane(content);
	}



}
