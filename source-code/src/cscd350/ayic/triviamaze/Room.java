package cscd350.ayic.triviamaze;

public class Room extends Cell{
	private Question question;
	public Room()
	{
		setState(RoomState.LOCKED);
		//TODO: Select question on creation. Question factory?
	}
	public Room(Question _question)
	{
		this.question = _question;
	}
	public Question getQuestion()
	{
		return this.question;
	}
}
