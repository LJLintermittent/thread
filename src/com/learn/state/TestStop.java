package com.learn.state;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 21:09<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestStop implements Runnable{
    private boolean flag = true;
    
    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("线程1:"+(i++));
        }
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程2:"+i);
            if(i==900){
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }

    }
    public void stop(){
        this.flag = false;
    }

}
