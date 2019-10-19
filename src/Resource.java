import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private final int MAX = 10;
    private Queue<Integer> resource = new LinkedList<Integer>();
    Random random = new Random();

    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();


    public void produce()
    {
        // 获得锁
        lock.lock();
        while (resource.size() >= MAX) {
            System.out.println("生产者" + Thread.currentThread().getName()
                    + "资源已满，进入等待队列");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int produce = random.nextInt(100);
        resource.add(produce);
        System.out.println("生产者" + Thread.currentThread().getName()
                + "生产产品：" + produce + "，当前资源数" + resource.size());

        full.signalAll();
        empty.signalAll();
        lock.unlock();
    }

    public void consume()
    {
        // 获得锁
        lock.lock();
        while (resource.size() == 0) {
            System.out.println("消费者" + Thread.currentThread().getName()
                    + "资源数为零，进入等待队列");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int consume = resource.poll();
        System.out.println("  消费者" + Thread.currentThread().getName()
                + "消费产品：" + consume + "，当前资源数" + resource.size());

        full.signalAll();
        empty.signalAll();
        lock.unlock();
    }
}
