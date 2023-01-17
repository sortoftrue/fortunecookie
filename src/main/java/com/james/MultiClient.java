package com.james;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClient {
    
    public static void main(String[] args) {
        
        int noThreads = Integer.parseInt(args[0]);
        ExecutorService executorServ = Executors.newFixedThreadPool(noThreads);
        for(int i=0;i<noThreads;i++){
            executorServ.execute(new ClientRunner());
        }

    }

}
