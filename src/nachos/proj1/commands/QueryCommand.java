package nachos.proj1.commands;

import java.util.ArrayList;

public abstract class QueryCommand implements Command
{
	private ArrayList<String> arguments;
	
	public QueryCommand(ArrayList<String> arguments)
	{
		this.arguments = arguments;
	}

	public ArrayList<String> getArguments()
	{
		return arguments;
	}

	public void setArguments(ArrayList<String> arguments)
	{
		this.arguments = arguments;
	}
}
