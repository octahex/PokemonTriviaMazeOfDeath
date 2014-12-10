package cscd350.ayic.triviamaze;

import java.io.FileInputStream;

import javax.swing.SwingUtilities;

import javazoom.jl.player.Player;
import cscd350.ayic.gui.GameWindow;

public class TriviaMaze
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				Maze maze = new Maze();
				LocationTracker tracker = new LocationTracker(maze, 0, 0);
				GameWindow game = new GameWindow(maze, tracker);
				game.setVisible(true);
			}
		});

		try
		{

			FileInputStream fis = new FileInputStream("sounds/lavender.mp3");
			Player playMP3 = new Player(fis);
			playMP3.play();

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
