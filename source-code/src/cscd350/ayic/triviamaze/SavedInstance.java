package cscd350.ayic.triviamaze;

import java.awt.Point;

import cscd350.ayic.utility.DataBase;

public class SavedInstance
{
	private String _name;
	private String _code;

	public SavedInstance()
	{
	}

	public SavedInstance(int id)
	{
		load(id);
	}

	public SavedInstance(String name, String code)
	{
		_name = name;
		_code = code;
	}

	public void save()
	{
		DataBase.getInstance().save(_name, _code);
	}

	private void load(int id)
	{
		_code = DataBase.getInstance().retrieveSave(id);
	}
}
