package cscd350.ayic.triviamaze;

public class Room extends Cell{
	private Question question;
	public Room(int _x, int _y)
	{
		setState(RoomState.LOCKED);
		this.setY(_y);
		//TODO: Select question on creation. Question factory?
	}
	public Room(int _x, int _y, Question _question)
	{
		this(_x, _y);
		this.question = _question;
	}
	public Question getQuestion()
	{
		return this.question;
	}
}
