package pattern.chain;

import java.util.Map;

public class ShelfDeleteChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        if (MapUtil.existInMap(map, "TYPE", "DELETE")
                && MapUtil.existInMap(map, "TABLE", "SHELF"))
        {
            System.out.println("É¾³ýSHELF±í");
        }
        else
        {
            this.nextChain.proceed(map);
        }
    }
}
