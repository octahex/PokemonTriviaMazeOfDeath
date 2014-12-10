package cscd350.ayic.utility;

public class test
{

	public static void main(String[] args)
	{
		DataBase db = DataBase.getInstance();
		
		int num = 2;
		
		String pokemon = db.retrieveSave(num);
		
		System.out.println(pokemon);

	}

}
