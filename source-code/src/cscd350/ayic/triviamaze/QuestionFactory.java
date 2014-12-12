package cscd350.ayic.triviamaze;

import java.util.Random;

import cscd350.ayic.utility.DataBase;

public class QuestionFactory
{
	private Random _gen;

	// private int nextQuestion = 1;
	// final int QUESTIONS = 5; // TODO: Change to be based on how many
	// questions are actually in the DB

	public QuestionFactory()
	{
		_gen = new Random();
	}

	public Question getRandomQuestion()
	{
		switch (_gen.nextInt(3))
		{
		case 0:
			return createQuestion("tf");
		case 1:
			return createQuestion("multi");
		case 2:
			return createQuestion("short");
		default:
			return createQuestion("tf");
		}
	}

	public Question createQuestion(String type)
	{
		QuestionBehavior behavior;
		Question question;
		int id = DataBase.getInstance().randomAID(true);

		switch (type.toLowerCase())
		{
		case "tf":
			behavior = new TrueFalseBehavior(id);
			break;
		case "multi":
			behavior = new MultipleAnswersBehavior(id);
			break;
		case "short":
			behavior = new ShortAnswerBehavior(id);
			break;
		default:
			behavior = new TrueFalseBehavior(id);
		}

		question = new Question(behavior, id);

		return question;
	}
}
