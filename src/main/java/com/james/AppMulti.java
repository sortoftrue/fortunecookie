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

public final class AppMulti {
    
    public static void main(String[] args) throws Exception{
        
        int port = Integer.parseInt(args[0]);
        ServerSocket server = new ServerSocket(port);

        File file = new File("./%s".formatted(args[1]));
        
        if(file.isFile()){
            System.out.println("file found!");
        } else System.out.println("no file found!");

        //check how many fortunes in text file (for randomizing)
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        int noFortunes = 0;
        String fortune ="";
        while((fortune = reader.readLine())!=null){
            System.out.println(fortune);
            noFortunes++;
        }
        System.out.printf("There are %d fortunes\n",noFortunes);

        String input="";

        while(true){
            System.out.println("Wait conn");
            Socket socket = server.accept();
            
            ClientHandler clientSock = new ClientHandler(socket, noFortunes, file);

            new Thread(clientSock).start();
        }
        


    }
}
