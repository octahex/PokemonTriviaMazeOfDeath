package cscd350.ayic.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cscd350.ayic.triviamaze.Tracker;

public class RoomMouseListener implements MouseListener
{
	protected Tracker _tracker;
	public RoomMouseListener(Tracker tracker)
	{
		_tracker = tracker;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		int x = arg0.getX()/64;
		int y = arg0.getY()/64;
		
		if((x==0||x==1)&&y==2)
			_tracker.moveWest();
		if((x==3||x==4)&&y==2)
			_tracker.moveEast();
		if((y==0||y==1)&&x==2)
			_tracker.moveNorth();
		if((y==3||y==4)&&x==2)
			_tracker.moveSouth();
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
