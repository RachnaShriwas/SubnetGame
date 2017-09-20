package com.socket.tcp;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCP_Client {

   public static void main(String [] args) {
	  //Server IP address 
      String serverName = "172.16.93.30";
      //Server port no.
      int port = 6760;
      
      try {
    	  
    	  String ip1,ip2,mask;
    	  Scanner sc = new Scanner(System.in);
    	  
    	  //Take input from user
    	  System.out.println("Enter IP Address 1:");
    	  ip1 = sc.nextLine();
    	  System.out.println("Enter IP Address 2:");
    	  ip2 = sc.nextLine();
    	  System.out.println("Enter Subnet Mask:");
    	  mask = sc.nextLine();
    	  
    	  System.out.println("Connecting to " + serverName + " on port " + port);
    	  Socket client = new Socket(serverName, port);
    	  System.out.println("Connected to " + client.getRemoteSocketAddress());
    	  
    	  //Object to send info to server
    	  OutputStream outToServer = client.getOutputStream();
    	  DataOutputStream out = new DataOutputStream(outToServer);

    	  //Send user input to server
    	  out.writeUTF(ip1);
    	  out.writeUTF(ip2);
    	  out.writeUTF(mask);

    	  //Object to receive info from server
    	  InputStream inFromServer = client.getInputStream();
    	  DataInputStream in = new DataInputStream(inFromServer);

    	  //Display result received from server to the user
    	  System.out.println("Result from server: " + in.readUTF());

    	  sc.close();
    	  //Close Client Socket
    	  client.close();
      }catch(IOException e) {
    	  e.printStackTrace();
      }
   }
}