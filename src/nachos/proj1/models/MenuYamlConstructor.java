package nachos.proj1.models;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class MenuYamlConstructor extends Constructor
{
	@SuppressWarnings("unchecked")
	@Override
	protected Object constructObject(Node node)
	{
		if (node.getTag() == Tag.MAP)
		{
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) super.constructObject(node);

			Menu menu = new Menu();
			menu.setId(map.get("id").toString());
			menu.setName(map.get("name").toString());
			menu.setSellPrice(Integer.parseInt(map.get("price").toString()));	
			return menu;
		}

		return super.constructObject(node);
	}
}