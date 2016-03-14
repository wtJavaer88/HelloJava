package pattern.chain;

import java.util.HashMap;
import java.util.Map;

public class Maintest
{
    public static void main(String[] args)
    {
        String reqString = "?TYPE=DELETE&TABLE=SHELF2&ID=10&NAME='HXQC  '";
        Map map = new HashMap();
        map = getMap(reqString);
        System.out.println(map);
        System.out.println(MapUtil.existInMap(map, "TYPE", "UPDATE")
                && MapUtil.existInMap(map, "TABLE", "ROOM"));

        ModifyChain room_uChain = new RoomUpdateChain();
        ModifyChain room_dChain = new RoomDeleteChain();
        ModifyChain shelf_uChain = new ShelfUpdateChain();
        ModifyChain shelf_dChain = new ShelfDeleteChain();
        // 组装链条
        room_uChain.setNextChain(room_dChain);
        room_dChain.setNextChain(shelf_uChain);
        shelf_uChain.setNextChain(shelf_dChain);
        shelf_dChain.setNextChain(new DefaultDealChain());
        // 传入参数从链条的头开始执行
        room_uChain.proceed(map);
    }

    private static Map getMap(String reqString)
    {
        Map map = new HashMap();
        if (reqString == null || reqString.trim().isEmpty())
        {
            return map;
        }
        reqString = reqString.replace("?", "").trim();
        int indexQuote = reqString.indexOf("?");
        boolean flag = indexQuote > -1 && reqString.length() > 1;

        if (flag)
            reqString = reqString.substring(indexQuote);

        String[] vars = reqString.split("&");
        if (vars != null && vars.length > 0)
        {
            for (String cp : vars)
            {
                String[] keyValues = cp.split("=");
                if (keyValues != null && keyValues.length == 2)
                {
                    map.put(keyValues[0], keyValues[1]);
                }
            }
        }
        return map;
    }
}
