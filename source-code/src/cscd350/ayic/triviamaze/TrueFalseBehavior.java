package cscd350.ayic.triviamaze;

import cscd350.ayic.utility.DataBase;

public class TrueFalseBehavior implements QuestionBehavior
{
	private DataBase _db;
	private int _id;
	private boolean _bluffing;
	
	public TrueFalseBehavior(DataBase db, int id)
	{
		_db = db;
		_id = id;
	}

	@Override
	public String getQuestion()
	{
		return "Is this "+_db.retrieveA(_id)+"?";
	}

	@Override
	public String getCorrectAnswer()
	{
		return !_bluffing+"";
	}

	@Override
	public String[] getFakeAnswers()
	{
		String[] fake = new String[1];
		fake[0] = _bluffing+"";
		return fake;
	}

}
