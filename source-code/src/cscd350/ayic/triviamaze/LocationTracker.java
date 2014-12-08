package cscd350.ayic.triviamaze;

import java.awt.Point;

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
	
	public void moveTo(int x, int y)
	{
		if(x < 0 || x >= 8 || y < 0 || y >= 8)
			return;
		_currentLocation.setLocation(x, y);
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
	
	private void move(int x, int y)
	{
		if(_maze.checkCell(x, y)==RoomState.SEALED)
			return;
		if(_maze.checkCell(x, y)==RoomState.UNLOCKED)
		{
			_currentLocation.setLocation(x, y);
			MiniMapPanel.getInstance().repaint();
			RoomPanel.getInstance().repaint();
		}
		Room room = (Room) _maze.getCell(x, y);
		QuestionPanel.getInstance().setRoom(room);
	}
}
