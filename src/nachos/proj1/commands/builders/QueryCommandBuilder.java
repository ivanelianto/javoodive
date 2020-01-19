package nachos.proj1.commands.builders;

import java.util.ArrayList;

import nachos.proj1.commands.CommandList;
import nachos.proj1.commands.QueryCommand;

public class QueryCommandBuilder extends BaseBuilder
{
	private static final int COMMAND_NAME_INDEX = 1;
	private String commandName;

	public QueryCommandBuilder(String query)
	{
		this.commandName = query.substring(COMMAND_NAME_INDEX);
		
		int firstSpaceIndex = query.indexOf(" ");

		if (firstSpaceIndex != -1)
		{
			String argumentsString = query.substring(firstSpaceIndex + 1);

			ArrayList<String> arguments = this.parseArgumentString(argumentsString);
			this.setArguments(arguments);
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
			QueryCommand queryCommand = (QueryCommand) CommandList.getInstance().getCommand(commandName).newInstance();
			queryCommand.setArguments(getArguments());
			return queryCommand;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
