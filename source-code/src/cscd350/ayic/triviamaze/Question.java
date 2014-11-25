package cscd350.ayic.triviamaze;

public abstract class Question {
	private String questionText;
	private String answer;
	public String getQuestion()
	{
		return questionText;
	}
	public String getAnswer()
	{
		return this.answer;
	}
	public String getQuestionText()
	{
		return this.questionText;
	}
	public void setAnswer(String newAnswer)
	{
		this.answer = newAnswer;
	}
	public void setQuestionText(String newQuestionText)
	{
		this.questionText = newQuestionText;
	}
	public boolean verifyAnswer(String checkAnswer) {
		checkAnswer = checkAnswer.toLowerCase();
		return this.getAnswer().equals(checkAnswer);
	}
	
}
