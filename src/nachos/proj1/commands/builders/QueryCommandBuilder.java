package nachos.proj1.commands.builders;

import java.util.ArrayList;

import nachos.proj1.commands.HelpCommand;
import nachos.proj1.commands.QueryCommand;

public class QueryCommandBuilder extends BaseBuilder
{
	private static final int COMMAND_NAME_INDEX = 1;
	private String commandName;

	public QueryCommandBuilder(String query)
	{
		int firstSpaceIndex = query.indexOf(" ");
		
		if (firstSpaceIndex != -1)
		{
			this.commandName = query.substring(COMMAND_NAME_INDEX, firstSpaceIndex);
			
			String argumentsString = query.substring(firstSpaceIndex + 1);

			ArrayList<String> arguments = this.parseArgumentString(argumentsString);
			this.setArguments(arguments);
		}
		else
		{
			this.commandName = query.substring(COMMAND_NAME_INDEX);
		}
	}

	public ArrayList<String> parseArgumentString(String argumentString)
	{
		ArrayList<String> arguments = new ArrayList<String>();

		String[] queryParts = argumentString.split(" ");

		for (String queryPart : queryParts)
		{
			if (queryPart.isEmpty())
				continue;

			arguments.add(queryPart.trim());
		}

		return arguments;
	}

	@Override
	public Object getResult()
	{
		try
		{
			QueryCommand queryCommand = null;
			
			switch (this.commandName)
			{
				case "help":
					queryCommand = new HelpCommand(this.getArguments());
					break;
			}
			
			
			return queryCommand;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
