package nachos.proj1.commands.invokers;

import nachos.proj1.commands.Command;

public class SingleCommandInvoker
{
	private Command command;
	
	public SingleCommandInvoker(Command command)
	{
		this.command.execute();
	}
}
