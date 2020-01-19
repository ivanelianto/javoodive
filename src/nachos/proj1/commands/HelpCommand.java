package nachos.proj1.commands;

import com.moandjiezana.toml.Toml;

import nachos.machine.Machine;
import nachos.machine.OpenFile;

public class HelpCommand implements Command
{
	private static final String HELP_FILENAME = "help.toml";
	private String commandName;
	private static String rawHelpText;
	
	public HelpCommand(String commandName)
	{
		this.commandName = commandName;
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

		Toml toml = new Toml().read(rawHelpText);
		String helpText = toml.getString(commandName);
		System.out.println(helpText);
	}
}
