package com.suativa.easylearning.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHandler {
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static ThreadPoolExecutorHandler handler;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final BlockingQueue<Runnable> mDecodeWorkQueue;
    private ScheduledThreadPoolExecutor executor;

    private ThreadPoolExecutorHandler() {
        mDecodeWorkQueue = new LinkedBlockingQueue<>();
        executor = new ScheduledThreadPoolExecutor(
                NUMBER_OF_CORES
        );
    }

    public static ThreadPoolExecutorHandler getInstance() {
        if (handler == null) {
            handler = new ThreadPoolExecutorHandler();
        }
        return handler;
    }

    public void addThread(Runnable runnable) {
        addThread(runnable, 0);
    }

    public void addThread(Runnable runnable, long delay) {
        synchronized (executor) {
            executor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
        }
    }
}
