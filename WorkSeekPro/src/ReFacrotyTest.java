public class ReFacrotyTest
{
    private static final String FILTER_STR = ".java";

    public ReFacrotyTest()
    {

    }

    public static void main(String[] args)
    {

    }

    public void update(String type, String name)
    {
        // null��ǰ���ֹ��дһ�����ں�
        boolean flag = type.endsWith(FILTER_STR);
        if (flag)
        {
            boolean validName = null != name && name.length() > 0
                    && name.length() < 10;
            if (validName)
            {
                rightDeal();
            }
            else
            {
                otherDeal();
            }
        }
    }

    private void otherDeal()
    {

    }

    private void rightDeal()
    {

    }
}
