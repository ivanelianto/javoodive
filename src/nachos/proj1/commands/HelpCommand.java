package nachos.proj1.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.moandjiezana.toml.Toml;

import nachos.proj1.utilities.TomlConfigFile;

public class HelpCommand extends QueryCommand
{
	private static final String HELP_FILENAME = "help.toml";
	private ArrayList<String> arguments;
	private Toml toml;

	public HelpCommand(ArrayList<String> arguments)
	{
		super(arguments);
	}

	@Override
	public String execute()
	{
		if (toml == null)
		{
			TomlConfigFile tomlConfigFile = new TomlConfigFile(HELP_FILENAME, false);
			toml = tomlConfigFile.getResult();
		}

		String helpText = "";
		
		if (arguments.size() < 1)
		{
			iterateHelp("", toml);
		}
		else
		{
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < arguments.size(); i++)
			{
				String argument = arguments.get(i);
				sb.append(argument);
				sb.append(".");
			}

			sb.append("description");

			helpText = toml.getString(sb.toString());
		}
		
		if (helpText == null)
			return "Undefined command.";
		else
		{
			String command = String.join(" ", arguments);
			
			return String.format("/%s\n%s", command, helpText);
		}
	}

	private void iterateHelp(String baseCommand, Toml toml)
	{
		Set<Entry<String, Object>> helps = toml.entrySet();
		Iterator<Entry<String, Object>> helpsIterator = helps.iterator();
		while (helpsIterator.hasNext())
		{
			Entry<String, Object> help = helpsIterator.next();

			if (help.getValue() instanceof Toml)
			{
				if (!baseCommand.isEmpty())
					System.out.printf("/%s %s\n", baseCommand, help.getKey());
				else
					System.out.printf("/%s\n", help.getKey());

				Toml keys = (Toml) help.getValue();
				String commandDescription = keys.getString("description");
				System.out.printf("%s\n\n", commandDescription);

				iterateHelp(help.getKey(), keys);
			}
		}
	}
}
