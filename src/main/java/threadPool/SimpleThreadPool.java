package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 简单线程池例子
 *
 * @author jerome_s@qq.com
 * @date 2017/2/23 15:42
 */
public class SimpleThreadPool {

    public static void main(String[] args) {
        ExecutorService executorPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread(String.valueOf(i));
            executorPool.execute(worker);

            // 返回此执行程序的任务队列.当前会从第5个线程才会开始堆积任务等待处理.
            // 可用作于监控线程任务队列情况,如果任务太多处理不来可触发阀值并Thread.sleep(sleepTimes);
            System.out.println(((ThreadPoolExecutor) executorPool).getQueue().size());
        }

        executorPool.shutdown();
        //wait for all tasks to finish
        while (!executorPool.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}


