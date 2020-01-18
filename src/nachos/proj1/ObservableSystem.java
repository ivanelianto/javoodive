package nachos.proj1;

import nachos.machine.NetworkLink;
import nachos.proj1.models.TextMessage;
import nachos.proj1.utilities.Console;
import nachos.threads.Semaphore;

public interface ObservableSystem
{
	void boot();
	void displayReceivedMessage(TextMessage message);
	Console getConsole();
	Semaphore getSemaphore();
	NetworkLink getNetworkLink();
}
