package cscd350.ayic.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Room;
import net.miginfocom.swing.MigLayout;

public class QuestionPanel extends JPanel
{
	public static QuestionPanel instance = null;
	private Maze _maze;
	private Room _room;
	private JLabel _question;
	private JRadioButton _choiceA;
	private JRadioButton _choiceB;
	
	public static void initialize(Maze maze)
	{
		instance = new QuestionPanel(maze);
	}
	
	public static QuestionPanel getInstance()
	{
		return instance;
	}
	
	private QuestionPanel(Maze maze)
	{
		_maze = maze;
		_question = new JLabel("This is fun.");
		_choiceA = new JRadioButton("True");
		_choiceB = new JRadioButton("False");
		
		setLayout(new MigLayout());
		
		add(_question, "wrap");
		add(_choiceA);
		add(_choiceB);
	}
	
	public void setRoom(Room room)
	{
		_room = room;
		updateQuestion();
	}

	private void updateQuestion()
	{
		_question.setText(_room.getQuestion().getQuestionText());
	}
}
