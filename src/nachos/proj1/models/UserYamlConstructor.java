package nachos.proj1.models;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class UserYamlConstructor extends Constructor
{
	@SuppressWarnings("unchecked")
	@Override
	protected Object constructObject(Node node)
	{
		if (node.getTag() == Tag.MAP)
		{
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) super.constructObject(node);

			User user = new User();
			user.setId(map.get("id").toString());
			user.setUsername(map.get("username").toString());
			user.setName(map.get("name").toString());
			user.setBalance(Integer.parseInt(map.get("balance").toString()));	
			return user;
		}

		return super.constructObject(node);
	}
}