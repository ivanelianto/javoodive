package nachos.proj2.commands;

import java.util.ArrayList;
import java.util.List;

import nachos.proj1.models.Transaction;
import nachos.proj1.models.User;
import nachos.proj1.repository.TransactionRepository;
import nachos.proj1.utilities.DateHelper;
import nachos.proj2.services.CommandService;

public class ShowDebtCommand extends QueryCommand
{
	public ShowDebtCommand(ArrayList<String> arguments)
	{
		super(arguments);
	}

	@Override
	public String execute()
	{
		StringBuilder sb = new StringBuilder();
		User user = CommandService.getInstance().getSender();
		List<Transaction> transactions = TransactionRepository.findByUser(user);
		int total = 0;
		for (int i = 0; i < transactions.size(); i++)
		{
			Transaction transaction = transactions.get(i);
			int subtotal = transaction.getQuantity() * transaction.getMenu().getSellPrice();
			total += subtotal;
			
			String transactionFormattedDate = DateHelper.getTransactionFormattedDate(transaction.getTransactionDate());
			
			String text = String.format("%s, %dx %s @ Rp%,d", transactionFormattedDate,
					transaction.getQuantity(),
					transaction.getMenu().getName(),
					subtotal);
			
			sb.append(text);
			
			if (i < transactions.size() - 1)
				sb.append("\n");
		}

		sb.append("\n");
		sb.append(String.format("Total : Rp%,d", total));
		
		return sb.toString();
	}

}
