package cscd350.ayic.triviamaze;

import java.util.Random;

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
		int i;
		Random r = new Random();
		int falseId = r.nextInt(151);
		_bluffing = r.nextBoolean();
		if(_bluffing == false)
		{
			return "Is this "+_db.retrieveA(_id)+"?";
		}
		else
		{
			return "Is this "+_db.retrieveA(falseId)+"?";
		}
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
