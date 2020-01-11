package nachos.proj2;

import java.awt.Toolkit;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.proj1.CustomSystem;
import nachos.proj1.facades.MessageFacade;
import nachos.proj1.models.Message;
import nachos.proj1.utilities.Console;
import nachos.proj1.utilities.DateHelper;
import nachos.threads.Semaphore;

public class ServerSystem implements CustomSystem
{
	private Console console;
	private Semaphore sem;
	private NetworkLink nl;
	
	public ServerSystem()
	{
		boot();
		
		console.read();
	}

	@Override
	public void boot()
	{
		this.console = Console.getInstance();
		this.sem = new Semaphore(0);
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(
				new ReceiveInterruptHandler(), 
				new SendInterruptHandler());

		console.println("Server Booted Up.");
		console.printLineSeparator();
		Toolkit.getDefaultToolkit().beep();
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
	
	class ReceiveInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			Packet packet = nl.receive();
			Message message = MessageFacade.getInstance().parseMessage(packet);
			System.out.printf("%s | %s : %s\n",
					DateHelper.getCurrentFormattedDate(),
					message.getSender().getUsername(),
					message.getContent());
			sem.V();
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
