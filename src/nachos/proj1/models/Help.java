package nachos.proj1.models;

import nachos.proj1.commands.Command;

public class Help
{
	private String description;
	private Command command;

	public Help(String description, Command command)
	{
		this.description = description;
		this.command = command;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Command getCommand()
	{
		return command;
	}

	public void setCommand(Command command)
	{
		this.command = command;
	}
}
