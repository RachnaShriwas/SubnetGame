package com.socket.udp;
import java.io.*;
import java.net.*;

public class UDP_Client
{
   public static void main(String args[]) throws Exception
   {
	   // create datagram socket
	  DatagramSocket clientSocket = new DatagramSocket();
	  
	//Take input from user
      BufferedReader inFromUser =
    	         new BufferedReader(new InputStreamReader(System.in));
      String ip1,ip2,mask;
	  System.out.println("Enter IP Address 1:");
	  ip1 = inFromUser.readLine();
	  System.out.println("Enter IP Address 2:");
	  ip2 = inFromUser.readLine();
	  System.out.println("Enter Subnet Mask:");
	  mask = inFromUser.readLine();
      
	  //Server's IP address
	  String ip = "172.16.93.30";
      InetAddress IPAddress = InetAddress.getByName(ip);
	  
      //Concat strings and send to server
	  String sendData = ip1 + " " + ip2 + " " + mask;
	  System.out.println("Sending data... " + sendData);
	  byte[] sendData1 = sendData.getBytes();
      
	  //make datagram packet and send
	  DatagramPacket sendPacket = new DatagramPacket(sendData1, sendData1.length, IPAddress, 9893);
      clientSocket.send(sendPacket);
      System.out.println("Data sent to server!");
      
      //Receive datagram packet from server
      byte[] receiveData = new byte[1024];
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      
      //Process the data and display to user
      String modifiedSentence = new String(receivePacket.getData());
      modifiedSentence = modifiedSentence.trim();
      System.out.println("Data received from server :  " + modifiedSentence);
      clientSocket.close();
   }
}