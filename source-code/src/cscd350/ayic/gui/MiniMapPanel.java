package cscd350.ayic.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import cscd350.ayic.triviamaze.Cell.RoomState;
import cscd350.ayic.triviamaze.Maze;

public class MiniMapPanel extends JPanel
{
	private Color _borderColor;
	private Color _lockedRoomColor;
	private Color _unlockedRoomColor;
	private Maze _maze;	
	private int _size;
	
	public MiniMapPanel(Maze maze, int size)
	{
		_maze = maze;
		_size = size;
		_borderColor = Color.white;
		_lockedRoomColor = Color.gray;
		_unlockedRoomColor = Color.green;
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
				else
					g2d.setColor(_lockedRoomColor);
				g2d.fill(rect);
				g2d.setColor(_borderColor);
				g2d.draw(rect);
			}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}
