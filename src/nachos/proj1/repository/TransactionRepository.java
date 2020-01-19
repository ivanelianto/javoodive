package nachos.proj1.repository;

import java.util.ArrayList;

import nachos.proj1.models.Transaction;

public class TransactionRepository
{
	private static ArrayList<Transaction> transactions = new ArrayList<>();
	
	public static void add(Transaction transaction)
	{
		transactions.add(transaction);
	}
	
	public static void delete(Transaction transaction)
	{
		transactions.remove(transaction);
	}
}
