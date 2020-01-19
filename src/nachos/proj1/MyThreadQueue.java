package nachos.proj1;

import java.util.LinkedList;

import nachos.threads.KThread;
import nachos.threads.ThreadQueue;

public class MyThreadQueue extends ThreadQueue
{
	private LinkedList<KThread> threads;
	
	public MyThreadQueue()
	{
		threads = new LinkedList<KThread>();
	}

	@Override
	public void waitForAccess(KThread thread)
	{
		threads.addLast(thread);
	}

	@Override
	public KThread nextThread()
	{
		if (threads.isEmpty())
			return null;
		return threads.pop();
	}

	@Override
	public void acquire(KThread thread)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void print()
	{
		// TODO Auto-generated method stub
	}
}
