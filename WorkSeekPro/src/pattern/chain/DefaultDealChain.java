package pattern.chain;

import java.util.Map;

public class DefaultDealChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        System.out.println("你要求的处理不存在! MAP:" + map);
    }
}
