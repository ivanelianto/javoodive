package nachos.proj1.repository;

import java.util.ArrayList;

import nachos.proj1.models.User;

public class UserRepository
{
	private static ArrayList<User> users = new ArrayList<>();
	
	public static void load(ArrayList<User> users)
	{
		UserRepository.users = users;
	}
	
	public static ArrayList<User> getAll()
	{
		return users;
	}
	
	public static User findById(String id)
	{
		return users.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public static User getByIndex(int index)
	{
		return users.get(index);
	}
	
	public static void add(User user)
	{
		users.add(user);
	}
	
	public static void delete(User user)
	{
		users.remove(user);
	}
	
	public static int size()
	{
		return users.size();
	}
}
