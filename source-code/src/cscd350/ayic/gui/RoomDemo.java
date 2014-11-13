package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RoomDemo extends JFrame
{

	public RoomDemo()
	{
		initialize();
	}

	private void initialize()
	{
		setTitle("Current room");

		add(new RoomPanel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(320, 355);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				RoomDemo rd = new RoomDemo();
				rd.setVisible(true);
			}
		});
	}
}
