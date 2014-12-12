package cscd350.ayic.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Tracker;

public class MenuActionListener implements ActionListener
{
	private Tracker _tracker;

	public MenuActionListener(Tracker tracker)
	{
		_tracker = tracker;
	}

	public void actionPerformed(ActionEvent item)
	{
		switch (item.getActionCommand().toLowerCase())
		{
		case "new":
			if (JOptionPane.showConfirmDialog(null, "Start a new game?",
					"New game", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				_tracker.reset();
			break;
		case "load":
			new Loader(_tracker).setVisible(true);
			break;
		case "save":
			_tracker.save();
			break;
		case "exit":
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
					"Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
		case "about":
			String about = "Pokemon Trivia Maze of DEATH\n\nv0.1\n\nBy team AYIC (And Yet It Compiles):\n";
			about += "- David Stewart\n"
					+ "- Samir Ouahhabi\n"
					+ "- Paul Jones\n";
			JOptionPane.showMessageDialog(null, about, "About", JOptionPane.OK_OPTION);
			break;
		case "help":
			String help = "You are a forensic investigator that has been hired to solve a mysterious series of crimes.\n\n"
					+ "You find yourself trapped in a maze, in order to solve the case you need to identify the dead Pokemon bodies before they rot.\n\n"
					+ "You start in the northwest room and you work your way out going from one room to another until the end.\n\n"
					+ "In order to successfully unlock a room you need to answer the presented question.\n"
					+ "If you get the wrong answer, the room will be sealed forever.\n\n"
					+ "The game ends when you reach the end room or when you get trapped and have no way to reach the end.\n\n"
					+ "You are presented with a minimap that shows the whole 8x8 map in 3 different colors:\n"
					+ "- Green: This room is unlocked, you can access it.\n"
					+ "- Gray: This room is locked, you have to answer a question correctly to unlock it\n"
					+ "- Red: This room is sealed forever, you can never access it again.\n\n"
					+ "To navigate the map click on the arrows showing in the current room view.\n\n"
					+ "Good luck in your quest!";
			JOptionPane.showMessageDialog(null, help, "Help", JOptionPane.OK_OPTION);
			break;
		}
	}

}
