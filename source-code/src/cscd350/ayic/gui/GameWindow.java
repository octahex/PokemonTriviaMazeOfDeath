package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import cscd350.ayic.triviamaze.Maze;

public class GameWindow extends JFrame
{
	public GameWindow()
	{
		initialize();
	}

	private void initialize()
	{
		setTitle("Trivia Maze");
		
		add(new MiniMapPanel(new Maze(), 8));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(870, 768);
		setLocation(0, 0);
		JPanel contentPane = new JPanel(new MigLayout("", "50[]50[]50", "50[][]50"));
		setContentPane(contentPane);
		contentPane.add(new JLabel("Current room:"), "cell 0 0, height ::20");
		contentPane.add(new RoomPanel(), "cell 0 1, width 320:320:320, height 320:320:320, center");
		contentPane.add(new MiniMapPanel(new Maze(), 8), "cell 1 0 1 2, width 400:400:400, height 400:400:400, center");
		contentPane.add(new QuestionPanel(), "cell 0 2 2 1");
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				GameWindow rd = new GameWindow();
				rd.setVisible(true);
			}
		});
	}
}
