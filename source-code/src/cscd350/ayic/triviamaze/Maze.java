package cscd350.ayic.triviamaze;

import cscd350.ayic.triviamaze.Cell.RoomState;

public class Maze {
	private static final int MAZESIZE = 8;
	private Cell[][] maze= new Cell[MAZESIZE][MAZESIZE];
	public Maze()
	{
		generateMaze();
	}
	private void generateMaze()	//TODO: Generate proper maze. Mazier.
	{
		for(int x = 0; x < MAZESIZE; x++)
		{
			for(int y = 0; y < MAZESIZE; y++)
			{
				maze[x][y] = new Room(x, y, new ShortAnswer( "The answer is 'Yes'", "yes"));
			}
		}
	}
	public Cell getCell(int x, int y) //TODO: Error checking
	{
		return maze[x][y];
	}
	public RoomState checkCell(int x, int y)
	{
		if( (x > 0 && x < MAZESIZE) && (y > 0 && y < MAZESIZE))	//Bounds check
		{
			return getCell(x, y).getState();
		}
		else
			return RoomState.SEALED;
	}
}
