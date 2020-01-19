package nachos.proj1.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.moandjiezana.toml.Toml;

import nachos.machine.Machine;
import nachos.machine.OpenFile;

public class HelpCommand extends QueryCommand
{
	private static final String HELP_FILENAME = "help.toml";
	private static String rawHelpText;
	private ArrayList<String> arguments;

	public HelpCommand(ArrayList<String> arguments)
	{
		this.arguments = arguments;
	}

	@Override
	public void execute()
	{
		if (HelpCommand.rawHelpText == null)
		{
			OpenFile file = Machine.stubFileSystem().open(HELP_FILENAME, false);
			byte[] bytes = new byte[file.length()];
			file.read(bytes, 0, bytes.length);
			file.close();

			HelpCommand.rawHelpText = new String(bytes);
		}

		String helpText = "";
		Toml toml = new Toml().read(rawHelpText);

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

		System.out.println(helpText);
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
