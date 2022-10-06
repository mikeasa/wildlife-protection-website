package com.lecheng.protectAnimals.spider.schedule;



import com.lecheng.protectAnimals.pojo.UrlTask;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 负责接收外部传过来的url任务，通过一定的分发策略，将相应的url任务分发到采集任务当中
 *
 */
public class ScheduleManager {
    /**
     * 待采集队列
     */
    public static LinkedList<UrlTask> todoTaskList = new LinkedList<UrlTask>();
    /**
     * 去重set集合
     */
    public static Set<String> todoTaskSet = new HashSet<String>();
    //将种子URL加入待采集队列
    public static boolean addSeedUrlToTaskList(String seedUrl) throws Exception {
        if (todoTaskSet.contains(seedUrl)){
            return false;
        }
        todoTaskSet.add(seedUrl);
        UrlTask urlTask = new UrlTask(seedUrl);
        todoTaskList.add(urlTask);
        return true;
    }
    public static void addSeedUrlToTaskList(List<String> seedUrl) throws Exception {
       for (String seed : seedUrl){
           addSeedUrlToTaskList(seed);
       }
    }
    //将指定URL从待采集队列删除
    public static void removeTaskList(String url ){
        UrlTask urlTask = new UrlTask(url);
        todoTaskList.remove(urlTask);
        todoTaskSet.remove(url);
    }

    //依照FIFO原则从待采集队列中分发UrlTask
    public static UrlTask getUrlTaskFromTodoTTaskList(){
        UrlTask urlTask = todoTaskList.pollFirst();
        if (urlTask != null){
            todoTaskSet.remove(urlTask.getUrl());
        }
        return urlTask;
    }
}
