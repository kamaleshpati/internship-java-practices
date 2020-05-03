package com.cognitree.customthreadpool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MyThreadPoolTest {

    private static final Logger log = LoggerFactory.getLogger(MyThreadPoolTest.class);

    @Test
    public void testMyThreadPool() {
        MyThreadPool pool = new MyThreadPool(3);
        ArrayList<Future<?>> futures = IntStream.range(0, 5)
                .mapToObj(TaskCallable::new)
                .map(pool::submit)
                .collect(Collectors.toCollection(ArrayList::new));
        // pool.shutdown();
        ArrayList<Future<?>> futures1 = new ArrayList<>();
        for (int i1 = 0; i1 < 5; i1++) {
            TaskRunnable taskRunnable = new TaskRunnable();
            Future<Boolean> execute = pool.execute(taskRunnable);
            futures1.add(execute);
        }
        Assertions.assertEquals(5, futures.size());
        IntStream.range(0, futures.size()).forEachOrdered(i -> {
            try {
                Assertions.assertEquals(i, futures.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("MyThreadPoolTest : {}", e.getMessage());
            }
        });
        IntStream.range(0, futures.size()).forEachOrdered(i -> {
            try {
                Assertions.assertTrue((Boolean) futures1.get(i).get());
                log.debug("{}",futures.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("MyThreadPoolTest : {}", e.getMessage());
            }
        });
        pool.shutdown();
    }

    private static class TaskCallable implements Callable<Integer> {

        private int num;

        public TaskCallable(int n) {
            num = n;
        }

        @Override
        public Integer call() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("TaskCallable : this is Callable {}", num);
            return num;
        }
    }

    private static class TaskRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("TaskRunnable : this is runnable");
        }
    }
}