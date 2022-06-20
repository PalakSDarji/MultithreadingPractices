package com.palak.mtp.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> {

    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private AtomicInteger count = new AtomicInteger();

    public int getCount() {
        return count.get();
    }

    public void push(T value){
        Node<T> newNode = new Node<>(value);

        while(true) {
            if(head == null || head.get() == null){
                head.set(newNode);
            }
            else {
                Node<T> currentNode = head.get();
                newNode.nextNode = currentNode;
                if(head.compareAndSet(currentNode,newNode)){
                    count.incrementAndGet();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                else{
                    LockSupport.parkNanos(1);
                    System.out.println("Still trying to push "+ value);
                }
            }
        }
    }

    public T pop(){

        if(head == null || head.get() == null){
            return null;
        }

        while (true){
            Node<T> currentNode = head.get();
            Node<T> newNode = currentNode.nextNode;
            if(head.compareAndSet(currentNode,newNode)){
                count.decrementAndGet();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return currentNode.value;
            }
            else {
                LockSupport.parkNanos(1);
                System.out.println("Still trying to pop " + currentNode.value);
            }
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> nextNode;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    private void play() {

        LockFreeStack<Integer> stack = new LockFreeStack<>();
        IncrementThread incrementThread = new IncrementThread(stack);
        DecrementThread decrementThread = new DecrementThread(stack);

        incrementThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total size: " + stack.getCount());
    }

    private static class IncrementThread extends Thread {

        private final LockFreeStack<Integer> stack;

        public IncrementThread(LockFreeStack<Integer> stack) {
            this.stack = stack;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++){
                stack.push(i);
                System.out.println("pushed: " + i);
            }
        }
    }

    private static class DecrementThread extends Thread {

        private final LockFreeStack<Integer> stack;

        public DecrementThread(LockFreeStack<Integer> stack) {
            this.stack = stack;
        }

        @Override
        public void run() {
            while(true){
                Integer value = stack.pop();
                System.out.println("popped: " + value);
                if(stack.getCount() == 0){
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        LockFreeStack<Integer> lockFreeStack = new LockFreeStack<>();
        lockFreeStack.play();
    }
}
