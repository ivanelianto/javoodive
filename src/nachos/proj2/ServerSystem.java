package nachos.proj2;

import java.awt.Toolkit;
import java.util.Date;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.proj1.ObservableSystem;
import nachos.proj1.facades.MessageFacade;
import nachos.proj1.models.TextMessage;
import nachos.proj1.utilities.Console;
import nachos.proj1.utilities.DateHelper;
import nachos.threads.Semaphore;

public class ServerSystem implements ObservableSystem
{
	private Console console;
	private Semaphore sem;
	private NetworkLink nl;

	public ServerSystem()
	{
		this.console = Console.getInstance();
		this.sem = new Semaphore(0);
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(new ReceiveInterruptHandler(), new SendInterruptHandler());
		boot();
	}

	@Override
	public void boot()
	{
		console.println("Server Booted Up.");
		console.printLineSeparator();
		Toolkit.getDefaultToolkit().beep();
		console.read();
	}

	@Override
	public Console getConsole()
	{
		return this.console;
	}

	@Override
	public Semaphore getSemaphore()
	{
		return this.sem;
	}

	@Override
	public NetworkLink getNetworkLink()
	{
		return this.nl;
	}

	@Override
	public void displayReceivedMessage(TextMessage message)
	{
		System.out.printf("%s | %s :\n%s\n\n", DateHelper.getFormattedDate(new Date()), message.getUser().getUsername(),
				message.getContent());
	}

	class ReceiveInterruptHandler implements Runnable
	{
		private static final String BOT_NAME = "Javoodive";

		@Override
		public void run()
		{
			Packet packet = nl.receive();
			sem.V();

			String rawData = new String(packet.contents);
			TextMessage message = MessageFacade.getInstance().parseTextMessage(rawData);

			displayReceivedMessage(message);

			CommandService service = CommandService.getInstance();
			service.setSender(message.getUser());
			String response = service.interpret(message.getContent());

			if (!response.isEmpty())
				System.out.printf("%s | %s :\n%s\n\n", DateHelper.getFormattedDate(new Date()), BOT_NAME,
						response.trim());
		}
	}

	class SendInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			sem.V();
		}
	}
}
