package cscd350.ayic.triviamaze;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import cscd350.ayic.gui.GameWindow;
import cscd350.ayic.triviamaze.Cell.RoomState;
public class TriviaMaze {
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				Maze maze = new Maze();
				LocationTracker tracker = new LocationTracker(maze, 0, 0);
				GameWindow game = new GameWindow(maze, tracker);
				game.setVisible(true);
			}
		});
	}
	
	/*public static void main(String[] args) 
	{
		Maze maze = new Maze();
		boolean gameover = false;
		int curx = 0, cury=0;
		
		String inp;

		while(!gameover)	// This game loop is really rushed, sorry. No good is here.
		{
			System.out.println("WHERE MOVE, CREATURE?");
			inp = kb.nextLine();
			gameover = move(maze, curx, cury, inp.charAt(0));
		}
		System.out.println("GAME IS OVER HUMAN.");
		kb.close();
	}
	public static boolean move(Maze maze, int curx, int cury, char dir)	// Figure out direction to process. (Just heads right, at the moment)
	{
		int newx = curx, newy = cury;
		switch(dir) {	//TODO: Anything but this
			case 'n':
				newy = cury-1;
				break;
			case 'e':
				newx = curx+1;
				break;
			case 'w':
				newx = curx-1;
				break;
			case 's':
				newy = cury+1;
				break;
		}
		System.out.format("YOUR POSITION WILL BE %d, %d MORTAL\n", newx, newy);
		return askQuestion((Room) maze.getCell(newx, newy));
	}
	public static boolean askQuestion(Room nextRoom)
	{
		Question question = nextRoom.getQuestion();
		System.out.println(question.getQuestionText());
		String inp = kb.nextLine();
		boolean correct = question.verifyAnswer(inp);
		System.out.println(correct);
		return correct;
	}*/
}
