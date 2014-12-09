package cscd350.ayic.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleDB
{
	private static SimpleDB instance = new SimpleDB();
	private ArrayList<String> _answers;

	public static SimpleDB getInstance()
	{
		return instance;
	}

	private SimpleDB()
	{
		init();
	}

	private void init()
	{
		_answers = new ArrayList<>();
		try
		{
			Scanner file = new Scanner(new FileReader("answers.txt"));
			while(file.hasNext())
			{
				file.nextLine();
				_answers.add(file.nextLine());
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public String retrieveA(int num)
	{
		return _answers.get(num-1);
	}
}
