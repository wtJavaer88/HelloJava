package com.wnc.string;

import java.lang.reflect.Field;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

public class BeanToStringUtil
{

    /**
     * 给出一个对象,智能获取其所有属性的键值对
     * 
     * @param bean
     * @return
     * @throws IllegalAccessException
     */
    public static String getBeanString(Object bean)
            throws IllegalAccessException
    {
        // 得到类对象
        Class clazz = bean.getClass();
        /*
         * 得到类中的所有属性集合
         */
        Field[] fs = clazz.getDeclaredFields();
        Object[] args = new Object[fs.length * 2];

        for (int i = 0; i < fs.length; i++)
        {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = null;
            try
            {
                val = f.get(bean);
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }// 得到此属性的值

            // System.out.println("name:" + f.getName() + "\t value = " + val);

            String type = f.getType().toString();// 得到此属性的类型
            if (type.endsWith("String"))
            {
                // System.out.println(f.getType() + "\t是String");
                args[i * 2] = f.getName();
                args[i * 2 + 1] = "" + val;
            }
            else
            {
                // System.out.println(f.getType() + "\t");
                args[i * 2] = f.getName();
                args[i * 2 + 1] = val;
            }

        }
        // System.out.println("所有参数:" + args);
        for (Object o : args)
        {
            // System.out.println(o);
        }
        return getBeanString2(args);
    }

    /***
     * 该方法接收不定参数,参数数目必须是偶数个, 其中参数1是变量名,参数2是参数1的值
     * 
     * @param args
     * @return
     */
    public static String getBeanString(Object... args)
    {
        return getBeanString2(args);
    }// end of function

    /***
     * 该方法接收参数数组,参数数目必须是偶数个
     * 
     * @param args
     * @return
     */
    public static String getBeanString2(Object[] args)
    {
        String retStr = "";
        int len = 0;
        if (args != null)
        {
            len = args.length;
        }
        else
        {
            System.out.println("为空返回");
            return retStr;
        }
        if (len < 2 || len % 2 == 1)
        {
            return retStr;
        }

        // System.out.println("参数成对出现!");
        String comp = "";
        String addChar = ",";// 分隔符
        for (int i = 0; i < len; i += 2)
        {
            if (i == len - 2)
            {
                addChar = "";
            }

            Object str = args[i];
            Object value = args[i + 1];
            if (str != null)
            {
                if (value instanceof Integer || value instanceof Double)
                {
                    // System.out.println("这是一个整型!  \"" + comp);
                    comp = "\"" + str.toString().replace(",", "") + "\"="
                            + value;
                }
                else if (value instanceof List)
                {
                    // System.out.println("这是一个列表型!  \"" + comp);
                    List list = (List) value;
                    comp = "\"" + str + "\"=\"" + arrToString(list.toArray())
                            + "\"";
                }
                else if (value instanceof Object[])
                {
                    // System.out.println("这是一个数组型!  \"" + comp);
                    comp = "\"" + str + "\"=\"" + arrToString((Object[]) value)
                            + "\"";
                }
                else
                {
                    // System.out.println("这是一个字符串型或Map类型...!  \"" + comp);
                    comp = "\"" + str + "\"=\"" + value + "\"";
                }

                retStr += comp + addChar;
            }
        }// end of for
        retStr = "{" + retStr + "}";
        // System.out.println(retStr);
        return retStr;
    }// end of function

    private static String arrToString(Object[] array)
    {
        if (array == null)
            return null;
        return Arrays.toString(array);
    }
}
