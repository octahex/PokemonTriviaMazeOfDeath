package cscd350.ayic.triviamaze;

import cscd350.ayic.utility.SimpleDB;

public class ShortAnswerBehavior implements QuestionBehavior
{
	private SimpleDB _db;
	private int _id;
	
	public ShortAnswerBehavior(int id)
	{

		_db = SimpleDB.getInstance();
		_id = id;
	}
	
	@Override
	public String getQuestion()
	{
		return "Who's that Pokemon?";
	}

	@Override
	public String getCorrectAnswer()
	{
		return _db.retrieveA(_id);
	}

	@Override
	public String[] getFakeAnswers()
	{
		return null;
	}

}
