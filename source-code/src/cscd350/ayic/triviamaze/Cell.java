package cscd350.ayic.triviamaze;

public class Cell
{
	public enum RoomState
	{
		LOCKED, UNLOCKED, SEALED
	};

	private RoomState state;
	private int x, y; // Position within the maze

	public Cell() // Default constructor needed for child classes.
	{
		setState(RoomState.SEALED);
	}

	public Cell(int _x, int _y)
	{
		this();
		this.setX(_x);
		this.setY(_y);
	}

	public RoomState getState()
	{
		return state;
	}

	public void setState(RoomState newState)
	{
		this.state = newState;
	}

	public void setX(int newX)
	{
		this.x = newX;
	}

	public void setY(int newY)
	{
		this.y = newY;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}