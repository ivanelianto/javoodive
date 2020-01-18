package nachos.proj1;

import java.util.ArrayList;
import java.util.Random;

import org.yaml.snakeyaml.Yaml;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.machine.OpenFile;
import nachos.machine.Packet;
import nachos.proj1.facades.MessageFacade;
import nachos.proj1.models.NewInstanceMessage;
import nachos.proj1.models.TextMessage;
import nachos.proj1.models.UserYamlConstructor;
import nachos.proj1.repository.UserRepository;
import nachos.proj1.utilities.Concealer;
import nachos.proj1.utilities.Console;
import nachos.proj2.ServerSystem;
import nachos.proj3.ClientSystem;
import nachos.threads.Semaphore;

public class MainSystem implements Mediator
{
	public static final int MEDIATOR_ADDRESS = 0;
	public static final int SERVER_ADDRESS = 1;
	private static final String USER_FILENAME = "users.b40";
	private static final String HELP_FILENAME = "help.toml";
	private NetworkLink nl;
	private ArrayList<Integer> addresses;
	private Semaphore semaphore;

	public MainSystem()
	{
		// OpenFile file = Machine.stubFileSystem().open(HELP_FILENAME, false);
		// byte[] bytes = new byte[file.length()];
		// file.read(bytes, 0, bytes.length);
		// file.close();
		//
		// String rawHelpText = new String(bytes);
		//
		// Toml toml = new Toml().read(rawHelpText);
		// String open = toml.getString("order.open.description");
		// System.out.println(open);

		loadUsers();
		boot();
	}

	public static void printGreetingMesssage()
	{
		String text = String.format("Welcome, to Javoodive. Type \"./help\" to view all commands help.");

		System.out.println(text);
	}

	private void boot()
	{
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(new MainSystemReceiveInterruptHandler(), new MainSystemSendInterruptHandler());
		this.addresses = new ArrayList<>();
		this.semaphore = new Semaphore(0);
		autoLoginByLinkAddress();
		Console.getInstance().read();
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
		if (this.nl.getLinkAddress() == MEDIATOR_ADDRESS)
		{
			System.out.println("Mediator system booted-up.");
		}
		else
		{
			Random random = new Random();
			int randomIndex = random.nextInt(UserRepository.size());

			this.notifyMediator(nl.getLinkAddress(), randomIndex);

			if (nl.getLinkAddress() == SERVER_ADDRESS)
				new ServerSystem(this);
			else
				new ClientSystem(this, randomIndex);
		}
	}

	@Override
	public void broadcast(TextMessage message)
	{
		message.setDstAddress(MEDIATOR_ADDRESS);
		MessageFacade.getInstance().sendMessage(nl, semaphore, message);
	}

	@Override
	public void notifyMediator(int srcAddress, int userIndex)
	{
		NewInstanceMessage instanceMessage = new NewInstanceMessage(nl.getLinkAddress(), MEDIATOR_ADDRESS, userIndex);
		MessageFacade.getInstance().sendMessage(nl, semaphore, instanceMessage);
	}

	class MainSystemReceiveInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			Packet packet = nl.receive();

			String content = new String(packet.contents);
			
			String[] rawData = content.split("@@@");

			switch (rawData[0])
			{
				case "TextMessage":
					processTextMessage(content);
					break;
				case "NewInstanceMessage":
					processNewInstanceMessage(rawData);
					break;
			}

			semaphore.V();
		}

		private void processTextMessage(String rawData)
		{
			for (Integer address : addresses)
			{
				TextMessage message = MessageFacade.getInstance().parseTextMessage(rawData);
				System.out.printf("%d:%s\n", address, message.getContent());
				message.setDstAddress(address);
				MessageFacade.getInstance().sendMessage(nl, semaphore, message);
			}
		}

		private void processNewInstanceMessage(String... rawData)
		{
			int senderAddress = Integer.parseInt(rawData[1]);
			addresses.add(senderAddress);
		}
	}

	class MainSystemSendInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			semaphore.V();
		}
	}
}
