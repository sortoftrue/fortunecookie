package com.james;

public class ClientRunner implements Runnable{

    @Override
    public void run() {
        
        String[] args = new String[]{"12345"};
    
        try {
            ClientRepeat.main(args);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
    
    

}
