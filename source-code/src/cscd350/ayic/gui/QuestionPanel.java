package cscd350.ayic.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cscd350.ayic.utility.ImageUtil;
import cscd350.ayic.triviamaze.Cell.RoomState;
import cscd350.ayic.triviamaze.LocationTracker;
import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Room;
import net.miginfocom.swing.MigLayout;

public class QuestionPanel extends JPanel implements ActionListener
{
	public static QuestionPanel instance = null;
	private Maze _maze;
	private LocationTracker _tracker;
	private Room _room;
	private JLabel _question;
	private JRadioButton _choiceA;
	private JRadioButton _choiceB;
	private JRadioButton _choiceC;
	private JRadioButton _choiceD;
	private ButtonGroup _bGroup;
	private JButton _submit;
	private JTextField _answer;

	public static void initialize(Maze maze, LocationTracker tracker)
	{
		instance = new QuestionPanel(maze, tracker);
	}

	public static QuestionPanel getInstance()
	{
		return instance;
	}

	private QuestionPanel(Maze maze, LocationTracker tracker)
	{
		_maze = maze;
		_tracker = tracker;
		_question = new JLabel(new ImageIcon(
				ImageUtil.getSilhouette("images/4.png")));

		_choiceA = new JRadioButton("True");
		_choiceB = new JRadioButton("False");
		_choiceC = new JRadioButton("True");
		_choiceD = new JRadioButton("False");
		_bGroup = new ButtonGroup();
		_submit = new JButton("Submit");
		_answer = new JTextField(20);
		
		_submit.addActionListener(this);

		_bGroup.add(_choiceA);
		_bGroup.add(_choiceB);
		_bGroup.add(_choiceC);
		_bGroup.add(_choiceD);

		setLayout(new MigLayout());

		_question.setVisible(false);
		_choiceA.setVisible(false);
		_choiceB.setVisible(false);
		_choiceC.setVisible(false);
		_choiceD.setVisible(false);
		_submit.setVisible(false);
		_answer.setVisible(false);

		add(_question, "cell 0 0 1 2");
		add(_choiceA, "cell 1 0 1 1");
		add(_choiceB, "cell 2 0 1 1");
		add(_choiceC, "cell 1 1 1 1");
		add(_choiceD, "cell 2 1 1 1");
		add(_submit, "cell 3 0 1 1");
		add(_answer, "cell 1 1 1 1");
	}

	public void setRoom(Room room)
	{
		_room = room;
		updateQuestion();
	}

	private void updateQuestion()
	{
		_question.setIcon(new ImageIcon(ImageUtil.getSilhouette("images/"
				+ _room.getQuestion().getId() + ".png")));
		_question.setText(_room.getQuestion().getQuestionText());

		ArrayList<String> choices = _room.getQuestion().getChoices();

		_question.setVisible(true);

		if (choices == null)
		{
			_choiceA.setVisible(false);
			_choiceB.setVisible(false);
			_choiceC.setVisible(false);
			_choiceD.setVisible(false);
			_submit.setVisible(true);
			_answer.setVisible(true);
		}
		else if (choices.size() == 2)
		{
			_choiceA.setText(choices.get(0));
			_choiceB.setText(choices.get(1));
			_choiceA.setVisible(true);
			_choiceB.setVisible(true);
			_choiceC.setVisible(false);
			_choiceD.setVisible(false);
			_submit.setVisible(true);
			_answer.setVisible(false);
		}
		else
		{
			_choiceA.setText(choices.get(0));
			_choiceB.setText(choices.get(1));
			_choiceC.setText(choices.get(2));
			_choiceD.setText(choices.get(3));
			_choiceA.setVisible(true);
			_choiceB.setVisible(true);
			_choiceC.setVisible(true);
			_choiceD.setVisible(true);
			_submit.setVisible(true);
			_answer.setVisible(false);
		}
		
		System.out.println("The correct answer is: "+ _room.getQuestion().getCorrectAnswer());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String answer = "";
		
		if(_room.getQuestion().getQuestionType().equals("short"))
			answer = _answer.getText();
		else
		{
			if(_choiceA.isSelected())
				answer = _choiceA.getText();
			else if(_choiceB.isSelected())
				answer = _choiceB.getText();
			else if(_choiceC.isSelected())
				answer = _choiceC.getText();
			else
				answer = _choiceD.getText();
		}
		
		boolean check = _room.getQuestion().checkAnswer(answer);
		
		if(check)
		{
			_room.setState(RoomState.UNLOCKED);
		}
		else
		{
			_room.setState(RoomState.SEALED);
		}
		
		_tracker.move(_room.getX(), _room.getY());
		_tracker.checkGameOver();
		_question.setVisible(false);
		_choiceA.setVisible(false);
		_choiceB.setVisible(false);
		_choiceC.setVisible(false);
		_choiceD.setVisible(false);
		_submit.setVisible(false);
		_answer.setVisible(false);
	}
}
