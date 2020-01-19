package nachos.proj1.utilities;

import nachos.machine.Machine;
import nachos.machine.OpenFile;

public abstract class ConfigFile
{
	private String filepath;
	private boolean isEncoded;
	private String content;

	public ConfigFile(String filepath, boolean isEncoded)
	{
		this.filepath = filepath;
		this.isEncoded = isEncoded;

		readContent();
	}

	private void readContent()
	{
		OpenFile file = Machine.stubFileSystem().open(filepath, false);
		byte[] bytes = new byte[file.length()];
		file.read(bytes, 0, bytes.length);
		file.close();

		this.content = new String(bytes);

		if (isEncoded)
			this.content = Concealer.getInstance().decode(content);
	}

	public String getContent()
	{
		return this.content;
	}

	public abstract Object getResult();
}
