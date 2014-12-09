package cscd350.ayic.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cscd350.ayic.utility.ImageUtil;

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
	private JRadioButton _choiceC;
	private JRadioButton _choiceD;
	
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
		_question = new JLabel(new ImageIcon(ImageUtil.getSilhouette("images/4.png")));
		_choiceA = new JRadioButton("True");
		_choiceB = new JRadioButton("False");
		_choiceC = new JRadioButton("True");
		_choiceD = new JRadioButton("False");
		
		setLayout(new MigLayout());
		
		add(_question, "cell 0 0 1 2");
		add(_choiceA, "cell 1 0 1 1");
		add(_choiceB, "cell 2 0 1 1");
		add(_choiceC, "cell 1 1 1 1");
		add(_choiceD, "cell 2 1 1 1");
	}
	
	public void setRoom(Room room)
	{
		_room = room;
		updateQuestion();
	}

	private void updateQuestion()
	{
		//_question.setText(_room.getQuestion().getQuestionText());
	}
}
