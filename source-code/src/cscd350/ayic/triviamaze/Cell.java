package cscd350.ayic.triviamaze;

public class Cell {
	public enum RoomState {LOCKED, UNLOCKED, SEALED};
	private RoomState state;
	public Cell()
	{
		setState(RoomState.SEALED);
	}
	public RoomState getState()
	{
		return state;
	}
	public void setState(RoomState newState)
	{
		this.state = newState;
	}
}
