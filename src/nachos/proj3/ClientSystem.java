package nachos.proj3;

import java.awt.Toolkit;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.proj1.CustomSystem;
import nachos.proj1.MainSystem;
import nachos.proj1.facades.MessageFacade;
import nachos.proj1.models.Message;
import nachos.proj1.models.User;
import nachos.proj1.utilities.Console;
import nachos.threads.Semaphore;

public class ClientSystem implements CustomSystem
{
	private static final int SERVER_ADDRESS = 0;
	private Console console;
	private Semaphore sem;
	private NetworkLink nl;
	private User user;
	private MessageFacade messageManager;
	
	public ClientSystem(User user)
	{
		this.user = user;
		
		boot();
		
		MainSystem.printGreetingMesssage();
		
		Toolkit.getDefaultToolkit().beep();
		
		while(true)
		{
			System.out.print(String.format("%s@javoodive => ", this.user.getUsername()));
			
			String userInput = console.read();
			
			Message message = new Message(this.user, SERVER_ADDRESS, userInput);
			
			messageManager.sendMessage(this.nl, this.sem, message);
		}
	}
	
	class ReceiveInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
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

	@Override
	public void boot()
	{
		this.console = Console.getInstance();
		this.sem  = new Semaphore(0);
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(
				new ReceiveInterruptHandler(), 
				new SendInterruptHandler());
		this.messageManager = MessageFacade.getInstance();
		
		console.println("Auto logged-in system, logged-in as :\n");
		this.user.printUserInfo();
		console.printLineSeparator();
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
}
