package com.cognitree.customthreadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyThreadPool {

    private static final Logger log = LoggerFactory.getLogger(MyThreadPool.class);

    private BlockingQueue<FutureTask<?>> queue;
    private FutureTask<?>[] workers;
    private boolean flagShutdown;

    public MyThreadPool(int nThreads) {
        queue = new LinkedBlockingQueue<>();
        workers = new FutureTask[nThreads];
        flagShutdown = false;
        Runnable runnable = this::run;
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void shutdown() {
        log.debug("shutdown : Shutting down thread pool");
        flagShutdown = true;
    }

//    public void awaitTermination(long timeInMilis) {
//        if (flagShutdown) {
//            long currentTime = System.currentTimeMillis();
//            while (currentTime + timeInMilis < System.currentTimeMillis()) {
//                try {
//                    workers.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            workers.clear();
//        }
//    }

    public Future<?> submit(Callable<?> callable) {
        if (!flagShutdown) {
            FutureTask<?> futureTask = new FutureTask<>(callable);
            queue.add(futureTask);
            return futureTask;
        } else {
            log.debug("submit : cant submit,shutdown invoked");
            return null;
        }
    }

    public Future<Boolean> execute(Runnable runnable) {
        if (!flagShutdown) {
            FutureTask<Boolean> futureTask = new FutureTask<>(runnable, true);
            queue.add(futureTask);
            return futureTask;
        } else {
            log.debug("submit : cant submit,shutdown invoked");
            return null;
        }
    }

    private void run() {
        while (!flagShutdown || !queue.isEmpty()) {
            IntStream.range(0, workers.length)
                    .filter(i -> workers[i] == null || workers[i].isDone())
                    .forEachOrdered(i -> {
                workers[i] = queue.poll();
                if (workers[i] != null) {
                    ((Runnable) workers[i]).run();
                }
            });
        }
    }
}
