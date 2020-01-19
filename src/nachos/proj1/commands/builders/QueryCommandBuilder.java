package nachos.proj1.commands.builders;

import java.util.ArrayList;

import nachos.proj1.commands.AddOrderCommand;
import nachos.proj1.commands.CancelOrderCommand;
import nachos.proj1.commands.HelpCommand;

public class QueryCommandBuilder extends BaseBuilder
{
	private static final int SUBCOMMAND_INDEX = 0;
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
		if (this.commandName.contentEquals("help"))
			return new HelpCommand(this.getArguments());
		else if (this.commandName.contentEquals("order"))
		{
			ArrayList<String> arguments = this.getArguments();

			if (arguments.get(SUBCOMMAND_INDEX).contentEquals("add"))
				return new AddOrderCommand(this.getArguments());
			else if (arguments.get(SUBCOMMAND_INDEX).contentEquals("cancel"))
				return new CancelOrderCommand(this.getArguments());
		}

		return null;
	}
}
