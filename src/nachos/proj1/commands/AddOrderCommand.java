package nachos.proj1.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nachos.proj1.models.Menu;
import nachos.proj1.models.Transaction;
import nachos.proj1.models.User;
import nachos.proj1.repository.MenuRepository;
import nachos.proj1.repository.TransactionRepository;
import nachos.proj1.utilities.DateHelper;
import nachos.proj2.CommandService;

public class AddOrderCommand extends QueryCommand
{
	private static final int MINIMUM_ARGUMENT_LENGTH = 2;
	private int quantity;
	private Menu menu;

	public AddOrderCommand(ArrayList<String> arguments)
	{
		super(arguments);
	}

	@Override
	public String execute()
	{
		ArrayList<String> arguments = this.getArguments();

		if (!isValidArguments(arguments))
			return "Missing arguments.";

		arguments.remove(0);
		String rawArgument = String.join(" ", arguments);

		Pattern pattern = Pattern.compile("(\\d*)?([\\w ]+)");
		Matcher matcher = pattern.matcher(rawArgument);

		if (matcher.find())
		{
			String inputtedQuantity = matcher.group(1).trim();
			quantity = inputtedQuantity.isEmpty() ? 1 : Integer.parseInt(inputtedQuantity);

			String menuName = matcher.group(2).trim();
			Menu menu = MenuRepository.findByName(menuName);

			if (menu == null)
				return "Menu not found.";
			else
			{
				User customer = CommandService.getInstance().getSender();
				Transaction transaction = new Transaction(customer, menu, quantity);
				TransactionRepository.add(transaction);
				return String.format("%s ordered %dx %s at %s.", customer.getUsername(), transaction.getQuantity(),
						transaction.getMenu().getName(), DateHelper.getFormattedDate(transaction.getTransactionDate()));
			}
		}

		return null;
	}

	private boolean isValidArguments(ArrayList<String> arguments)
	{
		return arguments.size() >= MINIMUM_ARGUMENT_LENGTH;
	}
}
