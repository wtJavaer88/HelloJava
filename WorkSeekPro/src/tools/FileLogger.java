package tools;

import com.wnc.basic.BasicFileUtil;

public class FileLogger
{
    public synchronized static void dbErr(String string)
    {
        // TODO Auto-generated method stub
        BasicFileUtil.writeFileString(
                "D:\\Users\\wnc\\MyLog\\WorkSeek_DbErr.txt", string + "\r\n",
                "UTF-8", true);
    }

    public synchronized static void noramalErr(String string)
    {
        BasicFileUtil.writeFileString(
                "D:\\Users\\wnc\\MyLog\\WorkSeek_normalErr.txt", string
                        + "\r\n", "UTF-8", true);
    }
}
