package com.james;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientRepeat {
    

    public static void main(String[] args) throws Exception {
        
        int port = Integer.parseInt(args[0]);
        Socket socket = new Socket("localhost",port);
        
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


    }
    

}
