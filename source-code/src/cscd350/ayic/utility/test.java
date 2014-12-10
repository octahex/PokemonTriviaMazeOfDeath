package cscd350.ayic.utility;

public class test
{

	public static void main(String[] args)
	{
		DataBase db = DataBase.getInstance();

		for(int i=0; i<10; i++)
			System.out.println(db.randomAID(false));

	}

}
