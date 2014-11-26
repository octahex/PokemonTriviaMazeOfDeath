package cscd350.ayic.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;
import sun.awt.geom.AreaOp.AddOp;

public class QuestionPanel extends JPanel
{
	private JLabel _question;
	private JRadioButton _choiceA;
	private JRadioButton _choiceB;
	
	public QuestionPanel()
	{
		_question = new JLabel("This is fun.");
		_choiceA = new JRadioButton("True");
		_choiceB = new JRadioButton("False");
		
		setLayout(new MigLayout());
		
		add(_question, "wrap");
		add(_choiceA);
		add(_choiceB);
	}
}
