package blocking.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 启动消息生产和消费线程
 *
 * @author jerome_s@qq.com
 * @date 2017/2/23 15:08
 */
public class ProducerConsumerService {

    public static void main(String[] args) {

        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        //starting producer to produce messages in queue
        new Thread(producer).start();

        //starting consumer to consume messages from queue
        new Thread(consumer).start();

        System.out.println("Producer and Consumer has been started");
    }

}
