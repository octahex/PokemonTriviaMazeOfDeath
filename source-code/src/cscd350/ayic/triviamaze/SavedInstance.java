package cscd350.ayic.triviamaze;

import java.awt.Point;

public class SavedInstance
{
	private Point location;
	private int[][] _cells;
	
	public SavedInstance(String code)
	{
		_cells = new int[Maze.MAZESIZE][Maze.MAZESIZE];
		load(code);
	}
	
	private void load(String code)
	{
		for(int i=0; i<_cells.length; i++)
		{
			for(int j=0; j<_cells[0].length; j++)
			{
				_cells[i][j] = code.charAt(i*Maze.MAZESIZE+j);
			}
		}

		location = new Point(code.charAt(Maze.MAZESIZE*Maze.MAZESIZE), code.charAt(Maze.MAZESIZE*Maze.MAZESIZE+1));
	}
}
