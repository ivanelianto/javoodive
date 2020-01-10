package nachos.proj3;

import java.awt.Toolkit;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.proj1.MainSystem;
import nachos.proj1.models.User;
import nachos.proj1.utilities.Console;
import nachos.threads.Semaphore;

public class ClientSystem
{
	private Console console;
	private Semaphore sem;
	private NetworkLink nl;
	private User user;
	
	public ClientSystem(User user)
	{
		this.console = Console.getInstance();
		this.sem  = new Semaphore(0);
		this.nl = Machine.networkLink();
		this.nl.setInterruptHandlers(
				new ReceiveInterruptHandler(), 
				new SendInterruptHandler());
		this.user = user;
		
		console.println("Auto logged-in system, logged-in as :\n");
		this.user.printUserInfo();
		console.printLineSeparator();
		
		MainSystem.printGreetingMesssage();
		
		Toolkit.getDefaultToolkit().beep();
		
		while(true)
		{
			System.out.print(String.format("%s@javoodive => ", this.user.getUsername()));
			
			String userInput = console.read();
		}
	}
	
	class ReceiveInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			
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
