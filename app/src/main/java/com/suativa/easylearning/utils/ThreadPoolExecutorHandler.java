package com.suativa.easylearning.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHandler {
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static ThreadPoolExecutorHandler handler;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final BlockingQueue<Runnable> mDecodeWorkQueue;
    private Executor executor;

    private ThreadPoolExecutorHandler() {
        mDecodeWorkQueue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mDecodeWorkQueue
        );
    }

    public static ThreadPoolExecutorHandler getInstance() {
        if (handler == null) {
            handler = new ThreadPoolExecutorHandler();
        }
        return handler;
    }

    public void addThread(Runnable runnable) {
        synchronized (executor) {
            executor.execute(runnable);
        }
    }
}
