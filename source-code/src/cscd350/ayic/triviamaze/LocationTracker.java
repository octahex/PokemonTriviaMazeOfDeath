package cscd350.ayic.triviamaze;

import java.awt.Point;

import cscd350.ayic.gui.QuestionPanel;
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

	public void moveWest(QuestionPanel questionPanel)
	{
		move(getX()-1, getY(), questionPanel);
	}

	public void moveEast(QuestionPanel questionPanel)
	{
		move(getX()+1, getY(), questionPanel);
	}

	public void moveNorth(QuestionPanel questionPanel)
	{
		move(getX(), getY()-1, questionPanel);
	}

	public void moveSouth(QuestionPanel questionPanel)
	{
		move(getX(), getY()+1, questionPanel);
	}
	
	private void move(int x, int y, QuestionPanel questionPanel)
	{
		if(_maze.checkCell(x, y)==RoomState.SEALED)
			return;
		if(_maze.checkCell(x, y)==RoomState.UNLOCKED)
		{
			_currentLocation.setLocation(x, y);
		}
		Room room = (Room) _maze.getCell(x, y);
		questionPanel.setRoom(room);
		System.out.println(room.getQuestion().getQuestionText());
	}
}
