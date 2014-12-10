package cscd350.ayic.triviamaze;

import java.util.Random;

import cscd350.ayic.triviamaze.Cell.RoomState;

public class Maze
{
	public static final int MAZESIZE=8;
	private static QuestionFactory qFactory;
	private Cell[][] maze;
	private boolean _gameOver;

	public Maze()
	{
		reset();
	}
	
	public void reset()
	{
		qFactory=new QuestionFactory();
		maze=new Cell[MAZESIZE][MAZESIZE];
		generateMaze();
	}
	
	public void load(SavedInstance instance)
	{
		
	}

	private void generateMaze() // TODO: Generate proper maze. Mazier.
	{
		Random gen = new Random();
		for (int x=0; x<MAZESIZE; x++)
		{
			for (int y=0; y<MAZESIZE; y++)
			{
				maze[y][x]=new Room(x, y, qFactory.getRandomQuestion());
				if(x==0||y==0||x==MAZESIZE-1||y==MAZESIZE-1||x-y==0||x-y==1)
					maze[y][x].setState(RoomState.LOCKED);
			}
		}
		maze[0][0].setState(RoomState.UNLOCKED);
	}

	public Cell getCell(int x, int y)
	{
		if (!inBounds(x, y))
			return null;

		return maze[y][x];
	}

	public RoomState checkCell(int x, int y)
	{
		if (inBounds(x, y))
			return getCell(x, y).getState();
		else
			return RoomState.SEALED;
	}

	public boolean inBounds(int x, int y)
	{
		return (x>=0&&x<MAZESIZE)&&(y>=0&&y<MAZESIZE);
	}
	
	public boolean isGameOver()
	{
		return _gameOver;
	}
	
	public void setGameOver(boolean b)
	{
		_gameOver = b;
	}
}
