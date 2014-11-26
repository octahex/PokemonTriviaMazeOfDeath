package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cscd350.ayic.triviamaze.Maze;

public class QuestionDemo extends JFrame
{
	public QuestionDemo()
	{
		initialize();
	}

	private void initialize()
	{
		setTitle("Question Demo");
		
		add(new QuestionPanel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				QuestionDemo rd = new QuestionDemo();
				rd.setVisible(true);
			}
		});
	}
}
