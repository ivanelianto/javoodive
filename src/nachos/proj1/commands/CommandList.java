package nachos.proj1.commands;

import java.util.HashMap;

public class CommandList
{
	private static CommandList instance;
	private final HashMap<String, Class<?>> commands;
	
	private CommandList()
	{
		commands = new HashMap<>();
		commands.put("help", HelpCommand.class);
	}
	
	public static CommandList getInstance()
	{
		if (instance == null)
			instance = new CommandList();
		return instance;
	}
	
	public Class<?> getCommand(String commandName)
	{
		return this.commands.get(commandName);
	}
}
