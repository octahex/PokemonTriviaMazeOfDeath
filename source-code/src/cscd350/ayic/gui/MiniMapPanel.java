package cscd350.ayic.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cscd350.ayic.triviamaze.Cell.RoomState;
import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Tracker;

public class MiniMapPanel extends JPanel
{
	public static MiniMapPanel instance = null;
	private Color _borderColor;
	private Color _lockedRoomColor;
	private Color _unlockedRoomColor;
	private Color _sealedRoomColor;
	private BufferedImage _hereImage;
	private BufferedImage _finishImage;
	private TexturePaint _hereTexture;
	private TexturePaint _finishTexture;
	private Maze _maze;
	private Tracker _tracker;
	private int _size;
	
	public static void initialize(Maze maze, Tracker tracker)
	{
		instance = new MiniMapPanel(maze, tracker);
	}
	
	public static MiniMapPanel getInstance()
	{
		return instance;
	}
	
	private MiniMapPanel(Maze maze, Tracker tracker)
	{
		_maze = maze;
		_tracker = tracker;
		_size = Maze.MAZESIZE;
		_borderColor = Color.white;
		_lockedRoomColor = Color.gray;
		_unlockedRoomColor = Color.green;
		_sealedRoomColor = Color.red;
		
		try
		{
			_hereImage = ImageIO.read(new File("textures/here.png"));
			_finishImage = ImageIO.read(new File("textures/finish.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		_hereTexture = new TexturePaint(_hereImage, new Rectangle(0, 0, 50, 50));
		_finishTexture = new TexturePaint(_finishImage, new Rectangle(0, 0, 50, 50));
	}
	
	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Rectangle2D rect;
		
		for(int r=0; r<_size; r++)
			for(int c=0; c<_size; c++)
			{
				rect = new Rectangle2D.Double(c*50, r*50, 50, 50);
				if(_maze.checkCell(c, r)==RoomState.UNLOCKED)
					g2d.setColor(_unlockedRoomColor);
				else if(_maze.checkCell(c, r)==RoomState.LOCKED)
					g2d.setColor(_lockedRoomColor);
				else
					g2d.setColor(_sealedRoomColor);
				g2d.fill(rect);
				g2d.setColor(_borderColor);
				g2d.draw(rect);
			}
		
		g2d.setPaint(_hereTexture);
		g2d.fillRect(50*_tracker.getX(), 50*_tracker.getY(), 50, 50);
		g2d.setPaint(_finishTexture);
		g2d.fillRect(50*(Maze.MAZESIZE-1), 50*(Maze.MAZESIZE-1), 50, 50);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}
