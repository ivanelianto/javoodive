package nachos.proj2.services;

import nachos.proj1.commands.QueryCommand;
import nachos.proj1.commands.builders.QueryCommandBuilder;
import nachos.proj1.models.User;

public class CommandService
{
	private static final String COMMAND_MESSAGE_INDICATOR = "/";
	private static CommandService instance;
	private User sender;

	public static CommandService getInstance()
	{
		if (instance == null)
			instance = new CommandService();
		return instance;
	}

	public User getSender()
	{
		return sender;
	}

	public void setSender(User sender)
	{
		this.sender = sender;
	}

	public String interpret(String messageContent)
	{
		if (messageContent.startsWith(COMMAND_MESSAGE_INDICATOR))
		{
			QueryCommandBuilder builder = new QueryCommandBuilder(messageContent);
			QueryCommand command = (QueryCommand) builder.getResult();
			String executionResult = command.execute();

			return executionResult == null ? "" : executionResult;
		}

		return "";
	}
}
