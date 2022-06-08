package com.palak.mtp.waitnotify;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemoUsingWait {
    public static void main(String[] args) {

        List<Data> dataList = new ArrayList<>();
        Thread producer = new Thread(new Producer(dataList,3));
        Thread consumer = new Thread(new Consumer(dataList));

        consumer.start();
        producer.start();
    }

    static class Producer implements Runnable {

        private final List<Data> dataList;
        private int MAX_CAPACITY;
        private int counter = 0;

        public Producer(List<Data> dataList, int MAX_CAPACITY) {
            this.dataList = dataList;
            this.MAX_CAPACITY = MAX_CAPACITY;
        }

        @Override
        public void run() {
            while (true){
                synchronized (dataList){
                    if(dataList.size() == MAX_CAPACITY){
                        System.out.println("Max capacity reached. " + Thread.currentThread().getName() + " is waiting.");
                        try {
                            dataList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Data data = produce(counter++);
                    dataList.add(data);
                    System.out.println(Thread.currentThread().getName() + " has produced value : " + data.getI());
                    dataList.notifyAll();
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private Data produce(int count){
            return new Data(count);
        }
    }

    static class Consumer implements Runnable {

        private List<Data> dataList;

        public Consumer(List<Data> dataList) {
            this.dataList = dataList;
        }

        @Override
        public void run() {
            while (true){
                synchronized (dataList){
                    if(dataList.isEmpty()){
                        try {
                            System.out.println("No data available to consume. " + Thread.currentThread().getName() + " waiting.");
                            dataList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Data data = dataList.remove(0);
                    consume(data);
                    dataList.notifyAll();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume(Data data){
            System.out.println(Thread.currentThread().getName() + " has consumed value : " + data.getI());
        }
    }

    static class Data{
        private int i;

        public Data(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

}
