package nachos.proj2.commands.builders;

import java.util.ArrayList;

import nachos.proj2.commands.AddOrderCommand;
import nachos.proj2.commands.CancelOrderCommand;
import nachos.proj2.commands.HelpCommand;
import nachos.proj2.commands.ShowDebtCommand;
import nachos.proj2.commands.ShowSummaryCommand;

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
		else if (this.commandName.contentEquals("stat"))
		{
			ArrayList<String> arguments = this.getArguments();

			if (arguments.get(SUBCOMMAND_INDEX).contentEquals("debt"))
				return new ShowDebtCommand(this.getArguments());
			else if (arguments.get(SUBCOMMAND_INDEX).contentEquals("awesomeness"))
				return new ShowSummaryCommand(this.getArguments());
		}
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
