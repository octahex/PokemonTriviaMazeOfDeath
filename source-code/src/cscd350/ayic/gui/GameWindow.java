package cscd350.ayic.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import cscd350.ayic.triviamaze.LocationTracker;
import cscd350.ayic.triviamaze.Maze;

public class GameWindow extends JFrame
{
	protected Maze _maze;
	protected LocationTracker _tracker;
	protected RoomPanel _roomPanel;
	protected MiniMapPanel _miniMapPanel;
	protected QuestionPanel _questionPanel;
	protected RoomMouseListener _mouseListener;
	
	public GameWindow(Maze m, LocationTracker tracker)
	{
		_maze = m;
		_tracker = tracker;
		_mouseListener = new RoomMouseListener(_tracker);
		QuestionPanel.initialize(_maze);
		RoomPanel.initialize(_maze, _tracker, new RoomMouseListener(_tracker));
		MiniMapPanel.initialize(_maze, _tracker);
		initialize();
	}

	private void initialize()
	{
		setTitle("Trivia Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(870, 768);
		setLocation(0, 0);
		JPanel contentPane = new JPanel(new MigLayout("", "50[]50[]50", "50[][]50"));
		setContentPane(contentPane);
		contentPane.add(new JLabel("Current room:"), "cell 0 0, height ::20");
		contentPane.add(RoomPanel.getInstance(), "cell 0 1, width 320:320:320, height 320:320:320, center");
		contentPane.add(MiniMapPanel.getInstance(), "cell 1 0 1 2, width 400:400:400, height 400:400:400, center");
		contentPane.add(QuestionPanel.getInstance(), "cell 0 2 2 1");
	}
}
