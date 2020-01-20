package nachos.proj2.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.moandjiezana.toml.Toml;

import nachos.proj2.utilities.HelpDescription;

public class HelpCommand extends QueryCommand
{
	public HelpCommand(ArrayList<String> arguments)
	{
		super(arguments);
	}

	@Override
	public String execute()
	{
		ArrayList<String> arguments = this.getArguments();

		Toml toml = HelpDescription.getInstance().getHelps();

		String helpText = "";

		if (arguments.isEmpty())
		{
			return iterateHelp("", toml);
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

			if (helpText == null)
				return "Invalid arguments.";

			return String.format("/%s\n%s", String.join(" ", arguments), helpText);
		}
	}

	private String iterateHelp(String baseCommand, Toml toml)
	{
		StringBuilder sb = new StringBuilder();
		Set<Entry<String, Object>> helps = toml.entrySet();
		Iterator<Entry<String, Object>> helpsIterator = helps.iterator();

		while (helpsIterator.hasNext())
		{
			Entry<String, Object> help = helpsIterator.next();

			if (help.getValue() instanceof Toml)
			{

				if (!baseCommand.isEmpty())
					sb.append(String.format("/%s %s\n", baseCommand, help.getKey()));
				else
					sb.append(String.format("/%s\n", help.getKey()));

				Toml keys = (Toml) help.getValue();
				String commandDescription = keys.getString("description");
				sb.append(String.format("%s\n\n", commandDescription));

				sb.append(iterateHelp(help.getKey(), keys));
			}
		}

		return sb.toString();
	}
}
