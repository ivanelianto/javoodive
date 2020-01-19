package nachos.proj2.utilities;

import com.moandjiezana.toml.Toml;

import nachos.proj1.utilities.TomlConfigFile;

public class HelpDescription
{
	private static final String HELP_FILENAME = "help.toml";
	private static HelpDescription instance;
	private Toml toml;

	private HelpDescription()
	{
		TomlConfigFile tomlConfigFile = new TomlConfigFile(HELP_FILENAME, false);
		toml = tomlConfigFile.getResult();
	}
	
	public static HelpDescription getInstance()
	{
		if (instance == null)
			instance = new HelpDescription();
		return instance;
	}
	
	public Toml getHelps()
	{
		return this.toml;
	}
}
