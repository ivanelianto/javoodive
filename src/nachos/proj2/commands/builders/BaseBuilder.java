package nachos.proj2.commands.builders;

import java.util.ArrayList;

public abstract class BaseBuilder
{
	private ArrayList<String> arguments;

	public BaseBuilder()
	{
		this.arguments = new ArrayList<String>();
	}

	public ArrayList<String> getArguments()
	{
		return arguments;
	}

	public void setArguments(ArrayList<String> arguments)
	{
		this.arguments = arguments;
	}

	public abstract Object getResult();
}
