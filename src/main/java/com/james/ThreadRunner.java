package com.james;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadRunner implements Runnable{
    
    ClientHandler client;

    ThreadRunner(ClientHandler client){
        this.client = client;
    }

    @Override
    public void run(){

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);

        Future future = exec.submit(client);
        Runnable cancelTask = () -> future.cancel(true);

        exec.schedule(cancelTask, 3000, TimeUnit.MILLISECONDS);
        exec.shutdown();    

    }

}
