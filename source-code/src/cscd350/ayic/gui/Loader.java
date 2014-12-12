package cscd350.ayic.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import cscd350.ayic.triviamaze.Tracker;
import cscd350.ayic.utility.DataBase;

public class Loader extends JFrame
{
	protected Tracker _tracker;
	protected JList<String> _saves;
	protected JButton _load;

	public Loader(Tracker tracker)
	{
		_tracker = tracker;
		_saves = new JList<String>(DataBase.getInstance().retrieveSaves());
		_load = new JButton("Load");

		setLookAndFeel();
		initialize();
	}

	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}

	private void initialize()
	{
		setTitle("Load a game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel(new MigLayout());
		setContentPane(contentPane);

		_saves.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		_saves.setLayoutOrientation(JList.VERTICAL);
		_saves.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(_saves);
		listScroller.setPreferredSize(new Dimension(290, 350));

		_load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (_saves.isSelectionEmpty())
					return;
				Scanner scan = new Scanner(_saves.getSelectedValue());
				int id = scan.nextInt();
				_tracker.load(id);
				dispose();
			}
		});

		contentPane.add(listScroller, "wrap");
		contentPane.add(_load);
	}
}
