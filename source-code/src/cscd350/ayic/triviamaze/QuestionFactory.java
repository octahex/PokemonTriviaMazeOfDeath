package cscd350.ayic.triviamaze;
import java.sql.SQLException;

import cscd350.ayic.utility.DataBase;
public class QuestionFactory {
	private DataBase db = new DataBase();
	private int nextQuestion = 1;
	final int QUESTIONS = 5;		// TODO: Change to be based on how many questions are actually in the DB
	
	public Question newQuestion()	// What will be called outside of this class. Returns a new, random question
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
	}
	
}

