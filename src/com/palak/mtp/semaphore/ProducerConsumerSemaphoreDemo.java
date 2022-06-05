package com.palak.mtp.semaphore;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphoreDemo {

    private Semaphore full = new Semaphore(0);
    private Semaphore empty = new Semaphore(1);
    private Item item = null;
    private volatile int count = 0;

    private void produce(){
        try {
            empty.acquire();
            this.item = produceItem();
            System.out.println("Produced : "+ this.item.getItemName());
            Thread.sleep(3000);
            full.release();
            System.out.println("producer avail permits in full " + full.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Item produceItem() {
        count++;
        return new Item("Item "+count);
    }

    private void consume(){
        try {
            System.out.println("consumer avail permits in full before acquire" + full.availablePermits());
            full.acquire();
            System.out.println("consumer avail permits in full after acquire" + full.availablePermits());
            Item item = consumeItem();
            System.out.println("Consumed : "+ item.getItemName());
            Thread.sleep(3000);
            empty.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Item consumeItem() {
        return this.item;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerSemaphoreDemo producerConsumerSemaphoreDemo = new ProducerConsumerSemaphoreDemo();

        Thread producerThread = new Thread(() -> {
            while(producerConsumerSemaphoreDemo.count < 100){
                producerConsumerSemaphoreDemo.produce();
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (producerConsumerSemaphoreDemo.count < 100){
                producerConsumerSemaphoreDemo.consume();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    private static class Item{
        private String itemName;

        public Item(String itemName) {
            this.itemName = itemName;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }
    }
}
