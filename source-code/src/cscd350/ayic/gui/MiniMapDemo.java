package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cscd350.ayic.triviamaze.Maze;

public class MiniMapDemo extends JFrame
{
	public MiniMapDemo()
	{
		initialize();
	}

	private void initialize()
	{
		setTitle("Mini Map Demo");
		
		add(new MiniMapPanel(new Maze(), 8));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(403, 437);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				MiniMapDemo rd = new MiniMapDemo();
				rd.setVisible(true);
			}
		});
	}
}
