package com.wnc.threads;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicTaskDispatcher
{
    protected List<Object> taskList = new ArrayList<Object>();
    protected List<MyMultiThreads> threads = new ArrayList<MyMultiThreads>();
    protected int counts = 0;

    /***
     * 必须在这儿初始化需要多线程处理的关键数据
     */
    public abstract void buildTaskList();

    /***
     * 设置要反射执行的TaskDeal类 初始化自定义的多线程类
     * 
     * @param j
     *            线程数目
     * 
     * @param reflectClassName
     *            反射的CustomTaskDealInterface类名
     */
    public void buildThreadList(int j, CustomTaskDealClass customTaskDealClass)
    {
        counts = j;

        if (counts == 0)
        {
            System.out.println("请初始化Counts!");
            throw new IllegalArgumentException();
        }
        else if (counts < 0)
        {
            System.out.println("Counts必须大于0!");
            throw new IllegalArgumentException();
        }
        else if (counts >= 100)
        {
            System.out.println("Counts必须小于100!");
            throw new IllegalArgumentException();
        }
        else
        {
            int i = 0;

            while (i < counts)
            {
                i++;
                CustomTaskDealClass customTaskDealClassClone = (CustomTaskDealClass) customTaskDealClass
                        .clone();
                MyMultiThreads thread = new MyMultiThreads(
                        customTaskDealClassClone);
                // thread.setDaemon(true);
                threads.add(thread);
            }
        }
    }

    public void buildThreadList(List<CustomTaskDealClass> taskClassList)
    {
        if (taskClassList == null || taskClassList.size() == 0)
        {
            System.out.println("列表不能为空!");
            throw new IllegalArgumentException();
        }
        counts = taskClassList.size();
        if (counts >= 100)
        {
            System.out.println("线程必须小于100!");
            throw new IllegalArgumentException();
        }
        else
        {
            int i = 0;

            while (i < counts)
            {
                CustomTaskDealClass customTaskDealClassClone = taskClassList
                        .get(i);
                MyMultiThreads thread = new MyMultiThreads(
                        customTaskDealClassClone);
                // thread.setDaemon(true);
                threads.add(thread);
                i++;
            }
        }
    }

    public boolean threadErr()
    {
        return threads == null || threads.size() == 0;
    }

    /**
     * 分派任务
     */
    public void paiTask()
    {
        System.out.println(taskList.size());
        if (threadErr())
        {
            throw new RuntimeException("当前不存在任何已初始化的线程!");
        }
        for (int i = 0; i < taskList.size(); i++)
        {
            threads.get(i % threads.size()).addSonTask(taskList.get(i));
        }
    }

    /**
     * 执行多线程任务
     */
    public void doTask()
    {
        if (!threadErr())
        {
            for (int i = 0; i < threads.size(); i++)
            {
                threads.get(i % threads.size()).start();
            }
        }
        else
        {
            throw new RuntimeException("当前不存在任何已初始化的线程!");
        }
    }

    public void waitOperating()
    {

        // while (true)
        // {
        // boolean isallOver = true;
        // if (threads != null && threads.size() > 0)
        // {
        // for (int i = 0; i < threads.size(); i++)
        // {
        // isallOver = threads.get(i).isOver;
        // if (!isallOver)
        // {
        // System.out.println("未结束-------------"
        // + threads.get(i).overItems);
        // break;
        // }
        // }
        // }
        // if (isallOver)
        // {
        // break;
        // }
        // else
        // {
        // try
        // {
        // System.out.println("睡眠1000ms:");
        // Thread.currentThread().sleep(100);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }
        // }
        // }
        for (Thread iThread : threads)
        {
            try
            {
                // 等待所有线程执行完毕
                iThread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                throw new RuntimeException("等待线程终止时出错!");
            }
        }

    }
}
