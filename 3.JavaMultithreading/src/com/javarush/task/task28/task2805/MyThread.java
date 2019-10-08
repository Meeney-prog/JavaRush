package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    static int count = 0;

    {
        int newPriority = count++ % 10 + 1;
        this.setPriority(newPriority);
        ThreadGroup g = this.getThreadGroup();
        if (g != null) {
            if (newPriority > g.getMaxPriority()) {
                this.setPriority(g.getMaxPriority());
            }
        }
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }
}
