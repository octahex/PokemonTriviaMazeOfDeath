package cscd350.ayic.triviamaze;
import java.util.Random;

import cscd350.ayic.utility.SimpleDB;
public class QuestionFactory {
	private SimpleDB _db;
	private Random _gen;
	//private int nextQuestion = 1;
	//final int QUESTIONS = 5;		// TODO: Change to be based on how many questions are actually in the DB
	
	public QuestionFactory()
	{
		_db = SimpleDB.getInstance();
		_gen = new Random();
	}
	
	public Question getRandomQuestion()
	{
		switch(_gen.nextInt(3))
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
	
	public Question createQuestion( String type )
	{
		QuestionBehavior behavior;
		Question question;
		int id = _gen.nextInt(151)+1;
		
		switch( type.toLowerCase())
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
	
	/*public Question newQuestion()	// What will be called outside of this class. Returns a new, random question
	{
		if(nextQuestion > QUESTIONS)
			nextQuestion = 1;
		Question newQuestion = null;
		String newText, newAnswer, newType;
		try {
			newType = db.retrieveType(nextQuestion);
			newText = db.retrieveQ(nextQuestion);
			newAnswer = db.retrieveA(db.retrieveAID(nextQuestion));
			if(newType.equals("MC"))	// Multiple Choice
				newQuestion = new MultipleChoice(newText, newAnswer);
			if(newType.equals("SA"))	// Short Answer
				newQuestion = new ShortAnswer(newText, newAnswer);
			if(newType.equals("TF"))	// True/False
				newQuestion = new TrueFalse(newText, newAnswer);
		}
		catch(SQLException e)
		{
			newQuestion = new TrueFalse("Was I able to connect to the database properly?", "false");
		}
		nextQuestion++;
		return (Question)newQuestion;
	} */
	
}

