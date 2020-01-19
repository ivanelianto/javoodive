package nachos.proj1.commands;

import java.util.ArrayList;

public class QueryCommand implements Command
{
	private ArrayList<String> arguments;

	public ArrayList<String> getArguments()
	{
		return arguments;
	}

	public void setArguments(ArrayList<String> arguments)
	{
		this.arguments = arguments;
	}

	@Override
	public String execute()
	{
		return null;
	}
}
