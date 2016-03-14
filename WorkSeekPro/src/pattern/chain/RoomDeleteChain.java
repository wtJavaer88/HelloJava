package pattern.chain;

import java.util.Map;

public class RoomDeleteChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        if (MapUtil.existInMap(map, "TYPE", "DELETE")
                && MapUtil.existInMap(map, "TABLE", "ROOM"))
        {
            System.out.println("ɾ��ROOM��");
        }
        else
        {
            this.nextChain.proceed(map);
        }
    }
}
