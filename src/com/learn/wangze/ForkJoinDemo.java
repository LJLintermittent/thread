package com.learn.wangze;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 * date: 2021/7/8 17:44
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class ForkJoinDemo {
    public static void main(String[] args) {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        Integer ans = null;
        try {
            ans = forkJoinTask.get();
            System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }
}

@SuppressWarnings("all")
class MyTask extends RecursiveTask<Integer> {

    //步长
    private static final int VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int mid = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, mid);
            MyTask task2 = new MyTask(mid + 1, end);
            //拆分
            task1.fork();
            task2.fork();
            //合并
            result = task1.join() + task2.join();
        }
        return result;
    }
}
