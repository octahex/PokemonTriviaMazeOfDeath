package cscd350.ayic.triviamaze;

public class MultipleChoice extends Question {
	private String[] answerList;
	public MultipleChoice(String _questionText, String _answer, String[] answerList)
	{
		setQuestionText(_questionText);
		setAnswer(_answer);
		this.answerList = answerList;
	}
}
