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
		try {
			this.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws SQLException
	{
		File file = new File("triviaMaze.db");
		boolean check = false;
		
		if(file.exists())
		{
			check = true;
		}
		try
		{
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:triviaMaze.db");
			this.conn.setAutoCommit(false);
		}
		catch(Exception e)
		{
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		
		if(check == false)
		{
			creatDB();
		}
	}
	
	public void creatDB() throws SQLException
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
		this.conn.commit();
	}
	
	public void createTable(String s) throws SQLException
	{
		String sql = null;
		this.stmt = this.conn.createStatement();
		if(s.compareTo("Question")==0)
		{
			sql = "CREATE TABLE QUESTIONS " +
					"(Question_ID	INT PRIMARY KEY		NOT NULL, " +
					"Answer_ID		INT 				NOT NULL, " +
					"Type 			TEXT				NOT NULL)";
		}
		if(s.compareTo("MC")==0)
		{
			sql = "CREATE TABLE MULTIPLECHOICE " +
					"(Question_ID	INT PRIMARY KEY		NOT NULL, " +
					"Question		TEXT 				NOT NULL)";
		}
		if(s.compareTo("TF")==0)
		{
			sql = "CREATE TABLE TRUEFALSE " +
					"(Question_ID	INT PRIMARY KEY		NOT NULL, " +
					"Question		TEXT 				NOT NULL)";
		}
		if(s.compareTo("SA")==0)
		{
			sql = "CREATE TABLE SHORTANSWER " +
					"(Question_ID	INT PRIMARY KEY		NOT NULL, " +
					"Question		TEXT 				NOT NULL)";
		}
		if(s.compareTo("Answer")==0)
		{
			sql = "CREATE TABLE ANSWERS " +
					"(Answer_ID		INT PRIMARY KEY		NOT NULL, " +
					"Question_ID	INT 				NOT NULL, " +
					"Answer 		TEXT				NOT NULL)";
		}
		if(s.compareTo("HS")==0)
		{
			sql = "CREATE TABLE HIGHSCORES " +
					"(HighScore_ID	INT PRIMARY KEY		NOT NULL, " +
					"HighScore		INT 				NOT NULL, " +
					"NAME 			TEXT				NOT NULL)";
		}
		if(s.compareTo("Save")==0)
		{
			sql = "CREATE TABLE SAVES " +
					"(Save_ID		INT PRIMARY KEY		NOT NULL, " +
					"Address		TEXT 				NOT NULL, " +
					"Answer 		char(10))";
		}
		
		this.stmt.executeUpdate(sql);
	    this.stmt.close();
	}
	
	
	
	public void fillQuestion() throws SQLException
	{
		Scanner myfile = new Scanner (System.in);
		int qID;
		int aID;
		String s;
		String type;
		String sql;
		
		try
		{
			myfile = new Scanner(new File("questions.txt"));
		}
		catch(IOException e)
		{
			System.out.println("Not a File");
		}
		
		while(myfile.hasNextLine())
		{
			qID = myfile.nextInt();
			aID = myfile.nextInt();
			type = myfile.nextLine();
			s = myfile.nextLine();
			
			this.stmt = this.conn.createStatement();
			
			sql = "INSERT INTO QUESTIONS (Question_ID, Answer_ID, Type) "+
					"VALUES("+qID+", "+aID+", "+type+" );";
			this.stmt.executeUpdate(sql);
			
			if(type.compareTo("MC")==0)
			{
				sql = "INSERT INTO MULTIPLECHOICE (Question_ID, Question) "+
						"VALUES("+qID+", "+s+" );";
				this.stmt.executeUpdate(sql);
			}
			
			if(type.compareTo("TF")==0)
			{
				sql = "INSERT INTO TRUEFALSE (Question_ID, Question) "+
						"VALUES("+qID+", "+s+" );";
				this.stmt.executeUpdate(sql);
			}
			
			if(type.compareTo("SA")==0)
			{
				sql = "INSERT INTO SHORTANSWER (Question_ID, Question) "+
						"VALUES("+qID+", "+s+" );";
				this.stmt.executeUpdate(sql);
			}
		}
		this.stmt.close();
	}
	
	
	public void fillAnswer() throws SQLException
	{
		Scanner myfile = new Scanner (System.in);
		int qID;
		int aID;
		String s;
		String sql;
		
		try
		{
			myfile = new Scanner(new File("answers.txt"));
		}
		catch(IOException e)
		{
			System.out.println("Not a File");
		}
		
		while(myfile.hasNextLine())
		{
			qID = myfile.nextInt();
			aID = myfile.nextInt();
			s = myfile.nextLine();
			
			this.stmt = this.conn.createStatement();
			
			sql = "INSERT INTO QUESTIONS (Answer_ID, Question_ID, Type) "+
					"VALUES("+aID+", "+qID+", "+s+" );";
			this.stmt.executeUpdate(sql);
		}
		this.stmt.close();
	}
	
	
	public void delete(int id) throws SQLException
	{
		this.stmt = this.conn.createStatement();
	    String sql = "DELETE from COMPANY where ID="+id+";";
	    this.stmt.executeUpdate(sql);

	    this.stmt.close();
	}
	
	
	public String retrieveQ(int id) throws SQLException
	{
		String type;
		String q;
		
		this.stmt = this.conn.createStatement();
		this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		
		while(rs.getInt("Question_ID") != id)
		{
			rs.next();
		}
		type = rs.getString("Type");
		
		this.rs = this.stmt.executeQuery("SELECT * FROM "+type+";");
		
		while(rs.getInt("Question_ID") != id)
		{
			rs.next();
		}
		q = rs.getString("Question");
		
		return q;
	}
	
	
	public String retrieveType(int id) throws SQLException
	{
		String type;
		
		this.stmt = this.conn.createStatement();
		this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		
		while(rs.getInt("Question_ID") != id)
		{
			rs.next();
		}
		type = rs.getString("Type");
		
		return type;
	}
	
	
	public int retrieveAID(int id) throws SQLException
	{
		int aID;
		
		this.stmt = this.conn.createStatement();
		this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		
		while(rs.getInt("Question_ID") != id)
		{
			rs.next();
		}
		aID = rs.getInt("Answer_ID");
		
		return aID;
	}
	
	
	public String retrieveA(int id) throws SQLException
	{
		String a;
		int aID;
		
		this.stmt = this.conn.createStatement();
		this.rs = this.stmt.executeQuery("SELECT * FROM QUESTIONS;");
		
		while(rs.getInt("Question_ID") != id)
		{
			rs.next();
		}
		aID = rs.getInt("Answer_ID");
		
		this.rs = this.stmt.executeQuery("SELECT * FROM ANSWERS;");
		
		while(rs.getInt("Answer_ID") != aID)
		{
			rs.next();
		}
		a = rs.getString("Answer");
		
		return a;
	}
}