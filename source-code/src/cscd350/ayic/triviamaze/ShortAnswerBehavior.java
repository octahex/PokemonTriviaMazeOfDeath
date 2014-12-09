package cscd350.ayic.triviamaze;

import cscd350.ayic.utility.DataBase;

public class ShortAnswerBehavior implements QuestionBehavior
{
	private DataBase _db;
	private int _id;
	
	public ShortAnswerBehavior(DataBase db, int id)
	{
		_db = db;
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
