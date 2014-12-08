package cscd350.ayic.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cscd350.ayic.triviamaze.LocationTracker;

public class RoomMouseListener implements MouseListener
{
	protected LocationTracker _tracker;
	protected QuestionPanel _questionPanel;
	public RoomMouseListener(LocationTracker tracker, QuestionPanel questionPanel)
	{
		_tracker = tracker;
		_questionPanel = questionPanel;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		int x = arg0.getX()/64;
		int y = arg0.getY()/64;
		
		if((x==0||x==1)&&y==2)
			_tracker.moveWest(_questionPanel);
		if((x==3||x==4)&&y==2)
			_tracker.moveEast(_questionPanel);
		if((y==0||y==1)&&x==2)
			_tracker.moveNorth(_questionPanel);
		if((y==3||y==4)&&x==2)
			_tracker.moveSouth(_questionPanel);
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
