package nachos.proj1.models;

import java.util.Date;

public class Transaction
{
	private User customer;
	private Menu menu;
	private int quantity;
	private Date transactionDate;
	private String status;

	public Transaction()
	{
		super();
	}

	public Transaction(User customer, Menu menu, int quantity, String status)
	{
		super();
		this.customer = customer;
		this.menu = menu;
		this.quantity = quantity;
		this.transactionDate = new Date();
		this.status = status;
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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
