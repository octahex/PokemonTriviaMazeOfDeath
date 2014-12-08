package cscd350.ayic.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cscd350.ayic.triviamaze.LocationTracker;
import cscd350.ayic.triviamaze.Maze;
import cscd350.ayic.triviamaze.Cell.RoomState;

public class RoomPanel extends JPanel
{
	private BufferedImage _brickImage;
	private BufferedImage _lockImage;
	private BufferedImage _arrowUpImage;
	private BufferedImage _arrowDownImage;
	private BufferedImage _arrowLeftImage;
	private BufferedImage _arrowRightImage;
	private BufferedImage _exitUpImage;
	private BufferedImage _exitDownImage;
	private BufferedImage _exitLeftImage;
	private BufferedImage _exitRightImage;
	private BufferedImage _closedImage;
	private BufferedImage _grassImage;
	
	private TexturePaint _brickTexture;
	private TexturePaint _lockTexture;
	private TexturePaint _arrowUpTexture;
	private TexturePaint _arrowDownTexture;
	private TexturePaint _arrowLeftTexture;
	private TexturePaint _arrowRightTexture;
	private TexturePaint _exitUpTexture;
	private TexturePaint _exitDownTexture;
	private TexturePaint _exitLeftTexture;
	private TexturePaint _exitRightTexture;
	private TexturePaint _closedTexture;
	private TexturePaint _grassTexture;
	
	private RoomMouseListener _mouseListener;
	private Maze _maze;
	private LocationTracker _tracker;
	
	public RoomPanel(Maze maze, LocationTracker tracker, RoomMouseListener mouseListener)
	{
		_maze = maze;
		_tracker = tracker;
		loadImages();
		loadTextures();
		_mouseListener = mouseListener;
		addMouseListener(_mouseListener);
	}

	private void loadImages()
	{
		try
		{
			_brickImage = ImageIO.read(new File("textures/brick.png"));
			_lockImage = ImageIO.read(new File("textures/lock.png"));
			_arrowUpImage = ImageIO.read(new File("textures/arrow-up.png"));
			_arrowDownImage = ImageIO.read(new File("textures/arrow-down.png"));
			_arrowLeftImage = ImageIO.read(new File("textures/arrow-left.png"));
			_arrowRightImage = ImageIO.read(new File("textures/arrow-right.png"));
			_exitUpImage = ImageIO.read(new File("textures/exit-north.png"));
			_exitDownImage = ImageIO.read(new File("textures/exit-south.png"));
			_exitLeftImage = ImageIO.read(new File("textures/exit-west.png"));
			_exitRightImage = ImageIO.read(new File("textures/exit-east.png"));
			_closedImage = ImageIO.read(new File("textures/closed.png"));
			_grassImage = ImageIO.read(new File("textures/grass.png"));
		}
		catch(IOException ex)
		{
			Logger.getLogger(RoomPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void loadTextures()
	{
		_brickTexture = new TexturePaint(_brickImage, new Rectangle(0, 0, 64, 64));
		_lockTexture = new TexturePaint(_lockImage, new Rectangle(0, 0, 64, 64));
		_arrowUpTexture = new TexturePaint(_arrowUpImage, new Rectangle(0, 0, 64, 64));
		_arrowDownTexture = new TexturePaint(_arrowDownImage, new Rectangle(0, 0, 64, 64));
		_arrowLeftTexture = new TexturePaint(_arrowLeftImage, new Rectangle(0, 0, 64, 64));
		_arrowRightTexture = new TexturePaint(_arrowRightImage, new Rectangle(0, 0, 64, 64));
		_exitUpTexture = new TexturePaint(_exitUpImage, new Rectangle(0, 0, 64, 64));
		_exitDownTexture = new TexturePaint(_exitDownImage, new Rectangle(0, 0, 64, 64));
		_exitLeftTexture = new TexturePaint(_exitLeftImage, new Rectangle(0, 0, 64, 64));
		_exitRightTexture = new TexturePaint(_exitRightImage, new Rectangle(0, 0, 64, 64));
		_closedTexture = new TexturePaint(_closedImage, new Rectangle(0, 0, 64, 64));
		_grassTexture = new TexturePaint(_grassImage, new Rectangle(0, 0, 64, 64));
	}
	
	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		paintBricks(g2d);
		
		paintGrass(g2d);

		paintArrows(g2d);
		
		paintExits(g2d);
		
		paintDoors(g2d);
	}
	
	private void paintDoors(Graphics2D g2d)
	{
		int x = _tracker.getX();
		int y = _tracker.getY();

		paintDoor(g2d, x-1, y, 0, 128);
		paintDoor(g2d, x+1, y, 256, 128);
		paintDoor(g2d, x, y-1, 128, 0);
		paintDoor(g2d, x, y+1, 128, 256);
	}
	
	private void paintDoor(Graphics2D g2d, int x, int y, int paintX, int paintY)
	{		
		if(_maze.checkCell(x, y)==RoomState.LOCKED)
		{
			g2d.setPaint(_lockTexture);
			g2d.fillRect(paintX, paintY, 64, 64);
			return;
		}
		if(_maze.checkCell(x, y)==RoomState.SEALED)
		{
			g2d.setPaint(_closedTexture);
			g2d.fillRect(paintX, paintY, 64, 64);
			return;
		}
		
	}

	private void paintExits(Graphics2D g2d)
	{
		g2d.setPaint(_exitUpTexture);
		g2d.fillRect(128, 0, 64, 64);
		
		g2d.setPaint(_exitDownTexture);
		g2d.fillRect(128, 256, 64, 64);
		
		g2d.setPaint(_exitRightTexture);
		g2d.fillRect(256, 128, 64, 64);
		
		g2d.setPaint(_exitLeftTexture);
		g2d.fillRect(0, 128, 64, 64);
	}

	private void paintArrows(Graphics2D g2d)
	{
		g2d.setPaint(_arrowUpTexture);
		g2d.fillRect(128, 64, 64, 64);
		
		g2d.setPaint(_arrowDownTexture);
		g2d.fillRect(128, 192, 64, 64);
		
		g2d.setPaint(_arrowRightTexture);
		g2d.fillRect(192, 128, 64, 64);
		
		g2d.setPaint(_arrowLeftTexture);
		g2d.fillRect(64, 128, 64, 64);
	}

	private void paintGrass(Graphics2D g2d)
	{
		g2d.setPaint(_grassTexture);
		g2d.fillRect(64, 64, 64, 64);
		g2d.fillRect(128, 64, 64, 64);
		g2d.fillRect(192, 64, 64, 64);
		g2d.fillRect(64, 128, 64, 64);
		g2d.fillRect(128, 128, 64, 64);
		g2d.fillRect(192, 128, 64, 64);
		g2d.fillRect(64, 192, 64, 64);
		g2d.fillRect(128, 192, 64, 64);
		g2d.fillRect(192, 192, 64, 64);
	}

	private void paintBricks(Graphics2D g2d)
	{
		g2d.setPaint(_brickTexture);
		g2d.fillRect(0, 0, 64, 64);
		g2d.fillRect(64, 0, 64, 64);
		g2d.fillRect(192, 0, 64, 64);
		g2d.fillRect(256, 0, 64, 64);
		g2d.fillRect(0, 64, 64, 64);
		g2d.fillRect(256, 64, 64, 64);
		g2d.fillRect(0, 192, 64, 64);
		g2d.fillRect(256, 192, 64, 64);
		g2d.fillRect(0, 256, 64, 64);
		g2d.fillRect(64, 256, 64, 64);
		g2d.fillRect(192, 256, 64, 64);
		g2d.fillRect(256, 256, 64, 64);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}
