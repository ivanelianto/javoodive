package nachos.proj1.facades;

import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.proj1.models.Message;
import nachos.proj1.models.User;
import nachos.proj1.utilities.Concealer;
import nachos.threads.Semaphore;

public class MessageFacade
{
	private static MessageFacade instance;
	
	private MessageFacade()
	{
	}

	public static MessageFacade getInstance()
	{
		if (instance == null)
			instance = new MessageFacade();
		return instance;
	}

	public void sendMessage(NetworkLink nl, Semaphore sem, Message message)
	{
		int address = nl.getLinkAddress();

		try
		{
			String content = message.toString();
			content = Concealer.getInstance().encode(content);
			
			Packet packet = new Packet(message.getDestinationAddress(), address, content.getBytes());
			nl.send(packet);
			sem.P();
		}
		catch (MalformedPacketException e)
		{
			e.printStackTrace();
		}
	}
	
	public Message parseMessage(Packet packet)
	{
		String encodedData = new String(packet.contents);
		String rawData = Concealer.getInstance().decode(encodedData);
		String[] data = rawData.split("@@@");
		
		User user = getUserFromMessage(data[1]);
		String messageContent = data[2];
		
		return new Message(user, packet.dstLink, messageContent);
	}
	
	private User getUserFromMessage(String rawUser)
	{
		String[] fields = rawUser.split("#");
		
		return new User(
				fields[0],
				fields[1],
				fields[2],
				Integer.parseInt(fields[3]));
	}
}
