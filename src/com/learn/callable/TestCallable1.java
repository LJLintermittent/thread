package com.learn.callable;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * callable实现网图下载
 * Description: <br/>
 * date: 2020/7/11 15:00<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestCallable1 implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable1(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url,name);
        System.out.println("下载了文件，文件名为："+name);
        return true;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable1 t1 = new TestCallable1("http://img4.imgtn.bdimg.com/it/u=1963304009,2816364381&fm=26&gp=0.jpg","4.jpg");
        TestCallable1 t2 = new TestCallable1("http://img1.imgtn.bdimg.com/it/u=1320272459,772040234&fm=26&gp=0.jpg", "5.jpg");
        TestCallable1 t3 = new TestCallable1("http://img0.imgtn.bdimg.com/it/u=3225163326,3627210682&fm=26&gp=0.jpg", "6.jpg");

        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Boolean> submit1 = pool.submit(t1);
        Future<Boolean> submit2 = pool.submit(t2);
        Future<Boolean> submit3 = pool.submit(t3);

        Boolean aBoolean1 = submit1.get();
        Boolean aBoolean2 = submit2.get();
        Boolean aBoolean3 = submit3.get();

        pool.shutdownNow();
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
