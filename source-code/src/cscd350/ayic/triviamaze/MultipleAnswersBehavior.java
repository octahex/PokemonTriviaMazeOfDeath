package cscd350.ayic.triviamaze;

import java.util.Random;

import cscd350.ayic.utility.DataBase;

public class MultipleAnswersBehavior implements QuestionBehavior
{
	private DataBase _db;
	private int _id;

	public MultipleAnswersBehavior(int id)
	{
		_db = DataBase.getInstance();
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
		Random gen = new Random();
		String[] fake = new String[3];
		int temp;
		for (int i = 0; i < 3; i++)
		{
			temp = DataBase.getInstance().randomAID(false);
			fake[i] = _db.retrieveA(temp);
		}

		return fake;
	}
}
