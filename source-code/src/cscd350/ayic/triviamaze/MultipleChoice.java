package cscd350.ayic.triviamaze;

public class MultipleChoice extends Question 
{
	public MultipleChoice(String _questionText, String _answer)
	{
		setQuestionText(_questionText);
		setAnswer(_answer);
	}
	public String[] getAnswerList() //TODO: Do this.
	{
		return new String[4];
	}
}
