package nachos.proj1.commands;

import java.util.HashMap;

public class CommandList
{
	private static final HashMap<String, Class<?>> commands = new HashMap<>();
	
	static
	{
		commands.put("help", HelpCommand.class);
	}
}
