package pattern.chain;

import java.util.Map;

public class RoomUpdateChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        if (MapUtil.existInMap(map, "TYPE", "UPDATE")
                && MapUtil.existInMap(map, "TABLE", "ROOM"))
        {
            System.out.println("¸üÐÂROOM±í");
        }
        else
        {
            this.nextChain.proceed(map);
        }
    }
}
