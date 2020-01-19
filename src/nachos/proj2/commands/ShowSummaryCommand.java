package nachos.proj2.commands;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nachos.proj1.models.Transaction;
import nachos.proj1.models.User;
import nachos.proj1.repository.TransactionRepository;
import nachos.proj2.services.CommandService;

public class ShowSummaryCommand extends QueryCommand
{
	public ShowSummaryCommand(ArrayList<String> arguments)
	{
		super(arguments);
	}

	@Override
	public String execute()
	{
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> menuOrderCounts = new HashMap<String, Integer>();

		User user = CommandService.getInstance().getSender();
		List<Transaction> transactions = TransactionRepository.findByUser(user);
		int total = 0;
		for (int i = 0; i < transactions.size(); i++)
		{
			Transaction transaction = transactions.get(i);

			String menuName = transaction.getMenu().getName();
			Integer previousQuantity = menuOrderCounts.putIfAbsent(menuName, transaction.getQuantity());

			if (previousQuantity != null)
				menuOrderCounts.put(menuName, previousQuantity + transaction.getQuantity());

			int subtotal = transaction.getQuantity() * transaction.getMenu().getSellPrice();
			total += subtotal;
		}

		SimpleEntry<String, Integer> favouriteMenu = getFavouriteMenu(menuOrderCounts);
		
		sb.append(String.format("%s is %s favourite menu!", favouriteMenu.getKey(), user.getUsername()));
		sb.append("\n");
		sb.append(String.format("Ordered %d times!", favouriteMenu.getValue()));
		sb.append("\n");
		sb.append(String.format("%s Total Order : Rp%,d", user.getUsername(), total));

		return sb.toString();
	}
	
	private SimpleEntry<String, Integer> getFavouriteMenu(HashMap<String, Integer> menuOrderCounts)
	{
		String menuName = "";
		int highestQuantity = 0;
		
		for (String key : menuOrderCounts.keySet())
		{
			int quantity = menuOrderCounts.get(key);
			if (quantity > highestQuantity)
			{
				menuName = key;
				highestQuantity = quantity;
			}
		}
		
		return new SimpleEntry<String, Integer>(menuName, highestQuantity);
	}
}
