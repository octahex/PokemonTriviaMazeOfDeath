package cscd350.ayic.triviamaze;

import cscd350.ayic.utility.DataBase;
import cscd350.ayic.utility.SimpleDB;

public class TrueFalseBehavior implements QuestionBehavior
{
	private SimpleDB _db;
	private int _id;
	private boolean _bluffing;
	
	public TrueFalseBehavior(int id)
	{
		_db = SimpleDB.getInstance();
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
