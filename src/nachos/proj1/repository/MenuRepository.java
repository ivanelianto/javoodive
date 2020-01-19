package nachos.proj1.repository;

import java.util.ArrayList;

import nachos.proj1.models.Menu;

public class MenuRepository
{
	private static ArrayList<Menu> menus = new ArrayList<>();

	public static void load(ArrayList<Menu> menus)
	{
		MenuRepository.menus = menus;
	}

	public static ArrayList<Menu> getAll()
	{
		return menus;
	}

	public static Menu findById(String id)
	{
		return menus.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
	}

	public static Menu findByName(String name)
	{
		return menus.stream().filter(x -> x.getName().contains(name)).findFirst().orElse(null);
	}

	public static Menu getByIndex(int index)
	{
		return menus.get(index);
	}

	public static void add(Menu menu)
	{
		menus.add(menu);
	}

	public static void delete(Menu menu)
	{
		menus.remove(menu);
	}

	public static int size()
	{
		return menus.size();
	}
}
