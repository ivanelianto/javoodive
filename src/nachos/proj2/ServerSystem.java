package nachos.proj2;

import java.awt.Toolkit;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.proj1.MainSystem;
import nachos.proj1.utilities.Console;
import nachos.threads.Semaphore;

public class ServerSystem
{
	private Console console;
	private Semaphore sem;
	private NetworkLink nl;
	
	public ServerSystem()
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
		
		MainSystem.printGreetingMesssage();
		
		while (true)
		{
			System.out.print("server@javoodive => ");
			
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
