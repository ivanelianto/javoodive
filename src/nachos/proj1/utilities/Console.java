package nachos.proj1.utilities;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class Console
{
	private static Console instance;
	private Semaphore sem;
	private SerialConsole console;
	private String userInput;

	private Console()
	{
		sem = new Semaphore(0);
		console = Machine.console();
		console.setInterruptHandlers(
				new ConsoleReceiveInterruptHandler(), 
				new ConsoleSendInterruptHandler());
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
		return userInput;
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

	class ConsoleReceiveInterruptHandler implements Runnable
	{
		private String userInput;

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
			sem.V();
		}
	}

}
