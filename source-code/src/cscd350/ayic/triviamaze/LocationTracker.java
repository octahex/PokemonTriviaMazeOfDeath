package cscd350.ayic.triviamaze;

import java.awt.Point;

import javax.swing.JOptionPane;

import cscd350.ayic.gui.MiniMapPanel;
import cscd350.ayic.gui.QuestionPanel;
import cscd350.ayic.gui.RoomPanel;
import cscd350.ayic.triviamaze.Cell.RoomState;

public class LocationTracker
{
	protected Maze _maze;
	protected Point _currentLocation;
	
	public LocationTracker(Maze maze, int x, int y)
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
		move(getX()-1, getY());
	}

	public void moveEast()
	{
		move(getX()+1, getY());
	}

	public void moveNorth()
	{
		move(getX(), getY()-1);
	}

	public void moveSouth()
	{
		move(getX(), getY()+1);
	}
	
	public void move(int x, int y)
	{
		if(_maze.checkCell(x, y)==RoomState.UNLOCKED)
		{
			_currentLocation.setLocation(x, y);
			if(x==Maze.MAZESIZE-1&&y==Maze.MAZESIZE-1)
			{
				int dialog =  JOptionPane.showConfirmDialog(
					    null,
					    "Congratulations, you win!!\n"
					    + "Would you like to play again?",
					    "Game Over",
					    JOptionPane.YES_NO_OPTION);
				if(dialog == JOptionPane.NO_OPTION)
					System.exit(0);
				else
				{
					_maze.reset();
					_currentLocation = new Point(0, 0);
					MiniMapPanel.getInstance().repaint();
					RoomPanel.getInstance().repaint();
				}
			}
		}
		if(_maze.checkCell(x, y)==RoomState.LOCKED)
		{
			Room room = (Room) _maze.getCell(x, y);
			QuestionPanel.getInstance().setRoom(room);
		}
		
		MiniMapPanel.getInstance().repaint();
		RoomPanel.getInstance().repaint();
	}
}
