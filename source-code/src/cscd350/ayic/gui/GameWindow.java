package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Tracker;

public class GameWindow extends JFrame
{
	protected Maze _maze;
	protected Tracker _tracker;
	protected RoomPanel _roomPanel;
	protected MiniMapPanel _miniMapPanel;
	protected QuestionPanel _questionPanel;
	protected RoomMouseListener _mouseListener;
	protected MenuActionListener _actionListener;
	protected JMenuBar _menuBar;

	public GameWindow(Maze m, Tracker tracker)
	{
		_maze = m;
		_tracker = tracker;
		_mouseListener = new RoomMouseListener(_tracker);
		_actionListener = new MenuActionListener(_tracker);
		_menuBar = new JMenuBar();

		setLookAndFeel();
		createMenuBar();

		QuestionPanel.initialize(_maze, _tracker);
		RoomPanel.initialize(_maze, _tracker, new RoomMouseListener(_tracker));
		MiniMapPanel.initialize(_maze, _tracker);
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

	private void createMenuBar()
	{
		setJMenuBar(_menuBar);
		
		// file 
		JMenu file = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem loadItem = new JMenuItem("Load");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		file.add(newItem);
		file.add(loadItem);
		file.add(saveItem);
		file.add(new JSeparator());
		file.add(exitItem);
		
		//help
		JMenu help = new JMenu("?");
		JMenuItem aboutItem = new JMenuItem("About");
		JMenuItem helpItem = new JMenuItem("Help");
		help.add(aboutItem);
		help.add(helpItem);
		
		_menuBar.add(file);
		_menuBar.add(help);

		newItem.addActionListener(_actionListener);
		loadItem.addActionListener(_actionListener);
		saveItem.addActionListener(_actionListener);
		exitItem.addActionListener(_actionListener);
		aboutItem.addActionListener(_actionListener);
		helpItem.addActionListener(_actionListener);
	}

	private void initialize()
	{
		setTitle("Trivia Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(870, 768);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel(new MigLayout("", "50[]50[]50",
				"50[][]50"));
		setContentPane(contentPane);
		contentPane.add(new JLabel("Current room:"), "cell 0 0, height ::20");
		contentPane.add(RoomPanel.getInstance(),
				"cell 0 1, width 320:320:320, height 320:320:320, center");
		contentPane.add(MiniMapPanel.getInstance(),
				"cell 1 0 1 2, width 400:400:400, height 400:400:400, center");
		contentPane.add(QuestionPanel.getInstance(), "cell 0 2 2 1");
	}
}
