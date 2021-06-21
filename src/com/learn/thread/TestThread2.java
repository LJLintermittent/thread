package com.learn.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 使用Thread 实现多线程同步下载图片
 * Description: <br/>
 * date: 2020/7/11 13:53<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestThread2 extends Thread{
    private String url;
    private String name;

    public TestThread2(String url,String name) {
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url,name);
        System.out.println("下载了文件，文件名为："+name);

    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594457345713&di=a4f2f72bbc9b069b044661aa28900fe9&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg","1.jpg");
        TestThread2 t2 = new TestThread2("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594457615280&di=1e43980fcecf25c7fb43b824eb080315&imgtype=0&src=http%3A%2F%2Fp2.so.qhimgs1.com%2Ft01dfcbc38578dac4c2.jpg", "2.jpg");
        TestThread2 t3 = new TestThread2("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594457640298&di=aa8885687c1939d0d9e83db894fefa52&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg", "3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownLoader{
    public void downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
