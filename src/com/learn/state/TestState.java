package com.learn.state;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 21:52<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("///////////");
        });
       Thread.State state = thread.getState();
        System.out.println(state);

        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (state != Thread.State.TERMINATED){
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);

        }

    }
}
