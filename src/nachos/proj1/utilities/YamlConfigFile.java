package nachos.proj1.utilities;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlConfigFile<T> extends ConfigFile
{
	private Yaml yaml;

	public YamlConfigFile(String filepath, boolean isEncoded, Constructor constructor)
	{
		super(filepath, isEncoded);
		this.yaml = new Yaml(constructor);
	}

	@Override
	public T getResult()
	{
		return yaml.load(getContent());
	}
}
