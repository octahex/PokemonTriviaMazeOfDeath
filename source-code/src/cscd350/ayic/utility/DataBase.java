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
			this.conn.setAutoCommit(false);
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
		createTable("Question");
		createTable("MC");
		createTable("TF");
		createTable("SA");
		createTable("Answer");
		createTable("HS");
		createTable("Save");
		fillQuestion();
		fillAnswer();
	}

	public void createTable(String s)
	{
		String sql = null;
		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		if (s.compareTo("Question") == 0)
		{
			sql = "CREATE TABLE QUESTIONS "
					+ "(Question_ID	INT PRIMARY KEY		NOT NULL, "
					+ "Answer_ID		INT 				NOT NULL, "
					+ "Type 			TEXT				NOT NULL)";
		}
		if (s.compareTo("MC") == 0)
		{
			sql = "CREATE TABLE MULTIPLECHOICE "
					+ "(Question_ID	INT PRIMARY KEY		NOT NULL, "
					+ "Question		TEXT 				NOT NULL)";
		}
		if (s.compareTo("TF") == 0)
		{
			sql = "CREATE TABLE TRUEFALSE "
					+ "(Question_ID	INT PRIMARY KEY		NOT NULL, "
					+ "Question		TEXT 				NOT NULL)";
		}
		if (s.compareTo("SA") == 0)
		{
			sql = "CREATE TABLE SHORTANSWER "
					+ "(Question_ID	INT PRIMARY KEY		NOT NULL, "
					+ "Question		TEXT 				NOT NULL)";
		}
		if (s.compareTo("Answer") == 0)
		{
			sql = "CREATE TABLE ANSWERS "
					+ "(Answer_ID		INT PRIMARY KEY		NOT NULL, "
					+ "Answer 		TEXT				NOT NULL)";
		}
		if (s.compareTo("HS") == 0)
		{
			sql = "CREATE TABLE HIGHSCORES "
					+ "(HighScore_ID	INT PRIMARY KEY		NOT NULL, "
					+ "HighScore		INT 				NOT NULL, "
					+ "NAME 			TEXT				NOT NULL)";
		}
		if (s.compareTo("Save") == 0)
		{
			sql = "CREATE TABLE SAVES "
					+ "(Save_ID		INT PRIMARY KEY		NOT NULL, "
					+ "Address		TEXT 				NOT NULL,)";
		}

		try
		{
			this.stmt.executeUpdate(sql);
			this.stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public void fillQuestion()
	{
		Scanner myfile = new Scanner(System.in);
		int qID;
		int aID;
		String s;
		String type;
		String sql;

		try
		{
			myfile = new Scanner(new File("questions.txt"));
		}
		catch (IOException e)
		{
			System.out.println("Not a File");
		}

		while (myfile.hasNextLine())
		{
			qID = myfile.nextInt();
			aID = myfile.nextInt();
			type = myfile.nextLine();
			s = myfile.nextLine();

			try
			{
				this.stmt = this.conn.createStatement();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

			sql = "INSERT INTO QUESTIONS (Question_ID, Answer_ID, Type) "
					+ "VALUES(" + qID + ", " + aID + ", " + type + " );";
			try
			{
				this.stmt.executeUpdate(sql);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

			if (type.compareTo("MC") == 0)
			{
				sql = "INSERT INTO MULTIPLECHOICE (Question_ID, Question) "
						+ "VALUES(" + qID + ", " + s + " );";
				try
				{
					this.stmt.executeUpdate(sql);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			if (type.compareTo("TF") == 0)
			{
				sql = "INSERT INTO TRUEFALSE (Question_ID, Question) "
						+ "VALUES(" + qID + ", " + s + " );";
				try
				{
					this.stmt.executeUpdate(sql);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			if (type.compareTo("SA") == 0)
			{
				sql = "INSERT INTO SHORTANSWER (Question_ID, Question) "
						+ "VALUES(" + qID + ", " + s + " );";
				try
				{
					this.stmt.executeUpdate(sql);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		try
		{
			this.stmt.close();
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
		} catch (IOException e)
		{
			System.out.println("Not a File");
		}

		while (myfile.hasNextLine())
		{
			aID = myfile.nextInt();
			s = myfile.nextLine();

			try
			{
				this.stmt = this.conn.createStatement();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

			sql = "INSERT INTO QUESTIONS (Answer_ID, Type) " + "VALUES(" + aID
					+ ", " + s + " );";
			try
			{
				this.stmt.executeUpdate(sql);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			this.stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void save(int id, String save)
	{
		String sql;
		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		sql = "INSERT INTO SAVES (SAVE_ID, Address) " + "VALUES(" + id + ", "
				+ save + ");";
		try
		{
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.stmt.close();
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		String sql = "DELETE from ANSWERS where ID=" + id + ";";
		try
		{
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.stmt.close();
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		String sql = "DELETE from SAVES where ID=" + id + ";";
		try
		{
			this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public String retrieveQ(int id)
	{
		String type = null;
		String q = null;

		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Question_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			type = rs.getString("Type");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM " + type + ";");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Question_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			q = rs.getString("Question");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return q;
	}

	public String retrieveType(int id)
	{
		String type = null;

		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Question_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			type = rs.getString("Type");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return type;
	}

	public int retrieveAID(int id)
	{
		int aID = 0;

		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Question_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			aID = rs.getInt("Answer_ID");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return aID;
	}

	public String retrieveA(int id)
	{
		String a = null;
		int aID = 0;

		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Question_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			aID = rs.getInt("Answer_ID");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM ANSWERS;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Answer_ID") != aID)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			a = rs.getString("Answer");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return a;
	}

	public String retrieveSave(int id)
	{
		String save = null;

		try
		{
			this.stmt = this.conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.rs = this.stmt.executeQuery("SELECT * FROM SAVES;");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			while (rs.getInt("Save_ID") != id)
			{
				rs.next();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			save = rs.getString("Address");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return save;
	}
}