package cscd350.ayic.utility;

public class test
{

	public static void main(String[] args)
	{
		DataBase db = new DataBase();
		
		int num = 1;
		
		String pokemon = db.retrieveA(num);
		
		System.out.println(pokemon);

	}

}
