package nachos.proj1.utilities;

import com.moandjiezana.toml.Toml;

public class TomlConfigFile extends ConfigFile
{
	private Toml toml;
	
	public TomlConfigFile(String filepath, boolean isEncoded)
	{
		super(filepath, isEncoded);
		toml = new Toml();
	}

	@Override
	public Toml getResult()
	{
		return toml.read(getContent());
	}
}
