package pattern.chain;

import java.util.Map;

public class DefaultDealChain extends ModifyChain
{

    @Override
    public void proceed(Map map)
    {
        System.out.println("��Ҫ��Ĵ�������! MAP:" + map);
    }
}
