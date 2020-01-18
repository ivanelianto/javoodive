package nachos.proj1.facades;

import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.proj1.models.Message;
import nachos.proj1.models.TextMessage;
import nachos.proj1.models.User;
import nachos.proj1.repository.UserRepository;
import nachos.threads.Semaphore;

public class MessageFacade
{
	private static final int MESSAGE_CONTENT_INDEX = 3;
	private static final int USER_INDEX = 2;
	private static final int DESTINATION_ADDRESS_INDEX = 1;
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

	public void sendMessage(NetworkLink nl, Semaphore semaphore, Message message)
	{
		try
		{
			String content = message.toString();
			Packet packet = new Packet(message.getDstAddress(), nl.getLinkAddress(), content.getBytes());
			nl.send(packet);
			semaphore.P();
		}
		catch (MalformedPacketException e)
		{
			e.printStackTrace();
		}
	}

	public TextMessage parseTextMessage(String rawData)
	{
		String[] data = rawData.split(TextMessage.MESSAGE_PART_DELIMETER);

		int dstAddress = Integer.parseInt(data[DESTINATION_ADDRESS_INDEX]);
		
		String userId = data[USER_INDEX];
		User user = UserRepository.findById(userId);

		String messageContent = data[MESSAGE_CONTENT_INDEX];

		return new TextMessage(dstAddress, user, messageContent);
	}
}
