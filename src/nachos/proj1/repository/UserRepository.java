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
	
	public static void add(User user)
	{
		users.add(user);
	}
	
	public static void delete(User user)
	{
		users.remove(user);
	}
}
