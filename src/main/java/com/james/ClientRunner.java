package com.james;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRunner implements Runnable{

    @Override
    public void run() {
        
        // String[] args = new String[]{"12345"};
    
        // try {
        //     ClientRepeat.main(args);
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        try (Socket socket = new Socket("localhost",12345)) {
            String input="get";

            while(!input.equals("exit")){
                //Scanner in = new Scanner(System.in);
                //input = in.next();

                switch (input){
                    case "exit":{
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        System.out.println("Sending exit!");
                        out.writeUTF("exit");
                        
                        break;
                    }
                    default:{
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF(input);

                        DataInputStream fromServer = new DataInputStream(socket.getInputStream());
                        String read = fromServer.readUTF();
                        System.out.println(read);
                        break;
                    }
                }
                
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
    
    

}
