package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;
import nachos.proj1.utilities.Console;
import nachos.proj2.ServerSystem;

public class MainSystem
{
	private static final int SERVER_ADDRESS = 0;
	private Console console;
	private NetworkLink nl;

	public MainSystem()
	{
		boot();
		autoLoginByLinkAddress();
	}

	public void boot()
	{
		console = Console.getInstance();
		nl = Machine.networkLink();
	}
	
	public void autoLoginByLinkAddress()
	{
		if (this.nl.getLinkAddress() == SERVER_ADDRESS) 
		{
			new ServerSystem();
		}
	}
}
