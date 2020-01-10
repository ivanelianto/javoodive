package nachos.proj1.utilities;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class Console
{
	private static Console instance;
	private Semaphore sem;
	private SerialConsole console;
	private ConsoleReceiveInterruptHandler receiveInterruptHandler;
	private ConsoleSendInterruptHandler sendInterruptHandler;

	private Console()
	{
		sem = new Semaphore(0);
		console = Machine.console();
		
		receiveInterruptHandler = new ConsoleReceiveInterruptHandler();
		sendInterruptHandler = new ConsoleSendInterruptHandler();
		console.setInterruptHandlers(receiveInterruptHandler,
				sendInterruptHandler);
	}

	public static Console getInstance()
	{
		if (instance == null)
			instance = new Console();

		return instance;
	}

	public String read()
	{
		sem.P();
		return receiveInterruptHandler.getUserInput();
	}

	public int readInt()
	{
		int userInputNumber = -1;

		try
		{
			userInputNumber = Integer.parseInt(read());
		}
		catch (Exception e)
		{

		}

		return userInputNumber;
	}

	public void print(Object obj)
	{
		byte[] bytes = obj.toString().getBytes();

		for (byte _byte : bytes)
		{
			console.writeByte(_byte);
			sem.P();
		}
	}

	public void println()
	{
		print("\n");
	}

	public void println(Object obj)
	{
		print(obj + "\n");
	}
	
	public void cls()
	{
		for (int i = 0; i < 25; i++)
			println();
	}
	
	public void printLineSeparator()
	{
		for (int i = 0; i < 25; i++)
			print("=");
		
		println();
	}

	class ConsoleReceiveInterruptHandler implements Runnable
	{
		private String userInput;
		
		public ConsoleReceiveInterruptHandler()
		{
			this.userInput = "";
		}

		@Override
		public void run()
		{
			char userInputChar = (char) console.readByte();
			
			if (userInputChar == '\n')
				sem.V();
			else
				userInput += userInputChar;
		}

		public String getUserInput()
		{
			return userInput;
		}
	}

	class ConsoleSendInterruptHandler implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			sem.V();
		}
	}

}
