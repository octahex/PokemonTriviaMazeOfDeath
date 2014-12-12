package cscd350.ayic.triviamaze;

import java.awt.Point;

import javax.swing.JOptionPane;

import cscd350.ayic.gui.MiniMapPanel;
import cscd350.ayic.gui.QuestionPanel;
import cscd350.ayic.gui.RoomPanel;
import cscd350.ayic.triviamaze.Cell.RoomState;
import cscd350.ayic.utility.DataBase;

public class Tracker
{
	protected Maze _maze;
	protected Point _currentLocation;

	public Tracker(Maze maze, int x, int y)
	{
		_maze = maze;
		_currentLocation = new Point(x, y);
	}

	public int getX()
	{
		return _currentLocation.x;
	}

	public int getY()
	{
		return _currentLocation.y;
	}

	public void moveWest()
	{
		move(getX() - 1, getY());
	}

	public void moveEast()
	{
		move(getX() + 1, getY());
	}

	public void moveNorth()
	{
		move(getX(), getY() - 1);
	}

	public void moveSouth()
	{
		move(getX(), getY() + 1);
	}

	public void move(int x, int y)
	{
		if (_maze.checkCell(x, y) == RoomState.UNLOCKED)
		{
			_currentLocation.setLocation(x, y);
		}
		if (_maze.checkCell(x, y) == RoomState.LOCKED)
		{
			Room room = (Room) _maze.getCell(x, y);
			QuestionPanel.getInstance().setRoom(room);
		}

		MiniMapPanel.getInstance().repaint();
		RoomPanel.getInstance().repaint();
	}

	public void checkGameOver()
	{
		if (_maze.checkCell(Maze.MAZESIZE - 1, Maze.MAZESIZE - 1) == RoomState.SEALED
				|| !checkPaths())
			youLose();

		if (_currentLocation.x == Maze.MAZESIZE - 1
				&& _currentLocation.y == Maze.MAZESIZE - 1)
		{
			youWin();
		}
	}

	private void youWin()
	{
		int dialog = JOptionPane.showConfirmDialog(null,
				"Congratulations, you win!!\n"
						+ "Would you like to play again?", "Game Over",
				JOptionPane.YES_NO_OPTION);
		if (dialog == JOptionPane.NO_OPTION)
			System.exit(0);
		else
			reset();
	}

	private void youLose()
	{
		int dialog = JOptionPane.showConfirmDialog(null, "Uh Oh! You lose!!\n"
				+ "Would you like to play again?", "Game Over",
				JOptionPane.YES_NO_OPTION);
		if (dialog == JOptionPane.NO_OPTION)
			System.exit(0);
		else
			reset();
	}

	public void reset()
	{
		_maze.reset();
		_currentLocation = new Point(0, 0);
		MiniMapPanel.getInstance().repaint();
		RoomPanel.getInstance().repaint();
		QuestionPanel.getInstance().reset();
	}

	private boolean checkPaths()
	{
		int[][] maze = getIntMaze();
		return checkPath(maze, _currentLocation.x, _currentLocation.y);
	}

	private boolean checkPath(int[][] maze, int x, int y)
	{
		if (!_maze.inBounds(x, y) || maze[y][x] == 0)
			return false;

		if (x == Maze.MAZESIZE - 1 && y == Maze.MAZESIZE - 1)
			return true;

		maze[y][x] = 0;

		return checkPath(maze, x + 1, y) || checkPath(maze, x - 1, y)
				|| checkPath(maze, x, y + 1) || checkPath(maze, x, y - 1);
	}

	private int[][] getIntMaze()
	{
		int[][] maze = new int[Maze.MAZESIZE][Maze.MAZESIZE];

		for (int y = 0; y < Maze.MAZESIZE; y++)
			for (int x = 0; x < Maze.MAZESIZE; x++)
				switch (_maze.checkCell(x, y))
				{
				case UNLOCKED:
					maze[y][x] = 1;
					break;
				case LOCKED:
					maze[y][x] = 1;
					break;
				case SEALED:
					maze[y][x] = 0;
				}

		return maze;
	}

	public void save()
	{
		String saveName = JOptionPane.showInputDialog(null,
				"Choose a save name:", "Save name",
				JOptionPane.OK_CANCEL_OPTION);

		if (saveName == null)
			return;

		String code = "";

		for (int y = 0; y < Maze.MAZESIZE; y++)
			for (int x = 0; x < Maze.MAZESIZE; x++)
				switch (_maze.checkCell(x, y))
				{
				case UNLOCKED:
					code += 0;
					break;
				case LOCKED:
					code += 1;
					break;
				case SEALED:
					code += 2;
				}

		code += getX();
		code += getY();

		DataBase.getInstance().save(saveName, code);
	}

	public void load(int id)
	{
		String code = DataBase.getInstance().retrieveSave(id);
		reset();
		_currentLocation.setLocation(
				code.charAt(Maze.MAZESIZE * Maze.MAZESIZE) - '0',
				code.charAt(Maze.MAZESIZE * Maze.MAZESIZE + 1) - '0');
		for (int r = 0; r < Maze.MAZESIZE; r++)
			for (int c = 0; c < Maze.MAZESIZE; c++)
			{
				int num = code.charAt(r * Maze.MAZESIZE + c) - '0';
				switch (num)
				{
				case 0:
					_maze.getCell(c, r).setState(RoomState.UNLOCKED);
					break;
				case 1:
					_maze.getCell(c, r).setState(RoomState.LOCKED);
					break;
				case 2:
					_maze.getCell(c, r).setState(RoomState.SEALED);
					break;
				default:
					_maze.getCell(c, r).setState(RoomState.LOCKED);
					break;
				}
			}
	}
}
