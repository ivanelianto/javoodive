package nachos.proj1.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nachos.proj1.models.Transaction;
import nachos.proj1.models.User;

public class TransactionRepository
{
	private static ArrayList<Transaction> transactions = new ArrayList<>();
	
	public static Transaction findByMenuName(String menuName)
	{
		return transactions.stream()
				.filter(x -> x.getMenu().getName().contains(menuName))
				.findFirst()
				.orElse(null);
	}
	
	public static List<Transaction> findByUser(User user)
	{
		return transactions.stream()
				.filter(x -> x.getCustomer().equals(user))
				.collect(Collectors.toList());
	}
	
	public static void add(Transaction transaction)
	{
		transactions.add(transaction);
	}
	
	public static void delete(Transaction transaction)
	{
		transactions.remove(transaction);
	}
}
