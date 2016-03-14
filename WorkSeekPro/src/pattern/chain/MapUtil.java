package pattern.chain;

import java.util.Map;

public class MapUtil
{
    public static boolean existInMap(Map map, String key, String value)
    {
        return map.containsKey(key) && value != null
                && value.equals(map.get(key).toString());
    }
}
