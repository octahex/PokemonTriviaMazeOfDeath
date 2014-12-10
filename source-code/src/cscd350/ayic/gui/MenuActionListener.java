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
			if (JOptionPane.showConfirmDialog(null, "Start a new game?", "New game",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				_tracker.reset();
			break;
		case "load":
			new Loader(_tracker).setVisible(true);
			break;
		case "save":
			_tracker.save();
			break;
		case "exit":
			System.exit(0);
			break;
		case "about":
			
			break;
		case "help":
			
			break;
		}
	}

}
