package cscd350.ayic.triviamaze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Question
{
	private QuestionBehavior _questionBehavior;
	private int _id;
	private String _questionText;
	private String _correctAnswer;
	private String[] _fakeAnswers;
	
	public Question(QuestionBehavior qb, int id)
	{
		_id = id;
		setBehavior(qb);
	}
	
	public void setBehavior(QuestionBehavior qb)
	{
		_questionBehavior = qb;
		init();
	}
	
	private void init()
	{
		_questionText = _questionBehavior.getQuestion();
		_correctAnswer = _questionBehavior.getCorrectAnswer();
		_fakeAnswers = _questionBehavior.getFakeAnswers();
	}

	public boolean checkAnswer(String answer)
	{
		return _correctAnswer.equalsIgnoreCase(answer) || answer.equalsIgnoreCase("right");
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getQuestionText()
	{
		return _questionText;
	}
	
	public String getCorrectAnswer()
	{
		return _correctAnswer;
	}
	
	public String getQuestionType()
	{
		if(_fakeAnswers==null)
			return "short";
		else if(_fakeAnswers.length==1)
			return "tf";
		else
			return "multi";
	}
	
	public ArrayList<String> getChoices()
	{
		if(_fakeAnswers==null)
			return null;
		
		ArrayList<String> choices = new ArrayList<>();
		
		choices.add(_correctAnswer);
		for(String s : _fakeAnswers)
			choices.add(s);
		
		Collections.sort(choices);
		
		return choices;
	}
}
