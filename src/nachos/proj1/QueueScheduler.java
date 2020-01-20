package nachos.proj1;

import nachos.threads.Scheduler;
import nachos.threads.ThreadQueue;

public class QueueScheduler extends Scheduler
{
	@Override
	public ThreadQueue newThreadQueue(boolean transferPriority)
	{
		return new MyThreadQueue();
	} 
}
