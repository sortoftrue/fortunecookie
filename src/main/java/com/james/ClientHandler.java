package com.james;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.Random;

public class ClientHandler implements Runnable{

    private Socket clientSocket;
    int noFortunes;
    File file;

    public ClientHandler(Socket socket, int noFortunes, File file){
        clientSocket = socket;
        this.noFortunes = noFortunes;
        this.file = file;
    }

    @Override
    public void run() {
        
        String input = "";

        try{
            while(!input.equals("exit")){
                //System.out.println(input);
                System.out.println(Thread.currentThread().getName());
                System.out.println("Wait for input");
                
                DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                input = in.readUTF();
    
                switch (input){
                    case "get":{
                        String outputFortune="";
                        Random newRandom = new Random();
                        int toSkip = newRandom.nextInt(noFortunes);
                        System.out.println(toSkip);
                        BufferedReader reader2 = new BufferedReader(new FileReader(file));
    
                        for(int i=0;i<=toSkip;i++){
                            outputFortune = reader2.readLine();
                            System.out.println(outputFortune);
                        }
    
                        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                        output.writeUTF(outputFortune + " @ " + Thread.currentThread().getName());
                        break;
                    }
                    case "exit":{
                        System.out.println("bye!");
                        break;
                    }
                    default:{
                        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                        output.writeUTF("Invalid entry!");
                        break;
                    }
                }
    
                if (input.equals("exit")) break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        

        
    }
    


}
