package com.palak.mtp.threadpool;

import java.util.concurrent.*;
import java.util.function.BiConsumer;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<3;i++){
                    System.out.println("Current Thread : " + Thread.currentThread().getName() + " printing : "+ i);
                }
            }
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        Future<Integer> s = scheduledExecutorService.schedule(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 3;
            }
        }, 4000, TimeUnit.MILLISECONDS);

        callS(s);
        ExecutorService service = Executors.newFixedThreadPool(2);
        /*for(int i=0;i<10;i++){
            service.execute(runnable);
        }*/
        service.shutdown();
        Thread.sleep(2000);
        service.execute(runnable); //result in RejectedExecutionException.
    }

    private static void callS(Future<Integer> s) {
        try {
            System.out.println(s.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
