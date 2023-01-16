package com.james;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public final class App {
    
    public static void main(String[] args) throws Exception{
        
        int port = Integer.parseInt(args[0]);
        ServerSocket server = new ServerSocket(port);

        File file = new File("./%s".formatted(args[1]));
        //file.createNewFile();
        if(file.isFile()){
            System.out.println("file found!");
        } else System.out.println("no file found!");

        //check how many lines
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        int noFortunes = 0;
        String fortune ="";
        while((fortune = reader.readLine())!=null){
            System.out.println(fortune);
            noFortunes++;
        }
        System.out.printf("There are %d fortunes\n",noFortunes);

        

        while(true){
            String input="";
            System.out.println("Wait conn");
            Socket socket = server.accept();
            

            while(!input.equals("exit")){
                System.out.println(input);
                System.out.println("Wait for input");
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                input = in.readUTF();

                switch (input){
                    case "get":{
                        String outputFortune="";
                        Random newRandom = new Random();
                        int toSkip = newRandom.nextInt(noFortunes);
                        System.out.println(toSkip);
                        BufferedReader reader2 = new BufferedReader(new FileReader(file));

                        for(int i=0;i<=toSkip;i++){
                            fortune = reader2.readLine();
                            System.out.println(fortune);
                        }

                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                        output.writeUTF(fortune);
                        break;
                    }
                    case "exit":{
                        System.out.println("bye!");
                        break;
                    }
                    default:{
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                        output.writeUTF("Invalid entry!");
                        break;
                    }
                }

            }

        }
        

        


    }
}
