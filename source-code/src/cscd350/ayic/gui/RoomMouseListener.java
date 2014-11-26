package cscd350.ayic.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomMouseListener implements MouseListener
{
	private RoomPanel _roomPanel;
	
	public RoomMouseListener(RoomPanel roomPanel)
	{
		_roomPanel = roomPanel;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		int x = arg0.getX()/64;
		int y = arg0.getY()/64;
		
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
