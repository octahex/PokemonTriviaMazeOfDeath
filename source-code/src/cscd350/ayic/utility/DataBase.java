package cscd350.ayic.utility;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DataBase
{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public DataBase()
	{
		connect();
	}

	public void connect()
	{
		File file = new File("triviaMaze.db");
		boolean check = false;

		if (file.exists())
		{
			check = true;
		}
		try
		{
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager
					.getConnection("jdbc:sqlite:triviaMaze.db");
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		if (check == false)
		{
			creatDB();
		}
	}

	public void creatDB()
	{
		createTable("Answer");
		createTable("Save");
		fillAnswer();
	}

	public void createTable(String s)
	{
		String sql = null;

		try
		{
			this.stmt = this.conn.createStatement();
			
			if (s.compareTo("Answer") == 0)
			{
				sql = "CREATE TABLE ANSWERS "
						+ "(Answer_ID	INT PRIMARY KEY		NOT NULL, "
						+ "Answer 		VARCHAR				NOT NULL,"
						+ "Image 		VARCHAR);";
				this.stmt.executeUpdate(sql);
			}
			if (s.compareTo("Save") == 0)
			{
				sql = "CREATE TABLE SAVES "
						+ "(Save_ID		INT PRIMARY KEY		NOT NULL, "
						+ "Address		VARCHAR				NOT NULL);";
				this.stmt.executeUpdate(sql);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public void fillAnswer()
	{
		Scanner myfile = new Scanner(System.in);
		int aID;
		String s;
		String sql;

		try
		{
			myfile = new Scanner(new File("answers.txt"));
		}
		catch (IOException e)
		{
			System.out.println("Not a File");
		}

		while (myfile.hasNextLine())
		{
			aID = myfile.nextInt();
			s = myfile.nextLine();
			while(s.equalsIgnoreCase("\n")||s.equalsIgnoreCase("")||s.equalsIgnoreCase(" "))
				s = myfile.nextLine();

			try
			{
				this.stmt = this.conn.createStatement();
				sql = "INSERT INTO ANSWERS (Answer_ID, Answer) " + "VALUES(" + aID + ", '" + s.replace("'", "") + "' );";
				this.stmt.executeUpdate(sql);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void save(int id, String save)
	{
		String sql;
		try
		{
			this.stmt = this.conn.createStatement();
			sql = "INSERT INTO SAVES (Save_ID, Save) " + "VALUES(" + id + ", '" + save.replace("'", "") + "' );";
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteA(int id)
	{
		try
		{
			this.stmt = this.conn.createStatement();
			String sql = "DELETE from ANSWERS where ID=" + id + ";";
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteS(int id)
	{
		try
		{
			this.stmt = this.conn.createStatement();
			String sql = "DELETE from SAVES where ID=" + id + ";";
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public String retrieveA(int id)
	{
		String a = null;

		try
		{
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("SELECT * FROM ANSWERS WHERE Answer_ID=" + id + ";");
			a = rs.getString("Answer");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return a;
	}
	
	public int retrieveAID(String a)
	{
		int id = 0;

		try
		{
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("SELECT * FROM ANSWERS WHERE Answer=" + a + ";");
			a = rs.getString("Answer_ID");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return id;
	}

	public String retrieveSave(int id)
	{
		String save = null;

		try
		{
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("SELECT * FROM SAVES WHERE Save_ID="+id+";");
			save = rs.getString("Address");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return save;
	}
}