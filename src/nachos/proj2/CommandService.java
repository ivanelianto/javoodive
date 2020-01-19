package nachos.proj2;

import nachos.proj1.commands.QueryCommand;
import nachos.proj1.commands.builders.QueryCommandBuilder;

public class CommandService
{
	private static final String COMMAND_MESSAGE_INDICATOR = "/";
	private static CommandService instance;
	
	public static CommandService getInstance()
	{
		if (instance == null)
			instance = new CommandService();
		return instance;
	}
	
	public void interpret(String messageContent)
	{
		if (messageContent.startsWith(COMMAND_MESSAGE_INDICATOR))
		{
			QueryCommandBuilder builder = new QueryCommandBuilder(messageContent);
			QueryCommand command = (QueryCommand) builder.getResult();
			command.execute();
		}
	}
}
