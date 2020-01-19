package nachos.proj1.models;

import java.util.Date;

public class Transaction
{
	private User customer;
	private Menu menu;
	private int quantity;
	private Date transactionDate;

	public Transaction()
	{
		super();
	}

	public Transaction(User customer, Menu menu, int quantity)
	{
		super();
		this.customer = customer;
		this.menu = menu;
		this.quantity = quantity;
		this.transactionDate = new Date();
	}

	public User getCustomer()
	{
		return customer;
	}

	public void setCustomer(User customer)
	{
		this.customer = customer;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public Menu getMenu()
	{
		return menu;
	}

	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	public Date getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}
}
