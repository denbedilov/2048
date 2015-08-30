package gamesEx2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	public static final int WIDTH = 500, HEIGHT = 500;
	private final int boardSize=2;
	private final int squareSize=120;
	private Logic logic = new Logic(boardSize);

	public GamePanel()
	{
		initializeBoard();
		setFocusable(true);
        requestFocus();
		addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				int key = e.getKeyCode();
			    switch( key ) 
			    {
			        case KeyEvent.VK_UP:	 logic.moveUp();		break;
			        case KeyEvent.VK_DOWN:	 logic.moveDown();		break;
			        case KeyEvent.VK_LEFT:	 logic.moveLeft();		break;
			        case KeyEvent.VK_RIGHT:  logic.moveRight();		break;
			        default:										break;
			    }
			    logic.addNewNum();
				repaint();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawBoard(g2d);
	}
	
	public void initializeBoard()
	{
		Random rand = new Random();
		int[][] board=logic.getBoard();
		//random square
		int rect1Row = (rand.nextInt(boardSize) + 1)-1;
		int rect1Col=(rand.nextInt(boardSize) + 1)-1;
		int rect2Row = (rand.nextInt(boardSize) + 1)-1;
		int rect2Col=(rand.nextInt(boardSize) + 1)-1;
		//random number 2 or 4
		int rNum1=rand.nextInt(4) + 1;
		int rNum2=rand.nextInt(4) + 1;

		if(rNum1>2)
			rNum1=4;
		else
			rNum1=2;

		if(rNum2>2)
			rNum2=4;
		else
			rNum2=2;

		board[rect1Row][rect1Col]=rNum1;
		board[rect2Row][rect2Col]=rNum2;
		logic.setBoard(board);
	}
	
	public void drawBoard(Graphics2D g2d)
	{
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(100, 100, WIDTH,HEIGHT);    
		int x=squareSize;
		int y=squareSize;
		int[][] board=logic.getBoard();
		g2d.setColor(Color.GRAY);
		for(int i=0; i<boardSize ; i++)
		{
			for(int j=0; j<boardSize; j++)
			{
				g2d.setColor(chooseColor(board[i][j]));
				g2d.fillRect(x,y,100,100);
				g2d.setColor(Color.BLACK);
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
				if(board[i][j] != 0)
				{
					g2d.drawString(String.valueOf(board[i][j]), x+45, y+60);
				}
				x+=squareSize;
				g2d.setColor(Color.gray);
			}
			x=squareSize;
			y+=squareSize;
		}
	}

	public Color chooseColor(int i)
	{
		switch(i)
		{
		case 2:
			return new Color(10, 50, 50, 80);
		case 4:
			return new Color(189, 189, 163, 80);
		case 8:
			return new Color(255, 171, 0, 80);
		case 16:
			return new Color(255, 94, 0, 80);
		case 32:
			return new Color(233, 44, 44, 80);
		case 64:
			return new Color(255, 15, 15, 80);
		case 128:
			return new Color(255, 144, 31, 80);
		case 256:
			return new Color(238, 238, 63, 80);
		case 512:
			return new Color(34, 0, 255, 80);
		case 1024:
			return new Color(171, 0, 255, 80);
		case 2048:
			return new Color(0, 255, 171, 80);
		default:
			return Color.gray;
		}
	}
	
	public Logic getLogic()
	{
		return logic;
	}
}
