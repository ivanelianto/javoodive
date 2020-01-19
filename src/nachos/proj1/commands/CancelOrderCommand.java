package nachos.proj1.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nachos.proj1.models.Menu;
import nachos.proj1.models.Transaction;
import nachos.proj1.models.User;
import nachos.proj1.repository.MenuRepository;
import nachos.proj1.repository.TransactionRepository;
import nachos.proj2.CommandService;

public class CancelOrderCommand extends QueryCommand
{
	private static final int MINIMUM_ARGUMENT_LENGTH = 2;
	private Menu menu;

	public CancelOrderCommand(ArrayList<String> arguments)
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

		Pattern pattern = Pattern.compile("([\\w ]+)");
		Matcher matcher = pattern.matcher(rawArgument);

		if (matcher.find())
		{
			String menuKeyword = matcher.group(1).trim();
			Menu menu = MenuRepository.findByName(menuKeyword);

			if (menu == null)
				return String.format("Transaction with menu name '%s' not found.", menuKeyword);
			else
			{
				User customer = CommandService.getInstance().getSender();
				Transaction transaction = TransactionRepository.findByMenuName(menu.getName());
				TransactionRepository.delete(transaction);

				return String.format("%s cancel order %s.", customer.getUsername(), transaction.getMenu().getName());
			}
		}

		return null;
	}

	private boolean isValidArguments(ArrayList<String> arguments)
	{
		return arguments.size() >= MINIMUM_ARGUMENT_LENGTH;
	}
}
