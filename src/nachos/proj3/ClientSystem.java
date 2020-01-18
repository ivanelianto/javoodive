package nachos.proj3;

import java.awt.Toolkit;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.proj1.MainSystem;
import nachos.proj1.Mediator;
import nachos.proj1.ObservableSystem;
import nachos.proj1.facades.MessageFacade;
import nachos.proj1.models.TextMessage;
import nachos.proj1.models.User;
import nachos.proj1.repository.UserRepository;
import nachos.proj1.utilities.Console;
import nachos.proj1.utilities.DateHelper;
import nachos.threads.Semaphore;

public class ClientSystem implements ObservableSystem
{
	private Console console;
	private Semaphore semaphore;
	private NetworkLink nl;
	private User user;
	private Mediator mediator;

	public ClientSystem(Mediator mediator, int userIndex)
	{
		this.mediator = mediator;
		this.user = UserRepository.getByIndex(userIndex);
		this.console = Console.getInstance();
		this.semaphore = new Semaphore(0);
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(new ClientReceiveInterruptHandler(),
				new ClientSendInterruptHandler());
		boot();
	}

	@Override
	public void boot()
	{
		console.println("Auto logged-in system, logged-in as :\n");
		this.user.printUserInfo();
		console.printLineSeparator();
		MainSystem.printGreetingMesssage();

		Toolkit.getDefaultToolkit().beep();

		while (true)
		{
			System.out.print(String.format("%s@javoodive => ", this.user.getUsername()));
			String userInput = console.read();

			TextMessage message = new TextMessage(MainSystem.SERVER_ADDRESS, this.user, userInput);
			MessageFacade.getInstance().sendMessage(this.nl, this.semaphore, message);
		}
	}

	@Override
	public Console getConsole()
	{
		return this.console;
	}

	@Override
	public Semaphore getSemaphore()
	{
		return this.semaphore;
	}

	@Override
	public NetworkLink getNetworkLink()
	{
		return this.nl;
	}

	@Override
	public void displayReceivedMessage(TextMessage message)
	{
		System.out.printf("%s | %s :\n%s\n", DateHelper.getCurrentFormattedDate(), message.getUser().getUsername(),
				message.getContent());

		System.out.print(String.format("\n\n%s@javoodive => ", this.user.getUsername()));
	}

	class ClientReceiveInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			Packet packet = nl.receive();
			String encodedData = new String(packet.contents);
			TextMessage message = MessageFacade.getInstance().parseTextMessage(encodedData);
			displayReceivedMessage(message);
			semaphore.V();
		}
	}

	class ClientSendInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			semaphore.V();
		}
	}
}
