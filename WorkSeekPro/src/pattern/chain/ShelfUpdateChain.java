package pattern.chain;

import java.util.Map;

public class ShelfUpdateChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        if (MapUtil.existInMap(map, "TYPE", "UPDATE")
                && MapUtil.existInMap(map, "TABLE", "SHELF"))
        {
            System.out.println("¸üÐÂSHELF±í");
        }
        else
        {
            this.nextChain.proceed(map);
        }
    }
}
