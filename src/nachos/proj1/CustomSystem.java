package nachos.proj1;

import nachos.machine.NetworkLink;
import nachos.proj1.utilities.Console;
import nachos.threads.Semaphore;

public interface CustomSystem
{
	void boot();
	Console getConsole();
	Semaphore getSemaphore();
	NetworkLink getNetworkLink();
}
