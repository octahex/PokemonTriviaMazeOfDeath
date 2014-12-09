package cscd350.ayic.triviamaze;

import java.util.Random;

public abstract class Question
{
	private Random _gen;
	private QuestionBehavior _questionBehavior;
	
	public Question(QuestionBehavior qb)
	{
		setBehavior(qb);
	}
	
	public void setBehavior(QuestionBehavior qb)
	{
		_questionBehavior = qb;
		init();
	}
	
	private void init()
	{
		
	}

	public boolean checkAnswer(String answer)
	{
		return false;
	}
}
