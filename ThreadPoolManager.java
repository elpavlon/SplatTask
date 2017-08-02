package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static ExecutorService service = Executors.newFixedThreadPool(4);

    private ThreadPoolManager() {
    }

    public static ExecutorService getThreadPool() {
        return service;
    }
}
