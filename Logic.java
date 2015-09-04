package gamesEx2;

import java.util.Random;

public class Logic {
	private int[][] board;
	private boolean moved; 
	private boolean win;
	private boolean gameOver;
	
	final int winsum = 1024;
	
	public Logic(int boardSize)
	{
		win = false;
		gameOver = false;
		board=new int[boardSize][boardSize];
		for(int i=0; i<boardSize; i++)
			for(int j=0; j<boardSize; j++)
			{
				board[i][j]=0;
			}
	}
	
	public int[][] getBoard()
	{
		return this.board;
	}
	
	public void setBoard(int[][] newBoard)
	{
		for(int i=0; i<board.length; i++)
			  for(int j=0; j<board[i].length; j++)
			    board[i][j]=newBoard[i][j];
	}
	
	private boolean isBoardFull()
	{
		for(int i=0; i<board.length; i++)
			  for(int j=0; j<board[i].length; j++)
			    if(board[i][j] == 0)
			    	return false;
		return true;
	}
	
	public void moveLeft()
	{
		moved = false;
		for(int i=0; i<board.length; i++)
			  for(int j=0; j<board.length; j++)
			  {
				  shiftLeft(i, j);
				  if(board[i][j] != 0 && j+1 < board.length)
				  {
					  if(board[i][j] == board[i][j+1])
					  	{
						  	moved = true;
						  	board[i][j] = (board[i][j])*2;
						  	if(board[i][j] == winsum)
						  		win = true;
						  	board[i][j+1] = 0;
						  	shiftLeft(i, j+1);
						}
				  }
			  }
		if(!moved && isBoardFull())
			gameOver = true;
	}
	
	public void moveDown()
	{
		moved = false;
		for(int i=board.length-1; i>=0; i--)
			  for(int j=board.length-1; j>=0; j--)
			  {
				  shiftDown(i, j);
				  if(board[i][j] != 0 && i-1 >= 0)
				  {
					  if(board[i][j] == board[i-1][j])
					  	{
						  	moved = true;
						  	board[i][j] = (board[i][j])*2;
						  	if(board[i][j] == winsum)
						  		win = true;
						  	board[i-1][j] = 0;
						  	shiftDown(i-1, j);
						}
				  }
			  }
		if(!moved && isBoardFull())
			gameOver = true;
	}
	
	public void moveUp()
	{
		moved = false;
		for(int i=0; i<board.length; i++)
			  for(int j=0; j<board.length; j++)
			  {
				  shiftUp(i, j);
				  if(board[i][j] != 0 && i+1 < board.length)
				  {
					  if(board[i][j] == board[i+1][j])
					  	{
						  	moved = true;
						  	board[i][j] = (board[i][j])*2;
						  	if(board[i][j] == winsum)
						  		win = true;
						  	board[i+1][j] = 0;
						  	shiftUp(i+1, j);
						}
				  }
			  }
		if(!moved && isBoardFull())
			gameOver = true;
	}
	
	public void moveRight()
	{
		moved = false;
		for(int i=board.length-1; i>=0; i--)
			  for(int j=board.length-1; j>=0; j--)
			  {
				  shiftRight(i, j);
				  if(board[i][j] != 0 && j-1 >= 0)
				  {
					  if(board[i][j] == board[i][j-1])
					  	{
						  	moved = true;
						  	board[i][j] = (board[i][j])*2;
						  	if(board[i][j] == winsum)
						  		win = true;
						  	board[i][j-1] = 0;
						  	shiftRight(i, j-1);
						}
				  }
			  }
		if(!moved && isBoardFull())
			gameOver = true;
	}
	
	private void shiftLeft(int i, int j)
	{
		int start = j;
		for(int k=board.length-j; k>0; k--)
		{
			if(board[i][j] == 0)
			{
				while(j+1 < board.length)
				{
					if(board[i][j+1] != 0)
						moved = true;
					board[i][j] = board[i][j+1];
					j++;
				}
				board[i][j] = 0;
			}
				while(board[i][start] != 0 && start+1 < board.length)
					start++;
				j = start;
				if(j == board.length)
					break;
		}
	}
	
	private void shiftRight(int i, int j)
	{
		int start = j;
		for(int k=board.length-1; k>0; k--)
		{
			if(board[i][j] == 0)
			{
				while(j-1 >= 0)
				{
					if(board[i][j-1] != 0)
						moved = true;
					board[i][j] = board[i][j-1];
					j--;
				}
				board[i][j] = 0;
			}
				while(board[i][start] != 0 && start-1 >= 0)
					start--;
				j = start;
				if(j < 0)
					break;
		}
	}
	
	private void shiftUp(int i, int j)
	{
		int start = i;
		for(int k=board.length-i; k>0; k--)
		{
			if(board[i][j] == 0)
			{
				while(i+1 < board.length)
				{
					if(board[i+1][j] != 0)
						moved = true;
					board[i][j] = board[i+1][j];
					i++;
				}
				board[i][j] = 0;
			}
				while(board[start][j] != 0 && start+1 < board.length)
					start++;
				i = start;
				if(i==board.length)
					break;
		}
	}
	
	private void shiftDown(int i, int j)
	{
		int start = i;
		for(int k=board.length-1; k>0; k--)
		{
			if(board[i][j] == 0)
			{
				while(i-1 >= 0)
				{
					if(board[i-1][j] != 0)
						moved = true;
					board[i][j] = board[i-1][j];
					i--;
				}
				board[i][j] = 0;
			}
				while(board[start][j] != 0 && start-1 >= 0)
					start--;
				i = start;
				if(i < 0)
					break;
		}
	}
	
	public void addNewNum()
	{
		if(moved)
		{
			Random rand = new Random();
			int rnd1, rnd2,rnd3;
			do
			{
				rnd1 = (rand.nextInt(board.length) + 1)-1;
				rnd2 = (rand.nextInt(board.length) + 1)-1;
				rnd3=rand.nextInt(4) + 1;

				if(rnd3>2)
					rnd3=4;
				else
					rnd3=2;

			}while(board[rnd1][rnd2] != 0);
			board[rnd1][rnd2] = rnd3;
		}
	}
	
	public boolean isWin()
	{
		return win;
	}
	
	public boolean isLoose()
	{
		return gameOver;
	}
}
