package nachos.proj1;

import nachos.proj1.models.TextMessage;

public interface Mediator
{
	void broadcast(TextMessage message);
	void notifyMediator(int srcAddress, int userIndex);
}
