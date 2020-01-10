package nachos.proj1;

import java.util.Random;

import org.yaml.snakeyaml.Yaml;

import com.moandjiezana.toml.Toml;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.machine.OpenFile;
import nachos.proj1.models.UserYamlConstructor;
import nachos.proj1.repository.UserRepository;
import nachos.proj1.utilities.Concealer;
import nachos.proj2.ServerSystem;
import nachos.proj3.ClientSystem;

public class MainSystem
{
	private static final int SERVER_ADDRESS = 0;
	private static final String USER_FILENAME = "users.b40";
	private static final String HELP_FILENAME = "help.toml";
	private NetworkLink nl;

	public MainSystem()
	{
		OpenFile file = Machine.stubFileSystem().open(HELP_FILENAME, false);
		byte[] bytes = new byte[file.length()];
		file.read(bytes, 0, bytes.length);
		file.close();

		String rawHelpText = new String(bytes);
		
		Toml toml = new Toml().read(rawHelpText);
		String open = toml.getString("order.open.description");
		System.out.println(open);
		
//		boot();
//		loadUsers();
//		autoLoginByLinkAddress();
	}

	public static void printGreetingMesssage()
	{
		String text = String.format("Welcome, to Javoodive. Type \"./help\" to view all commands help.");
		
		System.out.println(text);
	}
	
	private void boot()
	{
		nl = Machine.networkLink();
	}

	private void loadUsers()
	{
		OpenFile file = Machine.stubFileSystem().open(USER_FILENAME, false);
		byte[] bytes = new byte[file.length()];
		file.read(bytes, 0, bytes.length);
		file.close();

		String encodedText = new String(bytes);
		String decodedText = Concealer.getInstance().decode(encodedText);
		
		Yaml yaml = new Yaml(new UserYamlConstructor());
		UserRepository.load(yaml.load(decodedText));
	}

	private void autoLoginByLinkAddress()
	{
		if (this.nl.getLinkAddress() == SERVER_ADDRESS)
		{
			new ServerSystem();
		}
		else
		{
			Random random = new Random();
			int randomIndex = random.nextInt(UserRepository.size());
			
			new ClientSystem(UserRepository.getByIndex(randomIndex));
		}
	}
}
